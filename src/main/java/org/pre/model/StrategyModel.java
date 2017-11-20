package org.pre.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.dao.DataDAO;
import org.pre.dao.ResultDAO;
import org.pre.math_model.MvaStrategy;
import org.pre.pojo.Data;
import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class StrategyModel {

    private Executor exec;


    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<Strategy> strategyList;

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


    public StrategyModel(){
        strategyList = FXCollections.observableArrayList();
    }
}
