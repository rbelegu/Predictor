package org.pre.dao;


import org.pre.db.Database;
import org.pre.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class DataDAO {
    private Database database;
    //Tabelle und Spalten name
    private final static String TABLE_NAME = "data";
    private final static String ID = "id";
    private final static String DATASED_ID = "dataset_id";
    private final static String RATE_DATE = "ratedate";
    private final static String RATE = "rate";
    private final static String TIMESTAMP = "timestamp";


    @Autowired
    public void setDatabase(Database database){
        this.database = database;
    }


    /**
     * Schreibt die Daten von einem DatenSet in einem Stück (commit) in die DB.
     * -
     * @param dataList		Yahoo Data Run
     */
    public boolean insertDataRun(List<Data> dataList) throws SQLException {
        Connection conn = database.getConnection();
        boolean flag = false;
        PreparedStatement insertData;
        String insertStatement = "INSERT INTO " + TABLE_NAME +
                "( " + DATASED_ID + ", " + RATE_DATE + ", "
                + RATE + ") VALUES (?,?,?)";

        try{
            insertData = conn.prepareStatement(insertStatement);
            for (Data data : dataList){
                insertData.setInt(1, data.getUnderlying_id());
                insertData.setDate(2, data.getRateDate());
                insertData.setDouble(3,data.getRate());
                insertData.execute();
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



}
