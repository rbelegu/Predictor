package org.pre.controller.tab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.pre.dao.DataDAO;
import org.pre.dao.DataSetDAO;
import org.pre.model.DataSetModel;
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
    private final DataSetModel dataSetModel;

    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    public ImportDataController(DataSetModel dataSetModel){
        this.dataSetModel = dataSetModel;
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
        bernd.setTimestamp(new Timestamp(System.currentTimeMillis()));
        dataSetModel.addDataSet(bernd);

    }

}
