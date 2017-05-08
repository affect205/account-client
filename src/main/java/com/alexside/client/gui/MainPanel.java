package com.alexside.client.gui;

import com.alexside.client.gui.tabs.AccountTab;
import com.alexside.client.gui.tabs.OperationTab;
import com.alexside.client.gui.tabs.TransferTab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Alex on 08.05.2017.
 */
@Component
public class MainPanel extends BorderPane {
    @Autowired
    private AccountTab accountTab;
    @Autowired
    private OperationTab operationTab;
    @Autowired
    private TransferTab transferTab;

    private TabPane tabPane;

    public MainPanel() {}

    @PostConstruct
    public void onInit() {
        tabPane = new TabPane();
        tabPane.getTabs().add(accountTab);
        tabPane.getTabs().add(operationTab);
        tabPane.getTabs().add(transferTab);
        setCenter(tabPane);
    }
}
