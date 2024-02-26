package com.example.example2.view;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Route("rus")
public class TimeZoneRussia extends VerticalLayout {


    public TimeZoneRussia() {


        // Получаем список доступных часовых поясов для России
        List<String> russianTimeZones = getRussianTimeZones();

        // Создаем выпадающий список для выбора часового пояса
        ComboBox<String> timeZoneComboBox = new ComboBox<>("Select Timezone");
        timeZoneComboBox.setItems(russianTimeZones);
        //timeZoneComboBox.setEmptySelectionAllowed(false); // Запрещаем пустое значение

        // Обработчик события изменения выбранного часового пояса
        timeZoneComboBox.addValueChangeListener(event -> {
            String selectedTimeZone = event.getValue();
            if (selectedTimeZone != null) {
                // Действия при выборе часового пояса
                Notification.show("Selected Timezone: " + selectedTimeZone);
            }
        });

        timeZoneComboBox.addValueChangeListener(event -> {
            String selectedTimeZone = event.getValue();
            if (selectedTimeZone != null) {
                // Получаем текущее время в выбранном часовом поясе
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone(selectedTimeZone));
                String currentTime = sdf.format(new Date());

                // Выводим текущее время
                Notification.show("Current Time in " + selectedTimeZone + ": " + currentTime);
            }
        });

    add(timeZoneComboBox);
    }

    // Метод для получения списка часовых поясов по России
    private List<String> getRussianTimeZones() {
        List<String> russianTimeZones = new ArrayList<>();
        for (String timeZoneId : TimeZone.getAvailableIDs()) {
            if (timeZoneId.startsWith("Europe/Moscow") || timeZoneId.startsWith("Asia/Yekaterinburg") ||
                timeZoneId.startsWith("Asia/Omsk") || timeZoneId.startsWith("Asia/Novosibirsk") ||
                timeZoneId.startsWith("Asia/Krasnoyarsk") || timeZoneId.startsWith("Asia/Irkutsk") ||
                timeZoneId.startsWith("Asia/Yakutsk") || timeZoneId.startsWith("Asia/Vladivostok") ||
                timeZoneId.startsWith("Asia/Sakhalin") || timeZoneId.startsWith("Asia/Kamchatka")) {
                russianTimeZones.add(timeZoneId);
            }
        }
        return russianTimeZones;
    }
}