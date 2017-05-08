package com.alexside.client.gui.tabs;

import com.alexside.client.dto.TransferDTO;
import com.alexside.client.service.BankService;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Alex on 08.05.2017.
 */
@Component
public class TransferTab extends Tab {

    @Autowired
    private BankService bankService;

    private TextField senderFld;
    private TextField recipientFld;
    private TextField amountFld;
    private TextArea descriptionTa;
    private Button transferBtn;
    private Label statusLbl;

    public TransferTab() {
        super();
        setText("Переводы");
        setClosable(false);
    }

    @PostConstruct
    public void onInit() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(14);
        root.setVgap(14);

        senderFld = new TextField();
        recipientFld = new TextField();
        amountFld = new TextField();
        statusLbl = new Label("");
        descriptionTa = new TextArea();
        transferBtn = new Button("Выполнить");
        transferBtn.setOnAction(event -> {
            String sender = senderFld.getText();
            String recipient = recipientFld.getText();
            double amount = Double.parseDouble(amountFld.getText());
            String description = descriptionTa.getText();
            TransferDTO transfer = new TransferDTO(sender, recipient, amount, description);
            boolean success = bankService.doTransfer(transfer);
            statusLbl.setText(success ? "Выполнено" : "Ошибка");
        });

        root.addRow(1, new Label("Счет списания"), senderFld);
        root.addRow(2, new Label("Счет зачисления"), recipientFld);
        root.addRow(3, new Label("Сумма"), amountFld);
        root.addRow(4, transferBtn);
        root.addRow(5, new Label("Статус"), statusLbl);
        setContent(root);
    }
}
