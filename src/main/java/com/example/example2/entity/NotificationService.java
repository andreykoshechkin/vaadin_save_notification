package com.example.example2.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public boolean save(NotificationDto dto) {
        return Optional.ofNullable(dto)
                .map(it -> {
                    NotificationEntity notificationEntity = notificationMapper.mapToEntity(it);
                    notificationRepository.saveAndFlush(notificationEntity);
                    return true;
                }).orElse(false);
    }

    public List<NotificationDto> findAll(){
        return notificationRepository.findAll().stream().map(notificationMapper::mapToDto).toList();
    }
}

