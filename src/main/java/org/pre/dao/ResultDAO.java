package org.pre.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;
import org.pre.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    private final static String COUNT_LOSS_TRADES = "count_loss_trades";
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
    public boolean insertResultList(List<Result> resultList) throws SQLException {
        Connection conn = database.getConnection();
        boolean flag = false;
        PreparedStatement insertResult;
        String insertStatement = "INSERT INTO " + TABLE_NAME +
                "( " + STRATEGY_ID + ", " + PARAMETER + ", " + AVERAGE_YIELD + ", " + ACCUMULATED_PL + ", " + AVERAGEPL_VOL + ", "
                + COUNT_PROFIT_TRADES + ", " + COUNT_LOSS_TRADES + ", " + MAX_PROFIT_TRADE + ", " + MAX_LOSS_TRADE + ", " + TIMESTAMP + ") VALUES (?,?,?,?,?,?,?,?,?,?)";

        try{
            insertResult = conn.prepareStatement(insertStatement);
            for (Result result : resultList){
                insertResult.setInt(1, result.getStrategy_id());
                insertResult.setString(2,result.getParameter());
                insertResult.setDouble(3,result.getAverageYield());
                insertResult.setDouble(4,result.getAccumulatedPl());
                insertResult.setDouble(5,result.getAveragePlVol());
                insertResult.setInt(6,result.getCountProfitTrades());
                insertResult.setInt(7,result.getCountLossTrades());
                insertResult.setDouble(8,result.getMaxProfitTrade());
                insertResult.setDouble(9,result.getMaxLossTrade());
                insertResult.setTimestamp(10,java.sql.Timestamp.valueOf(result.getTimestamp()));
                insertResult.execute();
            }
            conn.commit();
            flag = true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            }catch(SQLException exc){
                e.printStackTrace();
            }
        } finally{
            conn.close();
        }
        return  flag;
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
                Result result = new Result();
                result.setId(resultSet.getInt(1));
                result.setStrategy_id(resultSet.getInt(2));
                result.setParameter(resultSet.getString(3));
                result.setAverageYield(resultSet.getDouble(4));
                result.setAccumulatedPl(resultSet.getDouble(5));
                result.setAveragePlVol(resultSet.getDouble(6));
                result.setCountProfitTrades(resultSet.getInt(7));
                result.setCountLossTrades(resultSet.getInt(8));
                result.setMaxProfitTrade(resultSet.getDouble(9));
                result.setMaxLossTrade(resultSet.getDouble(10));
                result.setTimestamp(resultSet.getTimestamp(11).toLocalDateTime());
                resultList.add(result);
            }
        }  finally {
            conn.close();
        }
        return resultList;
    }
}
