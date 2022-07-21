package com.example.email.repository;

import com.example.email.Dto.EmailDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailDto, Integer> {
}
