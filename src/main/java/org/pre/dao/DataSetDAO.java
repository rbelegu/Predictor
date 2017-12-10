package org.pre.dao;

import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;
import org.pre.pojo.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;

import static org.pre.util.DateUtils.convertLocalDateToSQLDate;
import static org.pre.util.DateUtils.convertSQLDateToLocalDate;


public class DataSetDAO {
    private final static String TABLE_NAME = "dataset";
    private final static String ID = "id";
    private final static String UNDERLYING = "underlying";
    private final static String FROM_DATE = "from_date";
    private final static String TO_DATE = "to_date";
    private final static String DATAPOINTS = "data_points";
    private final static String STATUS = "status";
    private final static String TIMESTAMP = "timestamp";



    /**
     * BLA BLA
     */
    public DataSet insertDataSet(DataSet dataSet) throws SQLException {
        ResultSet resultSet;
        PreparedStatement prestmt;
        try (Connection conn = Database.getConnection()) {
            String insertStatement = "INSERT INTO " + TABLE_NAME +
                    "( " + UNDERLYING + ", " + FROM_DATE + ", "
                    + TO_DATE + ", " + DATAPOINTS + ", " + STATUS + ", " + TIMESTAMP + ") VALUES ( " + "?, ?, ?, ?, ?, ? )";
            prestmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            prestmt.setString(1, dataSet.getUnderlying());
            prestmt.setDate(2, convertLocalDateToSQLDate(dataSet.getFromDate()));
            prestmt.setDate(3, convertLocalDateToSQLDate(dataSet.getToDate()));
            prestmt.setDouble(4, dataSet.getDatapoints());
            prestmt.setString(5, dataSet.getStatus());
            prestmt.setTimestamp(6, java.sql.Timestamp.valueOf(dataSet.getTimestamp()));

            prestmt.executeUpdate();
            resultSet = prestmt.getGeneratedKeys();
            conn.commit();
            if (resultSet.next()) {
                dataSet.setId(resultSet.getInt(1));
                return dataSet;
            }
        }
        return dataSet;
    }


    /**
     * BLA BLA
     */
    public void updateDataSet(DataSet dataSet) throws SQLException {
        PreparedStatement prestmt;
        try (Connection conn = Database.getConnection()) {
            String insertStatement = "UPDATE " + TABLE_NAME + " SET "
                    + UNDERLYING + " = ?, "
                    + FROM_DATE + " = ?, "
                    + TO_DATE + " = ?, "
                    + DATAPOINTS + " = ?, "
                    + STATUS + " = ?, "
                    + TIMESTAMP + " = ? WHERE "
                    + ID + " = ?";

            prestmt = conn.prepareStatement(insertStatement);
            prestmt.setString(1, dataSet.getUnderlying());
            prestmt.setDate(2, convertLocalDateToSQLDate(dataSet.getFromDate()));
            prestmt.setDate(3, convertLocalDateToSQLDate(dataSet.getToDate()));
            prestmt.setDouble(4, dataSet.getDatapoints());
            prestmt.setString(5, dataSet.getStatus());
            prestmt.setTimestamp(6, java.sql.Timestamp.valueOf(dataSet.getTimestamp()));
            prestmt.setInt(7, dataSet.getId());
            prestmt.executeUpdate();
            conn.commit();
        }
    }


    /**
     * BLA BLA
     */
    public ObservableList<DataSet> getDataSetList() throws SQLException {
        ObservableList<DataSet> dataSetList = FXCollections.observableArrayList();

        ResultSet resultSet;
        PreparedStatement prestmt;
        try (Connection conn = Database.getConnection()) {
            String insertStatement = "SELECT " + ID + ", " + UNDERLYING + ", " + FROM_DATE + ", " + TO_DATE + ", " +
                    DATAPOINTS + ", " + STATUS + ", " + TIMESTAMP + " FROM " + TABLE_NAME;
            prestmt = conn.prepareStatement(insertStatement);
            resultSet = prestmt.executeQuery();
            conn.commit();
            while (resultSet.next()) {
                //Resultat Zwilenweise Auslesen und neus EMail-Objekt erstellen
                dataSetList.add(new DataSet(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        convertSQLDateToLocalDate(resultSet.getDate(3)),
                        convertSQLDateToLocalDate(resultSet.getDate(4)),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).toLocalDateTime()));
            }
        }
        return dataSetList;

    }



}
