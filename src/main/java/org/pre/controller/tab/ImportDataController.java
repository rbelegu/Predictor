package org.pre.controller.tab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.controller.util.CellUtils;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;
import org.pre.util.ProgressStatus;

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
        // Symbol Textfeld alles gross schreiben
        underlyingTextField.textProperty().addListener((ov, oldValue, newValue) -> {
            underlyingTextField.setText(newValue.toUpperCase());
        });


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
        toDatePicker.setDayCellFactory(CellUtils.getDatePickerRestriction());
        fromDatePicker.setDayCellFactory(CellUtils.getDatePickerRestriction());



    }


    @FXML
    private void ImportData(ActionEvent event)  {
        if (toDatePicker.getValue().isAfter(LocalDate.now()) || fromDatePicker.getValue().isAfter(LocalDate.now())){
            Notifications.create().title("Error: Future Date(s)").text("Your date(s) are in the future! Please adjust your date(s).").hideAfter(Duration.minutes(5)).showError();
        } else {
            DataSet dataSet = new DataSet();
            dataSet.setUnderlying(underlyingTextField.getText());
            dataSet.setFromDate(fromDatePicker.getValue());
            dataSet.setToDate(toDatePicker.getValue());
            dataSet.setStatus(ProgressStatus.RUNNING.toString());
            dataSet.setTimestamp(LocalDateTime.now());
            dataSetModel.addDataSet(dataSet);
        }

    }


}
