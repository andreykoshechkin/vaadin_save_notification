package com.example.example2.view;

import com.example.example2.entity.NotificationDto;
import com.example.example2.entity.NotificationRepository;
import com.example.example2.entity.NotificationService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;

import com.vaadin.flow.component.dialog.Dialog;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;


@Route("rus")

public class TimeZoneRussia extends VerticalLayout {

    private NotificationService notificationService;
    private Binder<NotificationDto> notificationDtoBinder;
    public TimeZoneRussia() {
        Dialog dialog = new Dialog();

        Button notification = new Button("Напоминалка");

        notification.addClickListener(event -> {
            dialog.open();
            dialog.setWidth("350px");
            dialog.setHeight("450px");


            UI.getCurrent().getSession().getBrowser().getAddress();
            Button saveNotification = new Button("Сохранить");
            DatePicker dateNotification = new DatePicker("Дата: ");

            dateNotification.setValue(LocalDate.now());
            System.out.println();
            TimePicker timeNotification = new TimePicker("Время: ");

            ComboBox<String> timeZoneComboBox = new ComboBox<>("Выберите часовой пояс");
            timeZoneComboBox.setItems(getRussianCityNames());

            timeZoneComboBox.addValueChangeListener(event1 -> {
                String cityName = event1.getValue();
                if (cityName != null) {
                    String timeZoneId = getTimeZoneIdByCityName(cityName);
                    timeNotification.setValue(LocalTime.now(ZoneId.of(timeZoneId)));
                }
            });

            dialog.add(dateNotification, timeNotification, timeZoneComboBox, saveNotification);

            saveNotification.addClickListener(save -> {
                NotificationDto notificationDto = new NotificationDto();

            });
        });

        add(notification);
    }

    private List<String> getRussianCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (RussianTimeZones timeZone : RussianTimeZones.values()) {
            cityNames.add(timeZone.getCityName());
        }
        return cityNames;
    }

    private String getTimeZoneIdByCityName(String cityName) {
        for (RussianTimeZones timeZone : RussianTimeZones.values()) {
            if (timeZone.getCityName().equals(cityName)) {
                return timeZone.getTimeZoneId();
            }
        }
        return null;
    }

    // Перечисление для хранения названий городов и соответствующих временных зон
    private enum RussianTimeZones {
        EUROPE_MOSCOW("Europe/Moscow", "Москва"),
        ASIA_YEKATERINBURG("Asia/Yekaterinburg", "Екатеринбург"),
        ASIA_OMSK("Asia/Omsk", "Омск"),
        ASIA_NOVOSIBIRSK("Asia/Novosibirsk", "Новосибирск"),
        ASIA_KRASNOYARSK("Asia/Krasnoyarsk", "Красноярск"),
        ASIA_IRKUTSK("Asia/Irkutsk", "Иркутск"),
        ASIA_YAKUTSK("Asia/Yakutsk", "Якутск"),
        ASIA_VLADIVOSTOK("Asia/Vladivostok", "Владивосток"),
        ASIA_SAKHALIN("Asia/Sakhalin", "Сахалин"),
        ASIA_KAMCHATKA("Asia/Kamchatka", "Камчатка");

        private final String cityName;
        private final String timeZoneId;

        RussianTimeZones(String timeZoneId, String cityName) {
            this.cityName = cityName;
            this.timeZoneId = timeZoneId;
        }

        public String getCityName() {
            return cityName;
        }

        public String getTimeZoneId() {
            return timeZoneId;
        }
    }
}