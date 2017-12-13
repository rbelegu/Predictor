package org.pre.dao.impl;

import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.dao.datasource.MySqlDatasource;
import org.pre.dao.itf.StrategyDAO;
import org.pre.pojo.Strategy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.pre.util.DateUtils.convertSQLDateToLocalDate;

public class MySqlStrategyDAO implements StrategyDAO {

    private final static String TABLE_NAME = "strategy";
    private final static String CHILD_TABLE_NAME_RESULT = "result";
    private final static String CHILD_STRATEGY_ID = "strategy_id";
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


    /**
     * BLA BLA
     */
    public Strategy insertStrategy(Strategy strategy) throws SQLException {
        ResultSet resultSet;
        PreparedStatement prestmt;
        Connection conn = MySqlDatasource.getConnection();
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
        }catch(SQLException ex){
            throw new SQLException("Data Set: " +strategy.getUnderlying() + "\n" + "Strategy Type: " +strategy.getType() + "\n"
                    + "Parameter: " + strategy.getParameter(), ex);
        }finally{
            conn.close();
        }
        return strategy;
    }


    /**
     * BLA BLA
     */
    public boolean deleteStrategy(int strategy_id) throws SQLException {
        Connection conn = MySqlDatasource.getConnection();
        boolean flag = false;
        PreparedStatement prestmt;
        String deleteStatementResult = "DELETE FROM " + CHILD_TABLE_NAME_RESULT + " WHERE " + CHILD_STRATEGY_ID + " = " + strategy_id;
        String deleteStatementStrategy = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + strategy_id;

        try{
            prestmt = conn.prepareStatement(deleteStatementResult);
            prestmt.executeUpdate();
            prestmt = conn.prepareStatement(deleteStatementStrategy);
            prestmt.executeUpdate();
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

    /**
     * BLA BLA
     */
    public void updateStrategy(Strategy strategy) throws SQLException {
        PreparedStatement prestmt;
        try (Connection conn = MySqlDatasource.getConnection()) {
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
        }
    }


    /**
     * BLA BLA
     */
    public ObservableList<Strategy> getStrategyList() throws SQLException {
        ObservableList<Strategy> strategyList = FXCollections.observableArrayList();
        ResultSet resultSet;
        PreparedStatement prestmt;
        Connection conn = MySqlDatasource.getConnection();
        try  {
            String insertStatement = "SELECT " + TABLE_NAME + "." + ID + ", " + TABLE_NAME + "." + DATASET_ID + ", " + TABLE_NAME_DATASET + "." + UNDERLYING + ", " + TABLE_NAME_DATASET + "." + FROM_DATE
                    + ", " + TABLE_NAME_DATASET + "." + TO_DATE + ", " + TABLE_NAME + "." + TYPE + ", " + TABLE_NAME + "." + PARAMETER + ", "
                    + TABLE_NAME + "." + SIZE + ", " + TABLE_NAME + "." + STATUS + ", " + TABLE_NAME + "." + TIMESTAMP + " FROM " + TABLE_NAME + "," + TABLE_NAME_DATASET + " WHERE "
                    + TABLE_NAME + "." + DATASET_ID + " = " + TABLE_NAME_DATASET + "." + ID + " ORDER BY " + ID + " ASC ";
            prestmt = conn.prepareStatement(insertStatement);
            resultSet = prestmt.executeQuery();
            conn.commit();
            while (resultSet.next()) {
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
        }finally{
            conn.close();
        }
        return strategyList;
    }
}
