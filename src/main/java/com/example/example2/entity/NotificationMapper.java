package com.example.example2.entity;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationMapper {


    public NotificationDto mapToDto(NotificationEntity entity){
        return NotificationDto.builder()
               // .date_create(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))))
                .date_notification(entity.getDate_notification())
                .build();
    }

    public NotificationEntity mapToEntity(NotificationDto dto){


        return NotificationEntity.builder()
        //        .date_create(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))))
                .date_notification(dto.getDate_notification())
                .build();
    }
}
