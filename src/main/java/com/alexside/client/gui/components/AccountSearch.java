package com.alexside.client.gui.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;

/**
 * Created by Alex on 08.05.2017.
 */
public class AccountSearch extends GridPane {
    private TextField accountFld;
    private Button searchBtn;

    public AccountSearch(Consumer<String> searchCallback) {
        super();
        setHgap(14);
        setVgap(14);

        accountFld = new TextField();
        accountFld.setPromptText("Номер счета");

        searchBtn = new Button("Поиск");
        searchBtn.setOnAction(event -> {
            if (searchCallback != null) {
                searchCallback.accept(accountFld.getText());
            }
        });

        addRow(1, accountFld, searchBtn);
    }
}
