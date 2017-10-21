package org.pre.controller.tab;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.controlsfx.control.table.TableFilter;
import org.pre.controller.util.DateUtils;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;


public class DataManagerController {

    @FXML
    private TableView tableDataManager;
    @FXML
    private TableColumn<DataSet, Integer> idColumn;
    @FXML
    private TableColumn<DataSet, String> underlyingColumn;
    @FXML
    private TableColumn<DataSet, LocalDate> fromDateColumn;
    @FXML
    private TableColumn<DataSet, LocalDate> toDateColumn;
    @FXML
    private TableColumn<DataSet, Integer> numberOfDatapointsColumn;
    @FXML
    private TableColumn<DataSet, String> statusColumn;
    @FXML
    private TableColumn<DataSet, LocalDateTime> timestampColumn;


    private final DataSetModel dataSetModel;


    public DataManagerController(DataSetModel dataSetModel){
        this.dataSetModel = dataSetModel;
    }

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        underlyingColumn.setCellValueFactory(cellData -> cellData.getValue().underlyingProperty());
        fromDateColumn.setCellValueFactory(cellData -> cellData.getValue().fromDateProperty());
        toDateColumn.setCellValueFactory(cellData -> cellData.getValue().toDateProperty());
        numberOfDatapointsColumn.setCellValueFactory(cellData -> cellData.getValue().datapointsProperty().asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        timestampColumn.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
        // Spezial Fälle, Umwandlung LocalDate in Customized Format
        fromDateColumn.setCellFactory(DateUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        toDateColumn.setCellFactory(DateUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        timestampColumn.setCellFactory(DateUtils.getDateCell(DateUtils.getCustomizedTimestampFormat()));
        tableDataManager.setItems(dataSetModel.getDataSetList());
        TableFilter filter = new TableFilter(tableDataManager);
    }






}


