package com.example.movieapp.service;

import com.example.movieapp.client.PaymentClient;
import com.example.movieapp.dto.*;
import com.example.movieapp.enums.CurrencyType;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.SubscriptionPriceRepository;
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
    private SubscriptionPriceRepository subscriptionPriceRepository;
    private MovieService movieService;
    private SubscriptionPriceService subscriptionPriceService;

    @Value("${message.register}")
    private String REGISTER_MESSAGE;

    @Value("${message.login}")
    private String LOGIN_MESSAGE;

    @Value("${message.usernotfound}")
    private String USERNOTFOUND_MESSAGE;

    //Kullanıcı sisteme kayıt olur:
    public String register(UserRegisterRequest userRegisterRequest) {
        //Uygulama başlarken db'de movies tablosuna birkaç film ve subscriptionprice tablosuna bilgiler eklenir.

        //Request'ten alınan datanın yeni oluşturulan user'a set edilmesi:
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setSurname(userRegisterRequest.getSurname());
        user.setEmail(userRegisterRequest.getEmail());
        user.setSubscriptionType(userRegisterRequest.getSubscriptionType());

        //Şifrenin hash algoritması ile db'ye kaydedilmesini sağlar.
        user.setPassword(encryptor.encryptGivenPassword(userRegisterRequest.getEmail(), userRegisterRequest.getPassword()));

        //User'ı db'ye kaydeder:
        userRepository.save(user);

        //Ödeme yapılmasını sağlar.
        Payment payment = paymentClient.createPayment(preparePaymentInfo(user));
        log.info(payment.toString());

        return REGISTER_MESSAGE;

    }

    //Kullanıcı sisteme login olur:
    public String login(UserLoginRequest request){
           request.setPassword(encryptor.encryptGivenPassword(request.getEmail(), request.getPassword()));
           boolean isExists = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword()).isPresent();
           if(isExists) {
               return LOGIN_MESSAGE;

           }else{
               return USERNOTFOUND_MESSAGE;
           }
    }

    //User'ın password değiştirmesini sağlayan method.
    public void changePassword(int userId, UserChangePasswordRequest userChangePasswordRequest) {
        User foundUser = userRepository.findById(userId).orElseThrow();
        foundUser.setPassword(userChangePasswordRequest.getPassword());
        userRepository.save(foundUser);
    }

    //User'ın name değiştirmesini sağlayan method.
    public void changeName(int userId, UserChangeNameRequest userChangeNameRequest) {
        User foundUser = userRepository.findById(userId).orElseThrow();
        foundUser.setName(userChangeNameRequest.getName());
        userRepository.save(foundUser);
    }

    //User'ların hepsini getiren method.
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    //User'ların email adreslerini getiren method.
    public List<String> getAllEmails(){
        return getAllUsers().stream().map(u -> u.getEmail()).toList();
    }

    //Verilen id'ye sahip user'ın sistemde olup olmadığını dönen method.
    public boolean userExists(int userId){
        return userRepository.existsById(userId);
    }

    //Verilen id'ye sahip user'ı getiren method.
    public User findById(int userId){
        return userRepository.findById(userId).orElseThrow();
    }

    //Kullanıcının üyelik tipine göre ödeme bilgisini getiren method.
    public Payment preparePaymentInfo(User user){
        Payment payment = new Payment();
        BigDecimal amount = subscriptionPriceRepository.findBySubscriptionType(user.getSubscriptionType()).getAmount();
        payment.setAmount(amount);
        payment.setUserId(user.getId());
        payment.setCurrencyType(CurrencyType.TL);
        payment.setPaymentDate(LocalDateTime.now());
        return payment;
    }

    @Autowired
    public UserService(AmqpTemplate rabbitTemplate, PaymentClient paymentClient, Encryptor encryptor, UserRepository userRepository, SubscriptionPriceRepository subscriptionPriceRepository, MovieService movieService, SubscriptionPriceService subscriptionPriceService) {
        this.subscriptionPriceRepository=subscriptionPriceRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.paymentClient=paymentClient;
        this.encryptor = encryptor;
        this.userRepository = userRepository;
        this.movieService=movieService;
        this.subscriptionPriceService=subscriptionPriceService;
    }

    @PostConstruct
    public void init(){
        subscriptionPriceService.addSubscriptionPrices();
        movieService.addMovies();
    }

}
