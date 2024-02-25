package com.example.example2.entity;

import lombok.experimental.UtilityClass;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    LocalDateTime dateTime = LocalDateTime.now();
    LocalDateTime dateTimeWithoutMillis = dateTime.withNano(0);
    public NotificationDto mapToDto(NotificationEntity entity) {
        return NotificationDto.builder()
                .id(entity.getId())
                .date_create(LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER)))
                .date_notification(entity.getDate_notification())
                .build();
    }

    public NotificationEntity mapToEntity(NotificationDto dto) {


        return NotificationEntity.builder()
                .id(dto.getId())
                //   .date_create(LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .date_create(dateTimeWithoutMillis)

                .date_notification(dto.getDate_notification())
                .build();
    }
}
