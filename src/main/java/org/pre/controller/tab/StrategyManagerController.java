package org.pre.controller.tab;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.StageStyle;
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
import java.util.Iterator;
import java.util.Optional;

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
        for (Iterator<Strategy> strategyListIterator = strategyModel.getStrategyList().listIterator(); strategyListIterator.hasNext();){
            Strategy item= strategyListIterator.next();
            if(item.isChecked() && (item.getStatus().equals(ProgressStatus.DONE.name())|| item.getStatus().equals(ProgressStatus.FAILED.name()))){
                count += 1;
                strategyModel.removeStrategy(item);
                strategyListIterator.remove();
            }else if(item.isChecked() && (item.getStatus().equals(ProgressStatus.RUNNING.name()))){
                count += 1;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Information " + item.getUnderlying());
                alert.setHeaderText("Are you sure to remove " + "\n" + "the following running Strategy?");
                alert.setContentText(item.getUnderlying() +
                        "\n" + item.getParameter());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    strategyModel.removeStrategy(item);
                    strategyListIterator.remove();
                }

            }
                else if(item.isChecked()){
                Notifications.create().title(item.getUnderlying() + " is not valid!").text("You have to check a valid Strategy with Status DONE or FAILED").showInformation();
                count += 1;
            }
        }
        if(count == 0){
            Notifications.create().title("No checked Strategy").text("You have to check a valid Strategy with Status DONE or FAILED").showInformation();}
    }



}



