package com.example.example2.view;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;


@Route("example")
public class ExampleView extends VerticalLayout {
    public ExampleView() {
        DatePicker notification = new DatePicker("Выберите дату");


        DatePicker notificationRepeat = new DatePicker("Дата повторного напоминания");
        //notificationRepeat.setRequiredIndicatorVisible(true);
        notificationRepeat.setEnabled(false);

        Checkbox repeatCheckbox = new Checkbox("Повторное напоминание");
        repeatCheckbox.setValue(false); // Изначально флажок не выбран


        notification.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            if (selectedDate == null) {
                repeatCheckbox.setValue(false); // Если значение DatePicker стало null, устанавливаем флажок в false
                notificationRepeat.setValue(null);

            } else if (repeatCheckbox.getValue()) {
                notificationRepeat.setValue(selectedDate.plusDays(1L));
            }
        });


        repeatCheckbox.addValueChangeListener(cl -> {
            if (!cl.getValue()) {
                notificationRepeat.setValue(null);
                notificationRepeat.setEnabled(false);
            } else if (notification.getValue() == null) {
                // Если дата в поле "Выберите дату" не установлена
                repeatCheckbox.setValue(false); // Сбрасываем значение флажка
                Notification.show("Чтобы воспользоваться функцией, вы должны установить базовое время напоминания");
            } else {
                // Устанавливаем новую дату в поле "Дата повторного напоминания"
                notificationRepeat.setEnabled(true);
                notificationRepeat.setValue(notification.getValue().plusDays(1L));
            }
        });


        add(notification, notificationRepeat, repeatCheckbox);
    }
}
