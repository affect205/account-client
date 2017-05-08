package com.alexside.client.gui.tabs;

import com.alexside.client.dto.AccountDTO;
import com.alexside.client.event.AccountSearchEvent;
import com.alexside.client.gui.components.AccountSearch;
import com.alexside.client.service.BankService;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.alexside.client.config.AppConfig.ACCOUNT_SEARCH;
import static com.alexside.client.utils.CurrencyUtils.toRub;

/**
 * Created by Alex on 08.05.2017.
 */
@Component
public class AccountTab extends Tab
        implements ApplicationListener<AccountSearchEvent> {

    @Autowired
    @Qualifier(ACCOUNT_SEARCH)
    private AccountSearch accountSearch;

    @Autowired
    private BankService bankService;

    private Label balanceValue;

    public AccountTab() {
        super();
        setText("Счет");
        setClosable(false);
    }

    @PostConstruct
    public void onInit() {
        Label balanceLbl = new Label("БАЛАНС:  ");
        balanceValue = new Label("0 руб.");
        HBox content = new HBox(balanceLbl, balanceValue);

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(16);
        root.getChildren().add(accountSearch);
        root.getChildren().add(content);

        setContent(root);
    }

    @Override
    public void onApplicationEvent(AccountSearchEvent event) {
        AccountDTO account = bankService.getAccountByNumber(event.getAccountNumber());
        if (account != null) {
            balanceValue.setText(toRub(account.getBalance()));
        } else balanceValue.setText("Ошибка. Нет данных.");
    }
}
