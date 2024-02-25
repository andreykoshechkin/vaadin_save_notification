package com.example.example2.view;

import com.example.example2.entity.NotificationDto;
import com.example.example2.entity.NotificationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Route("main")
public class NotificationGet extends HorizontalLayout {

    private final Dialog dialog;

    @Autowired
    private NotificationService notificationService;

    private Binder<NotificationDto> binder;

    public NotificationGet(NotificationService notificationService) {
        this.notificationService = notificationService;

        Button button = new Button("Напоминание");
        dialog = new Dialog();
        binder = new Binder<>();

        button.addClickListener(event -> {
            dialog.removeAll(); // Очистить содержимое диалога перед открытием
            dialog.open();

            DatePicker date = new DatePicker("Дата");
            date.setValue(LocalDate.now().plusDays(1L));

            TimePicker time = new TimePicker("Время");
            time.setStep(Duration.ofMinutes(1)); // Установить шаг в минуту
            time.getElement().setProperty("timeFormat", "HH:mm"); // Установить формат времени

            Button save = new Button("Сохранить");

            save.addClickListener(event2 -> {
                LocalDateTime dateTime = LocalDateTime.of(date.getValue(), time.getValue());
                NotificationDto notification = new NotificationDto();
                notification.setDate_notification(dateTime);
                notificationService.save(notification);
                Notification.show("Напоминание сохранено!");
                dialog.close(); // Закрыть диалоговое окно после сохранения
            });

            dialog.add(date, time, save);
        });

        add(button);
    }
}