package com.example.example2.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

       @Query("SELECT entity " +
              "FROM NotificationEntity entity " +
              "WHERE YEAR(entity.date_notification) = YEAR(current_timestamp)\n" +
              "  AND MONTH(entity.date_notification) = MONTH(current_timestamp)\n" +
              "  AND DAY(entity.date_notification) = DAY(current_timestamp)\n" +
              "  AND HOUR(entity.date_notification) = HOUR(current_timestamp)\n" +
              "  AND MINUTE(entity.date_notification) = MINUTE(current_timestamp)")
    List<NotificationEntity> get1();




/*       @Query("select en FROM NotificationEntity  en WHERE en.date_notification = (select trunc(current date ) from )")
    List<NotificationEntity> get2();*/
}
