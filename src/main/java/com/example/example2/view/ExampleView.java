package com.example.example2.view;

import com.example.example2.domain.NotificationService;
import com.example.example2.entity.NotificationDto;
import com.example.example2.entity.NotificationEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;


@Route("example")

public class ExampleView extends VerticalLayout {

    private Binder<NotificationDto> binder;

    @Autowired
    private NotificationService notificationService;

    public ExampleView() {
        binder = new Binder<>(NotificationDto.class);

        Button button = new Button("Save");

        DateTimePicker date = new DateTimePicker("Выберите дату");


        DateTimePicker dateRepeated = new DateTimePicker("Дата повторного напоминания");
        //dateRepeated.setRequiredIndicatorVisible(true);
        dateRepeated.setEnabled(false);

        Checkbox repeatCheckbox = new Checkbox("Повторное напоминание");
        repeatCheckbox.setValue(false); // Изначально флажок не выбран


        date.addValueChangeListener(event -> {
            LocalDateTime selectedDate = event.getValue();
            if (selectedDate == null) {
                repeatCheckbox.setValue(false); // Если значение DatePicker стало null, устанавливаем флажок в false
                dateRepeated.setValue(null);

            } else if (repeatCheckbox.getValue()) {
                dateRepeated.setValue(selectedDate.plusDays(1L));
            }
        });


        repeatCheckbox.addValueChangeListener(cl -> {
            if (!cl.getValue()) {
                dateRepeated.setValue(null);
                dateRepeated.setEnabled(false);
            } else if (date.getValue() == null) {
                // Если дата в поле "Выберите дату" не установлена
                repeatCheckbox.setValue(false); // Сбрасываем значение флажка
                Notification.show("Чтобы воспользоваться функцией, вы должны установить базовое время напоминания");
            } else {
                // Устанавливаем новую дату в поле "Дата повторного напоминания"
                dateRepeated.setEnabled(true);
                dateRepeated.setValue(date.getValue().plusDays(1L));
            }
        });


        button.addClickListener(event -> {
            NotificationDto notificationDto= new NotificationDto();

            try {



                binder.forField(date)
                        .bind(NotificationDto::getDateExist, NotificationDto::setDateExist);

                binder.forField(dateRepeated)
                        .bind(NotificationDto::getDateExist, NotificationDto::setDateRepeated);

                binder.writeBean(notificationDto);

                notificationService.sendEmail(notificationDto);

            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }

        });

        add(date, dateRepeated, repeatCheckbox, button);


    }
}
