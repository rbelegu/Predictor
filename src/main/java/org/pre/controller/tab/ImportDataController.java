package org.pre.controller.tab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.controller.util.CellUtils;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;
import org.pre.util.ProgressStatus;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;

public class ImportDataController {

    @FXML
    private TextField underlyingTextField;

    @FXML
    private TextField csvPathField;

    @FXML
    private Button csvBrowseBtn;

    @FXML
    private Button importDateBtn;

    private final DataSetModel dataSetModel;


    public ImportDataController(DataSetModel dataSetModel) {

        this.dataSetModel = dataSetModel;
    }


    public void initialize() {
        // Button ist nur Aktive wenn die Felder auch ausgefüllt sind!
        importDateBtn.disableProperty().bind(
                underlyingTextField.textProperty().isEmpty()
                        .or(csvPathField.textProperty().isEmpty()));
        // Symbol Textfeld alles gross schreiben
        underlyingTextField.textProperty().addListener((ov, oldValue, newValue) -> underlyingTextField.setText(newValue.toUpperCase()));

    }

    @FXML
    private void CsvPath(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            csvPathField.textProperty().setValue(file.toString());
        }
    }


    @FXML
    private void ImportData(ActionEvent event)  {
            DataSet dataSet = new DataSet();
            dataSet.setUnderlying(underlyingTextField.getText());
            dataSet.setStatus(ProgressStatus.RUNNING.toString());
            dataSet.setTimestamp(LocalDateTime.now());
            dataSetModel.addDataSet(dataSet, csvPathField.getText());
         }


}
