package org.pre.controller.tab;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.table.TableFilter;
import org.pre.model.StrategyModel;
import org.pre.pojo.DataSet;
import org.pre.pojo.Result;
import org.pre.pojo.Strategy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ResultAnalyserController {



    @FXML
    private TableView<Result> tableResultManager;



    @FXML
    private TableColumn<Result, String> parameterColumn;
    @FXML
    private TableColumn<Result, Double> averageYieldColumn;
    @FXML
    private TableColumn<Result,Double> accumulatedPlColumn;
    @FXML
    private TableColumn<Result, Integer> countProfitTradesColumn;
    @FXML
    private TableColumn<Result, Integer> countLossTradesColumn;
    @FXML
    private TableColumn<Result, Double> maxProfitTradeColumn;
    @FXML
    private TableColumn<Result, Double> maxLossTradeColumn;



    @FXML
    public void initialize(ObservableList<Result> results) {
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().parameterProperty());
        averageYieldColumn.setCellValueFactory(cellData -> cellData.getValue().averageYieldProperty().asObject());
        accumulatedPlColumn.setCellValueFactory(cellData -> cellData.getValue().accumulatedPlProperty().asObject());
        countProfitTradesColumn.setCellValueFactory(cellData -> cellData.getValue().countProfitTradesProperty().asObject());
        countLossTradesColumn.setCellValueFactory(cellData -> cellData.getValue().countLossTradesProperty().asObject());
        maxLossTradeColumn.setCellValueFactory(cellData -> cellData.getValue().maxLossTradeProperty().asObject());
        maxProfitTradeColumn.setCellValueFactory(cellData -> cellData.getValue().maxProfitTradeProperty().asObject());
        tableResultManager.setItems(results);
        TableFilter filter = new TableFilter(tableResultManager);

    }

}
