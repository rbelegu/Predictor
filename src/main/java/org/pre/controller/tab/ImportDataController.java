package org.pre.controller.tab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;

public class ImportDataController {

    @FXML
    private TextField underlyingTextField;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Button importDateBtn;

    private Executor exec;
    private final DataSetModel dataSetModel;


    public ImportDataController(DataSetModel dataSetModel) {

        this.dataSetModel = dataSetModel;
    }


    public void initialize() {
        // Button ist nur Aktive wenn die Felder auch ausgefüllt sind!
        importDateBtn.disableProperty().bind(
                underlyingTextField.textProperty().isEmpty()
                        .or(fromDatePicker.valueProperty().isNull())
                        .or(toDatePicker.valueProperty().isNull()));

        //Prüfen ob to Datum nicht vor dem from Datum ist.
        fromDatePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (toDatePicker.getValue() != null && fromDatePicker.getValue() != null) {
                if (fromDatePicker.getValue().isAfter(toDatePicker.getValue())) {
                    toDatePicker.setValue(null);
                }
            }
        });

        toDatePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (fromDatePicker.getValue() != null && toDatePicker.getValue() != null) {
                if (fromDatePicker.getValue().isAfter(toDatePicker.getValue())) {
                    fromDatePicker.setValue(null);
                }
            }
        });

        fromDatePicker.setDayCellFactory(
                new Callback<DatePicker, DateCell>() {
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
// Must call super
                                super.updateItem(item, empty);
// Disable all future date cells
                                if (item.isAfter(LocalDate.now())) {
                                    this.setDisable(true);
                                }

// Show Weekends in blue
                                DayOfWeek day = DayOfWeek.from(item);
                                if (day == DayOfWeek.SATURDAY ||
                                        day == DayOfWeek.SUNDAY) {
                                    this.setTextFill(Color.BLUE);
                                }
                            }
                        };
                    }
                });


    }


    @FXML
    private void ImportData(ActionEvent event) throws SQLException {
        System.out.println("neuer Test");

        LocalDate today = LocalDate.now();


        DataSet bernd = new DataSet();
        bernd.setDatapoints(5);
        bernd.setStatus("TEST");
        bernd.setUnderlying(underlyingTextField.getText());
        bernd.setFromDate(LocalDate.now());
        bernd.setToDate(LocalDate.now());
        bernd.setTimestamp(LocalDateTime.now());
        dataSetModel.addDataSet(bernd);


    }


}
