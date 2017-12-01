package org.pre.dao;

import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;
import org.pre.pojo.DataSet;
import org.pre.pojo.Strategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.pre.util.DateUtils.convertSQLDateToLocalDate;


public class StrategyDAO {
    private static Database database;
    private final static String TABLE_NAME = "strategy";
    private final static String ID = "id";
    private final static String DATASET_ID = "dataset_id";
    private final static String TYPE = "type";
    private final static String PARAMETER = "parameter";
    private final static String SIZE = "size";
    private final static String STATUS = "status";
    private final static String TIMESTAMP = "timestamp";
    private final static String TABLE_NAME_DATASET = "dataset";
    private final static String UNDERLYING = "underlying";
    private final static String FROM_DATE = "from_date";
    private final static String TO_DATE = "to_date";

    @Autowired
    public void setDatabase(Database database){
        this.database = database;
    }


    /**
     * BLA BLA
     */
    public Strategy insertStrategy(Strategy strategy) throws SQLException {
        Connection conn = database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
        try {
            String insertStatement = "INSERT INTO " + TABLE_NAME +
                    "( " + DATASET_ID + ", " + TYPE + ", "
                    + PARAMETER + ", " + STATUS + ", " + SIZE + ", " + TIMESTAMP + ") VALUES ( " + "?, ?, ?, ?, ?, ? )";
            prestmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            prestmt.setInt(1, strategy.getDataSet_id());
            prestmt.setString(2, strategy.getType());
            prestmt.setString(3, strategy.getParameter());
            prestmt.setString(4, strategy.getStatus());
            prestmt.setInt(5, strategy.getSize());
            prestmt.setTimestamp(6, java.sql.Timestamp.valueOf(strategy.getTimestamp()));

            prestmt.executeUpdate();
            resultSet = prestmt.getGeneratedKeys();
            conn.commit();
            if (resultSet.next()) {
                strategy.setId(resultSet.getInt(1));
                return strategy;
            }
        }  finally {
            conn.close();
        }
        return strategy;
    }


    /**
     * BLA BLA
     */
    public void updateStrategy(Strategy strategy) throws SQLException {
        Connection conn = database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
        try {
            String insertStatement = "UPDATE " + TABLE_NAME + " SET "
                    + DATASET_ID + " = ?, "
                    + TYPE + " = ?, "
                    + PARAMETER + " = ?, "
                    + STATUS + " = ?, "
                    + SIZE + " = ?, "
                    + TIMESTAMP + " = ? WHERE "
                    + ID + " = ?";

            prestmt = conn.prepareStatement(insertStatement);
            prestmt.setInt(1, strategy.getDataSet_id());
            prestmt.setString(2, strategy.getType());
            prestmt.setString(3, strategy.getParameter());
            prestmt.setString(4, strategy.getStatus());
            prestmt.setInt(5, strategy.getSize());
            prestmt.setTimestamp(6, java.sql.Timestamp.valueOf(strategy.getTimestamp()));
            prestmt.setInt(7, strategy.getId());
            prestmt.executeUpdate();
            conn.commit();
        }  finally {
            conn.close();
        }
    }


    /**
     * BLA BLA
     */
    public ObservableList<Strategy> getStrategyList() throws SQLException {
        ObservableList<Strategy> strategyList = FXCollections.observableArrayList();
        Connection conn = database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
        try {
            String insertStatement = "SELECT " + TABLE_NAME + "." + ID + ", " + TABLE_NAME + "." + DATASET_ID + ", " + TABLE_NAME_DATASET + "." + UNDERLYING + ", " + TABLE_NAME_DATASET + "." + FROM_DATE
                    + ", " + TABLE_NAME_DATASET + "." + TO_DATE + ", " + TABLE_NAME + "." + TYPE + ", " + TABLE_NAME + "." + PARAMETER + ", "
                    + TABLE_NAME + "." + SIZE + ", " + TABLE_NAME + "." + STATUS + ", " + TABLE_NAME + "." + TIMESTAMP + " FROM " + TABLE_NAME + "," + TABLE_NAME_DATASET + " WHERE "
                    + TABLE_NAME + "." + DATASET_ID + " = " + TABLE_NAME_DATASET + "." + ID;
           prestmt = conn.prepareStatement(insertStatement);
            resultSet = prestmt.executeQuery();
           conn.commit();
            while (resultSet.next()){
               //Resultat Zwilenweise Auslesen und neues Objekt erstellenn
                Strategy strategy = new Strategy();
                strategy.setId(resultSet.getInt(1));
                strategy.setDataSet_id(resultSet.getInt(2));
                strategy.setUnderlying(resultSet.getString(3));
                strategy.setFromDate(convertSQLDateToLocalDate(resultSet.getDate(4)));
                strategy.setToDate(convertSQLDateToLocalDate(resultSet.getDate(5)));
                strategy.setType(resultSet.getString(6));
                strategy.setParameter(resultSet.getString(7));
                strategy.setSize(resultSet.getInt(8));
                strategy.setStatus(resultSet.getString(9));
                strategy.setTimestamp(resultSet.getTimestamp(10).toLocalDateTime());
                strategyList.add(strategy);
           }
       }  finally {
           conn.close();
       }
        return strategyList;
    }



}
