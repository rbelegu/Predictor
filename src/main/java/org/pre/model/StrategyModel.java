package org.pre.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import org.pre.dao.DataDAO;
import org.pre.dao.DataSetDAO;
import org.pre.dao.ResultDAO;
import org.pre.dao.StrategyDAO;
import org.pre.math_model.MvaStrategy;
import org.pre.pojo.Data;

import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
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

    public void addStrategy(Strategy strategy) throws SQLException {
        ObservableList<Data> data;
        DataDAO DataDao = new DataDAO();
        data =  DataDao.getDataList(strategy.getDataSet_id());
        MvaStrategy test2 = new MvaStrategy(2, data);
        Result test = new Result();
        ResultDAO ResultDAO = new ResultDAO();
        Result currentDataSet = ResultDAO.insertStrategy(test);

    }



}
