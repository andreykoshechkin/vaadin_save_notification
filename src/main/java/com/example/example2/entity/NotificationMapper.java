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

    public NotificationDto mapToDto(NotificationEntity entity) {
        return NotificationDto.builder()
                .id(entity.getId())

                .dateExist(entity.getDateExist())

                .build();
    }

    public NotificationEntity mapToEntity(NotificationDto dto) {


        return NotificationEntity.builder()
                .id(dto.getId())
                .dateExist(LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER)))


                .build();
    }
}
