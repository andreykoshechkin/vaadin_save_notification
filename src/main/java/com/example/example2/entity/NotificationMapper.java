package com.example.example2.entity;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {


    public NotificationDto mapToDto(NotificationEntity entity){
        return NotificationDto.builder()
                .build();
    }

    public NotificationEntity mapToEntity(NotificationDto dto){
        return NotificationEntity.builder()
                .date_create(dto.getDate_notification())
                .date_notification(dto.getDate_notification())
                .build();
    }
}
