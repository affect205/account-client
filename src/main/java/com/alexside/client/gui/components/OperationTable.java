package com.alexside.client.gui.components;

import com.alexside.client.dto.OperationDTO;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.alexside.client.utils.CurrencyUtils.toRub;
import static com.alexside.client.utils.DateUtils.convertToDateTime;

/**
 * Created by Alex on 08.05.2017.
 */
@Component
public class OperationTable extends TableView<OperationDTO> {

    @PostConstruct
    public void init() {

        List<TableColumn<OperationDTO, ?>> columns = new ArrayList<>();

        TableColumn<OperationDTO, Integer> numCol = new TableColumn<>("#");
        numCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getNum()));
        numCol.setPrefWidth(40);
        columns.add(numCol);

        TableColumn<OperationDTO, String> dateCol = new TableColumn<>("Дата");
        dateCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(convertToDateTime(p.getValue().getDate())));
        dateCol.setPrefWidth(140);
        columns.add(dateCol);

        TableColumn<OperationDTO, String> senderCol = new TableColumn<>("Счет списания");
        senderCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getSender()));
        senderCol.setPrefWidth(160);
        columns.add(senderCol);

        TableColumn<OperationDTO, String> recipientCol = new TableColumn<>("Счет зачисления");
        recipientCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getRecipient()));
        recipientCol.setPrefWidth(160);
        columns.add(recipientCol);

        TableColumn<OperationDTO, String> amountCol = new TableColumn<>("Сумма");
        amountCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(toRub(p.getValue().getAmount())));
        amountCol.setPrefWidth(90);
        columns.add(amountCol);

        TableColumn<OperationDTO, String> descriptionCol = new TableColumn<>("Дополнительно");
        descriptionCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getDescription()));
        descriptionCol.setPrefWidth(240);
        columns.add(descriptionCol);

        getColumns().setAll(columns);
    }

    public void refreshBy(List<OperationDTO> items) {
        Platform.runLater(() -> {
            setItems(FXCollections.observableArrayList(items));
        });
    }
}
