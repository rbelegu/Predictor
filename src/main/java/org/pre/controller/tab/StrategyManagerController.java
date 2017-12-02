package org.pre.controller.tab;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.table.TableFilter;
import org.pre.controller.util.CellUtils;
import org.pre.model.StrategyModel;
import org.pre.pojo.DataSet;
import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import org.pre.util.DateUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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


    private final StrategyModel strategyModel;


    public StrategyManagerController(StrategyModel strategyModel){
        this.strategyModel = strategyModel;
    }

    @FXML
    private void initialize(){
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
        checkBoxColumn.setCellFactory(param -> new CheckBoxTableCell<Strategy, Boolean>());
        fromDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        toDateColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedDateFormat()));
        timestampColumn.setCellFactory(CellUtils.getDateCell(DateUtils.getCustomizedTimestampFormat()));
        tableStrategyManager.setRowFactory(tv -> {
            TableRow<Strategy> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Strategy rowData = row.getItem();
                        try {
                            strategyModel.showResults(rowData);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            return row;
        });
        tableStrategyManager.setItems(strategyModel.getStrategyList());
        TableFilter filter = new TableFilter(tableStrategyManager);
    }




}



