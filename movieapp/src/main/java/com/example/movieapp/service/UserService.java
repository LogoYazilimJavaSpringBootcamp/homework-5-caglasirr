package com.example.movieapp.service;

import com.example.movieapp.client.PaymentClient;
import com.example.movieapp.dto.*;
import com.example.movieapp.enums.CurrencyType;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.UserRepository;
import com.example.movieapp.security.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private AmqpTemplate rabbitTemplate;
    private PaymentClient paymentClient;
    private Encryptor encryptor;
    private UserRepository userRepository;
    private MovieService movieService;
    private SubscriptionTypeService subscriptionTypeService;

    @Value("${message.register}")
    private String REGISTER_MESSAGE;

    @Value("${message.login}")
    private String LOGIN_MESSAGE;

    @Value("${message.usernotfound}")
    private String USERNOTFOUND_MESSAGE;

    @Value("${message.useralreadyexists}")
    private String USER_ALREADY_EXISTS_MESSAGE;

    //Kullanıcı sisteme kayıt olur:
    public String register(UserRegisterRequest userRegisterRequest) {

        //Sistemde böyle bir kullanıcının zaten olup olmadığının kontrolünü yapar:
        boolean isExists = userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent();
        if(!isExists) {
            //Request'ten alınan datanın yeni oluşturulan user'a set edilmesi:
            User user = new User();
            user.setName(userRegisterRequest.getName());
            user.setSurname(userRegisterRequest.getSurname());
            user.setEmail(userRegisterRequest.getEmail());
            user.setSubscriptionType(subscriptionTypeService.findById(userRegisterRequest.getSubscriptionTypeId()));

            //Şifrenin hash algoritması ile db'ye kaydedilmesini sağlar.
            user.setPassword(encryptor.encryptGivenPassword(userRegisterRequest.getPassword()));

            //User'ı db'ye kaydeder:
            userRepository.save(user);

            //Ödeme yapılmasını sağlar.
            PaymentDto paymentDto = paymentClient.createPayment(preparePaymentInfo(user));
            log.info(paymentDto.toString());

            return REGISTER_MESSAGE;

        }

        return USER_ALREADY_EXISTS_MESSAGE;
    }

    //Kullanıcı sisteme login olur:
    public String login(UserLoginRequest request){
           request.setPassword(encryptor.encryptGivenPassword(request.getPassword()));
           boolean isExists = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword()).isPresent();
           if(isExists) {
               return LOGIN_MESSAGE;

           }else{
               return USERNOTFOUND_MESSAGE;
           }
    }

    public void changeUserInfo(int userId, UserChangeInfoRequest userChangeInfoRequest) {
        User foundUser = userRepository.findById(userId).orElseThrow();
        foundUser.setName(userChangeInfoRequest.getName());
        foundUser.setPassword(encryptor.encryptGivenPassword(userChangeInfoRequest.getPassword()));
        userRepository.save(foundUser);
    }

    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    //User'ların email adreslerini getiren method.
    public List<String> getAllEmails(){
        return getAllUsers().stream().map(u -> u.getEmail()).toList();
    }

    public User findById(int userId){
        return userRepository.findById(userId).orElseThrow();
    }

    //Kullanıcının üyelik tipine göre ödeme bilgisini getiren method.
    public PaymentDto preparePaymentInfo(User user){
        PaymentDto paymentDto = new PaymentDto();
        BigDecimal amount = user.getSubscriptionType().getAmount();
        paymentDto.setAmount(amount);
        paymentDto.setUserId(user.getId());
        paymentDto.setCurrencyType(CurrencyType.TL);
        paymentDto.setPaymentDate(LocalDateTime.now());
        return paymentDto;
    }

    @Autowired
    public UserService(AmqpTemplate rabbitTemplate, PaymentClient paymentClient, Encryptor encryptor, UserRepository userRepository, MovieService movieService, SubscriptionTypeService subscriptionTypeService) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentClient=paymentClient;
        this.encryptor = encryptor;
        this.userRepository = userRepository;
        this.movieService=movieService;
        this.subscriptionTypeService=subscriptionTypeService;
    }

    @PostConstruct
    public void init(){
        //Uygulama başlarken db'de movies tablosuna birkaç film ve subscriptionprice tablosuna bilgiler eklenir.
        subscriptionTypeService.addSubscriptionTypes();
        movieService.addMovies();
    }

}
