package com.example.example2.view;

import com.example.example2.entity.NotificationDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("get")
public class NotificationGet extends HorizontalLayout {

    private Dialog dialog;
    private  Grid<NotificationDto> notificationDtoGrid;


    private Binder<NotificationDto> binder;

    public NotificationGet() {


        Button button = new Button("Получить");

        notificationDtoGrid = new Grid<>(NotificationDto.class);

        button.addClickListener(event -> {

            dialog = new Dialog();
            dialog.open();

            dialog.add(notificationDtoGrid);
            notificationDtoGrid.setWidth("800px");
            notificationDtoGrid.setHeight("600px");
            add(dialog);
        });

        add(button );
    }
}
