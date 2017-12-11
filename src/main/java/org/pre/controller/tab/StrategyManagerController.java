package org.pre.controller.tab;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;
import org.pre.controller.util.CellUtils;
import org.pre.model.StrategyModel;
import org.pre.pojo.Strategy;
import org.pre.util.DateUtils;
import org.pre.util.ProgressStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.lang.System.out;

public class StrategyManagerController {

    @FXML
    private TableView<Strategy> tableStrategyManager;
    @FXML
    private TableColumn<Strategy, Integer> idColumn;
    @FXML
    private TableColumn<Strategy, String> underlyingColumn;
    @FXML
    private TableColumn<Strategy, String> typeColumn;
    @FXML
    private TableColumn<Strategy, String> parameterColumn;
    @FXML
    private TableColumn<Strategy, LocalDate> fromDateColumn;
    @FXML
    private TableColumn<Strategy, LocalDate> toDateColumn;
    @FXML
    private TableColumn<Strategy, Integer> numberOfStrategiesColumn;
    @FXML
    private TableColumn<Strategy, String> statusColumn;
    @FXML
    private TableColumn<Strategy, LocalDateTime> timestampColumn;
    @FXML
    private TableColumn<Strategy, Boolean> checkBoxColumn;
    @FXML
    private Button showStrategyResultsBtn;


    private final StrategyModel strategyModel;


    public StrategyManagerController(StrategyModel strategyModel){
        this.strategyModel = strategyModel;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        underlyingColumn.setCellValueFactory(cellData -> cellData.getValue().underlyingProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().parameterProperty());
        fromDateColumn.setCellValueFactory(cellData -> cellData.getValue().fromDateProperty());
        toDateColumn.setCellValueFactory(cellData -> cellData.getValue().toDateProperty());
        numberOfStrategiesColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty().asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        timestampColumn.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
        checkBoxColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
        // Spezial Fälle, Umwandlung LocalDate in Customized Format
        checkBoxColumn.setCellFactory(param -> new CheckBoxTableCell<>());
        fromDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        toDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        timestampColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedTimestampFormat()));
        tableStrategyManager.setRowFactory(tv -> {
            TableRow<Strategy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty() && (row.getItem().getStatus().equals(ProgressStatus.DONE.toString())))) {
                    Strategy rowData = row.getItem();
                    strategyModel.showResults(rowData);


                } else if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Notifications.create().title("Strategy Task not completed").text("Please wait till Strategy Task is completed").hideAfter(Duration.seconds(30)).showInformation();
                }
            });
            return row;
        });
        tableStrategyManager.setItems(strategyModel.getStrategyList());
        TableFilter.forTableView(tableStrategyManager).apply();
    }


    @FXML
    private void ShowStrategyResults(ActionEvent event) {
        int count = 0;
        for (Strategy item : strategyModel.getStrategyList()){
            if(item.isChecked() && item.getStatus().equals(ProgressStatus.DONE.name())){
                count += 1;
                strategyModel.showResults(item);
            }else if(item.isChecked()){
                Notifications.create().title(item.getUnderlying() + " is not valid!").text("You have to check a valid Strategy with Status DONE").showInformation();
                count += 1;
            }
    }
    if(count == 0){
        Notifications.create().title("No checked Strategy").text("You have to check a valid Strategy with Status DONE").showInformation();}
    }


    @FXML
    private void RemoveStrategy(ActionEvent event) {
        int count = 0;
        for (Strategy item : strategyModel.getStrategyList()){
            if(item.isChecked() && (item.getStatus().equals(ProgressStatus.DONE.name())|| item.getStatus().equals(ProgressStatus.FAILED.name()))){
                count += 1;
                strategyModel.removeStrategy(item);
            }else if(item.isChecked()){
                Notifications.create().title(item.getUnderlying() + " is not valid!").text("You have to check a valid Strategy with Status DONE or FAILED").showInformation();
                count += 1;
            }
        }
        if(count == 0){
            Notifications.create().title("No checked Strategy").text("You have to check a valid Strategy with Status DONE or FAILED").showInformation();}
    }



}



