package org.pre.controller.tab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.pre.dao.DataSetDAO;
import org.pre.pojo.Data;
import org.pre.pojo.DataSet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

import java.util.List;

public class ImportDataController {

    @FXML
    private void ImportData(ActionEvent event) throws SQLException {
        System.out.println("neuer Test");


        DataSet bernd = new DataSet();
        bernd.setDatapoints(5);
        bernd.setStatus("TEST");
        bernd.setUnderlying("EURCHF");
        bernd.setFromDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        bernd.setToDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        bernd.setCreationTimestamp(new Timestamp(System.currentTimeMillis()));

        int test;

        //DataSetDAO test4 = new DataSetDAO();
        DataSetDAO.addDataSet(bernd);
    }

}
