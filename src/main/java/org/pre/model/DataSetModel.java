package org.pre.model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.dao.itf.DataDAO;

import org.pre.dao.factory.DAOFactory;
import org.pre.dao.itf.DataSetDAO;
import org.pre.pojo.Data;
import org.pre.pojo.DataSet;
import org.pre.util.CSVReader;
import org.pre.util.ProgressStatus;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;


public class DataSetModel {
    private Executor exec;
    private DAOFactory daoFactory;

    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<DataSet> dataSetList;


    public DataSetModel(){
        dataSetList = FXCollections.observableArrayList();
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    }

    @PostConstruct
    public void init(){
        Task<ObservableList<DataSet>> loadTask = new Task<ObservableList<DataSet>>(){
            @Override
            public ObservableList<DataSet> call() throws SQLException {
                DataSetDAO dataSetDAO = daoFactory.getDataSetDAO();
                return dataSetDAO.getDataSetList();
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
        });
        loadTask.setOnSucceeded(e -> dataSetList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    public ObservableList<DataSet> getDataSetList() {
        return dataSetList;
    }

    public void addDataSet(DataSet dataSet, String csvPath){
        Task<DataSet> loadTask = new Task<DataSet>(){
            @Override
            public DataSet call() throws Exception {
                // Speichern des Objekts mit Running Status in DB und danach in Tabelle publishen.

                DataDAO dataDAO = daoFactory.getDataDAO();
                DataSetDAO dataSetDAO = daoFactory.getDataSetDAO();
                DataSet currentDataSet = dataSetDAO.insertDataSet(dataSet);
                Platform.runLater(() -> dataSetList.addAll(currentDataSet));
                List<Data> dataList;
                try {
                    dataList = CSVReader.getDataListFromCsv(csvPath, currentDataSet.getId());
                    Platform.runLater(() -> currentDataSet.setDatapoints(dataList.size()));
                    Platform.runLater(() -> currentDataSet.setFromDate(dataList.get(0).getRateDate()));
                    Platform.runLater(() -> currentDataSet.setToDate((dataList.get(dataList.size()-1).getRateDate())));
                   dataDAO.insertDataList(dataList);
                    Platform.runLater(() -> currentDataSet.setStatus(ProgressStatus.DONE.toString()));
                    dataSetDAO.updateDataSet(currentDataSet);
                }catch(Exception e){
                    Platform.runLater(() -> currentDataSet.setStatus(ProgressStatus.FAILED.toString()));
                      dataSetDAO.updateDataSet(currentDataSet);
                      throw e;
                }
                return currentDataSet;
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
