package org.pre.model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.dao.DataDAO;
import org.pre.dao.DataSetDAO;
import org.pre.dao.ResultDAO;
import org.pre.dao.StrategyDAO;
import org.pre.math_model.MvaStrategiesSolver;
import org.pre.math_model.MvaStrategy;
import org.pre.pojo.Data;

import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import org.pre.util.ProgressStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;

public class StrategyModel {

    private Executor exec;


    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<Strategy> strategyList;

    public StrategyModel(){
        strategyList = FXCollections.observableArrayList();
    }


    @PostConstruct
    public void init(){
        Task<ObservableList<Strategy>> loadTask = new Task<ObservableList<Strategy>>(){
            @Override
            public ObservableList<Strategy> call() throws SQLException {
                StrategyDAO newAccess = new StrategyDAO();
                return newAccess.getStrategyList();
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
        });
        loadTask.setOnSucceeded(e -> strategyList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    public ObservableList<Strategy> getStrategyList() {

        return strategyList;
    }

    public void addStrategy(Strategy strategy) {
        Task<Strategy> loadTask = new Task<Strategy>(){
            @Override
            public Strategy call() throws Exception {
                // Speichern des Objekts mit Running Status in DB und danach in Tabelle publishen.
                // Status auf Running setzen
                strategy.setStatus(ProgressStatus.RUNNING.toString());
                StrategyDAO strategyDAO = new StrategyDAO();
                Strategy currentStrategy = strategyDAO.insertStrategy(strategy);
                Platform.runLater(() -> strategyList.addAll(currentStrategy));
                DataDAO dataDAO = new DataDAO();
                // Data holen
                List<Data> dataList = dataDAO.getDataList(strategy.getDataSet_id());
                MvaStrategiesSolver mvaStrategiesSolver = new MvaStrategiesSolver(dataList, currentStrategy.getParameter(), currentStrategy.getId());

                List<Result> resultList = mvaStrategiesSolver.getResultList();
                ResultDAO resultDAO = new ResultDAO();
                resultDAO.insertResultList(resultList);

                Platform.runLater(() -> currentStrategy.setSize(resultList.size()));
                Platform.runLater(() -> currentStrategy.setStatus(ProgressStatus.DONE.toString()));
                strategyDAO.updateStrategy(currentStrategy);


                return currentStrategy;
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
            Notifications.create().title("Error:").text(exception.getMessage()).hideAfter(Duration.minutes(5)).showError();
        });
        loadTask.setOnSucceeded(event -> Notifications.create().title("Import was successful").text(loadTask.getValue().getUnderlying()).hideAfter(Duration.minutes(2)).showInformation());
        exec.execute(loadTask);
    }



}
