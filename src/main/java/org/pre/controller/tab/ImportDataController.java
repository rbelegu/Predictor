package org.pre.controller.tab;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.pre.dao.DataDAO;
import org.pre.dao.DataSetDAO;
import org.pre.pojo.Data;
import org.pre.pojo.DataSet;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

import java.util.List;
import java.util.concurrent.Executor;

public class ImportDataController {

    private Executor exec;

    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }


    @FXML
    private void ImportData(ActionEvent event) throws SQLException {
        System.out.println("neuer Test");


        DataSet bernd = new DataSet();
        bernd.setDatapoints(5);
        bernd.setStatus("TEST");
        bernd.setUnderlying("GBPFNZD");
        bernd.setFromDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        bernd.setToDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        bernd.setCreationTimestamp(new Timestamp(System.currentTimeMillis()));

        int test;

        //DataSetDAO test4 = new DataSetDAO();
        Task<Integer> loadTask = new Task<Integer>(){
            @Override
            public Integer call() throws SQLException{
                DataSetDAO test5 = new DataSetDAO();
                return test5.addDataSet(bernd);
            }
        };
        loadTask.setOnFailed(evt -> loadTask.getException().printStackTrace());
        loadTask.setOnSucceeded(evt -> System.out.print(loadTask.getValue()));
        exec.execute(loadTask);



    }

}
