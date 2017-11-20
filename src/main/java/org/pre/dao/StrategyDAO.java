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
        //Wieder entfernen
        Strategy test = new Strategy();
       // try {
       //     String insertStatement = "SELECT " + ID + ", " + UNDERLYING + ", " + FROM_DATE + ", " + TO_DATE + ", " +
       //             DATAPOINTS + ", " + STATUS + ", " + TIMESTAMP + " FROM " + TABLE_NAME;
       //     prestmt = conn.prepareStatement(insertStatement);
       ////     resultSet = prestmt.executeQuery();
       //     conn.commit();
        //    while (resultSet.next()){
        //        //Resultat Zwilenweise Auslesen und neus EMail-Objekt erstellen
        //        dataSetList.add(new DataSet(
         //               resultSet.getInt(1),
          //              resultSet.getString(2),
          //              resultSet.getDate(3).toLocalDate(),
          //              resultSet.getDate(4).toLocalDate(),
         //               resultSet.getInt(5),
         //               resultSet.getString(6),
          //              resultSet.getTimestamp(7).toLocalDateTime()));
     ////       }
     //   }  finally {
      //      conn.close();
      //  }
        return strategyList;
    }



}
