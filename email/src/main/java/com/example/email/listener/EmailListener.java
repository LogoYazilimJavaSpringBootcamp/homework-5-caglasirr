package com.example.email.listener;

import com.example.email.Dto.EmailDto;
import com.example.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "movie.emailserver.port=8082")
    public void emailListener(EmailDto emailDto){
        log.info("email address: {}", emailDto.getToEmail());
        emailService.sendEmail(emailDto);
    }

//    @Autowired
//    private EmailSenderService senderService;
//
//    @RabbitListener(queues = "movie.emailserver.port=8082")
//    public void emailListener(EmailDto emailDto) {
//        log.info("email address: {}", emailDto.getEmail());
//        senderService.sendEmail(emailDto.getToEmail(), emailDto.getSubject(), emailDto.getBody());
//    }


}