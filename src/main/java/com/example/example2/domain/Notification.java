package com.example.example2.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public interface Notification {

    void sendMail(LocalDateTime localDateTime);
    void sendMail(LocalDateTime dateExist, LocalDateTime dateRepeated);
}
