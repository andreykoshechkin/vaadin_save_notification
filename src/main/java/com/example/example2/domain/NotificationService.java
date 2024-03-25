package com.example.example2.domain;

import com.example.example2.entity.NotificationDto;
import com.example.example2.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NotificationService {
    @Autowired
    private Notification notification;


    public Map<LocalDateTime, LocalDateTime> sendEmail(NotificationDto dto) {
        Map<LocalDateTime, LocalDateTime> noty = new HashMap<>();

        Optional.ofNullable(dto.getDateExist())
                .ifPresent(dateExist -> {
                    noty.put(dateExist, null);
                    notification.sendMail(dateExist);
                });

        Optional.ofNullable(dto.getDateRepeated())
                .map(dateRepeated -> {
                    noty.put(dto.getDateExist(), dateRepeated);
                    return dateRepeated;
                })
                .ifPresent(notification::sendMail);

        return noty;
    }
}


///*Optional.ofNullable(dto.getDateExist())
//                .ifPresent(notification::sendMail);
//
//        Optional.ofNullable(dto.getDateRepeated())
//                .ifPresent(notification::sendMail);
//   */
