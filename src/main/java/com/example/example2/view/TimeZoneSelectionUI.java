
package com.example.example2.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.theme.Theme;

import java.util.Arrays;
import java.util.TimeZone;

@Route("time")
public class TimeZoneSelectionUI extends VerticalLayout {

    public TimeZoneSelectionUI() {


        // Получаем список доступных часовых поясов
        String[] timeZoneIds = TimeZone.getAvailableIDs();

        // Создаем выпадающий список для выбора часового пояса

        ComboBox<String> timeZoneComboBox = new ComboBox<>("Select Timezone");
        timeZoneComboBox.setItems(Arrays.asList(timeZoneIds));

        //timeZoneComboBox.setEmptySelectionAllowed(false); // Запрещаем пустое значение
        //timeZoneComboBox.setSelectedItem(TimeZone.getDefault().getID()); // Устанавливаем значение по умолчанию

        // Обработчик события изменения выбранного часового пояса
        timeZoneComboBox.addValueChangeListener(event -> {
            String selectedTimeZone = event.getValue();
            if (selectedTimeZone != null) {
                // Действия при выборе часового пояса
                Notification.show("Selected Timezone: " + selectedTimeZone);
            }
        });

        add(timeZoneComboBox);
    }

}
