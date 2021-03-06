package org.pre.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.factory.DAOFactory;
import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ResultModel {
    private Executor exec;


    private ObservableList<Result> resultList;
    private Strategy strategy;
    private DAOFactory daoFactory;

    public ResultModel(Strategy strategy){
        this.strategy = strategy;
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        resultList = FXCollections.observableArrayList();
        setExecutor();
        setResultList();
    }

    private void setExecutor(){
        exec = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
    }

    private void setResultList(){
        Task<ObservableList<Result>> loadTask = new Task<ObservableList<Result>>(){
            @Override
            public ObservableList<Result> call() throws SQLException {
                ResultDAO resultDAO = daoFactory.getResultDAO();
                return resultDAO.getResultList(strategy.getId());
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
            Notifications.create().title("Oops - Database:").text("There is a problem with your Database! Check your settings!").hideAfter(Duration.minutes(2)).showError();
        });
        loadTask.setOnSucceeded(e -> resultList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }


    public ObservableList<Result> getResultList() {
        return resultList;
    }



}
