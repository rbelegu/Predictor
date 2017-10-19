package org.pre.controller.tab;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.table.TableFilter;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;

import java.sql.Date;
import java.sql.Timestamp;


public class DataManagerController {

    @FXML
    private TableView tableDataManager;
    @FXML
    private TableColumn<DataSet, Integer> idColumn;
    @FXML
    private TableColumn<DataSet, String> underlyingColumn;
    @FXML
    private TableColumn<DataSet, Date> fromDateColumn;
    @FXML
    private TableColumn<DataSet, Date> toDateColumn;
    @FXML
    private TableColumn<DataSet, Integer> numberOfDatapointsColumn;
    @FXML
    private TableColumn<DataSet, String> statusColumn;
    @FXML
    private TableColumn<DataSet, Timestamp> timestampColumn;


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
        tableDataManager.setItems(dataSetModel.getDataSetList());
        TableFilter filter = new TableFilter(tableDataManager);
    }

}
