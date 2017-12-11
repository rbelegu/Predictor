package org.pre.controller.tab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;
import org.pre.controller.util.CellUtils;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;
import org.pre.util.DateUtils;
import org.pre.util.ProgressStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DataManagerController {

    @FXML
    private TableView<DataSet> tableDataManager;
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
    @FXML
    private TableColumn<DataSet, Boolean> checkBoxColumn;


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
        checkBoxColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
        // Spezial Fälle, Umwandlung LocalDate in Customized Format
        checkBoxColumn.setCellFactory(param -> new CheckBoxTableCell<DataSet, Boolean>());
        fromDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        toDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        timestampColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedTimestampFormat()));
        tableDataManager.setItems(dataSetModel.getDataSetList());
        TableFilter.forTableView(tableDataManager).apply();

    }

    @FXML
    private void RemoveDataSet(ActionEvent event) {
        int count = 0;
        for (DataSet item : dataSetModel.getDataSetList()){
            if(item.isChecked() && (item.getStatus().equals(ProgressStatus.DONE.name())|| item.getStatus().equals(ProgressStatus.FAILED.name()))){
                count += 1;
                dataSetModel.removeDataSet(item);
            }else if(item.isChecked()){
                Notifications.create().title(item.getUnderlying() + " is not valid!").text("You have to check a valid DataSet with Status DONE or FAILED").showInformation();
                count += 1;
            }
        }
        if(count == 0){
            Notifications.create().title("No checked DataSet").text("You have to check a valid DataSet with Status DONE or FAILED").showInformation();}
    }






}


