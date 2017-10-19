package org.pre.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.dao.DataSetDAO;
import org.pre.db.Database;
import org.pre.pojo.DataSet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.concurrent.Executor;

public class DataSetModel {
    private Executor exec;


    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<DataSet> dataSetList;


    public DataSetModel(){
        dataSetList = FXCollections.observableArrayList();
    }

    @PostConstruct
    public void init(){
        Task<ObservableList<DataSet>> loadTask = new Task<ObservableList<DataSet>>(){
            @Override
            public ObservableList<DataSet> call() throws SQLException {
                DataSetDAO newAccess = new DataSetDAO();
                return newAccess.getDataSetList();
            }
        };
        loadTask.setOnFailed(e -> loadTask.getException().printStackTrace());
        loadTask.setOnSucceeded(e -> dataSetList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    public ObservableList<DataSet> getDataSetList() {
        return dataSetList;
    }

    public void addDataSet(DataSet dataSet){
        Task<DataSet> loadTask = new Task<DataSet>(){
            @Override
            public DataSet call() throws Exception {
                DataSetDAO newAccess = new DataSetDAO();
                return newAccess.addDataSet(dataSet);
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
            Notifications.create().title("Oooops").text(exception.getMessage()).hideAfter(Duration.minutes(5)).showError();
        });
        loadTask.setOnSucceeded(event -> dataSetList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    public void test(){

    }
}
