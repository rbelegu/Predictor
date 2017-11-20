package org.pre.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;

import org.pre.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDAO {
    private Database database;
    //Tabelle und Spalten name
    private final static String TABLE_NAME = "result";
    private final static String ID = "id";
    private final static String STRATEGY_ID = "strategy_id";
    private final static String PARAMETER = "parameter";
    private final static String AVERAGE_YIELD = "average_yield";
    private final static String ACCUMULATED_PL = "accumulated_pl";
    private final static String AVERAGEPL_VOL = "averagepl_vol";
    private final static String COUNT_PROFIT_TRADES = "count_profit_trades";
    private final static String COUNT_LOSS_TRADES = "count_lost_trades";
    private final static String MAX_PROFIT_TRADE = "max_profit_trade";
    private final static String MAX_LOSS_TRADE = "max_LOSS_trade";
    private final static String TIMESTAMP = "timestamp";


    @Autowired
    public void setDatabase(Database database){
        this.database = database;
    }

    /**
     * BLA BLA
     */
    public Result insertStrategy(Result strategy){
        Result test = new Result();
     return test;
    }

    public ObservableList<Result> getResultList() throws SQLException {
        ObservableList<Result> resultList = FXCollections.observableArrayList();
        Connection conn = database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
        try {
            String insertStatement = "SELECT " + ID + ", " + STRATEGY_ID + ", " + PARAMETER + ", " + AVERAGE_YIELD + ", " +
                    ACCUMULATED_PL + ", " + AVERAGEPL_VOL + ", " + COUNT_PROFIT_TRADES + ", " + COUNT_LOSS_TRADES + ", " + MAX_PROFIT_TRADE + ", "
                    + MAX_LOSS_TRADE + ", " + TIMESTAMP + " FROM " + TABLE_NAME;
            prestmt = conn.prepareStatement(insertStatement);
            resultSet = prestmt.executeQuery();
            conn.commit();
            while (resultSet.next()){
                //Resultat Zwilenweise Auslesen und neus EMail-Objekt erstellen
                resultList.add(new Result(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getDouble(9),
                        resultSet.getDouble(10),
                        resultSet.getTimestamp(11).toLocalDateTime()));
            }
        }  finally {
            conn.close();
        }
        return resultList;
    }
}
