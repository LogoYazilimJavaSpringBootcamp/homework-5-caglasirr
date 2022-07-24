package com.example.email.service;

import com.example.email.dto.EmailDto;
import com.example.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    public void sendEmail(EmailDto emailDto){
        emailRepository.save(emailDto);
    }

}
