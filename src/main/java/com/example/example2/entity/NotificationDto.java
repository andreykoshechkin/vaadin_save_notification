package com.example.example2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {

    private Long id;

    private LocalDateTime date_create;

    private LocalDateTime date_notification;

    private String timezone;
}
