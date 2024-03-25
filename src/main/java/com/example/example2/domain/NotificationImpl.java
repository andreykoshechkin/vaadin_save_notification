package com.example.example2.domain;

import com.example.example2.entity.NotificationDto;
import com.example.example2.entity.NotificationEntity;
import com.example.example2.entity.NotificationMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationImpl implements Notification{

    private final EntityManager entityManager;
    private final NotificationMapper notificationMapper;
    @Override
    public void sendMail(LocalDateTime localDateTime) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setDateExist(localDateTime);
        entityManager.persist(notificationEntity);
        entityManager.flush();
    }

    @Override
    public void sendMail(LocalDateTime dateExist, LocalDateTime dateRepeated) {
        NotificationEntity notificationEntity = new NotificationEntity();
       // notificationEntity.setDateRepeated(dateRepeated);
        entityManager.persist(notificationEntity);
        entityManager.flush();
    }


}
