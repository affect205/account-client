package com.alexside.client.gui.tabs;

import com.alexside.client.dto.OperationDTO;
import com.alexside.client.event.OperationSearchEvent;
import com.alexside.client.gui.components.AccountSearch;
import com.alexside.client.gui.components.OperationTable;
import com.alexside.client.service.BankService;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.alexside.client.config.AppConfig.OPERATION_SEARCH;

/**
 * Created by Alex on 08.05.2017.
 */
@Component
public class OperationTab extends Tab
        implements ApplicationListener<OperationSearchEvent> {

    @Autowired
    @Qualifier(OPERATION_SEARCH)
    private AccountSearch accountSearch;

    @Autowired
    private OperationTable operationTable;

    @Autowired
    private BankService bankService;

    public OperationTab() {
        super();
        setText("Операции");
        setClosable(false);
    }

    @PostConstruct
    public void onInit() {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(16);
        root.getChildren().add(accountSearch);
        root.getChildren().add(operationTable);
        setContent(root);
    }

    @Override
    public void onApplicationEvent(OperationSearchEvent event) {
        List<OperationDTO> operations = bankService.getOperationsByNumber(event.getAccountNumber());
        operationTable.refreshBy(operations);
    }
}
