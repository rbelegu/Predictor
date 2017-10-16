package org.pre.dao;


import org.pre.db.Database;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

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
     * -.
     * -
     * @param yahoo			Yahoo Data Run
     * @param dataset_id		Data Set id als FK
     */
    public void insertDataRun(Map yahoo, Integer dataset_id) throws SQLException {
        PreparedStatement insertData = null;
        String insertStatement = "INSERT INTO " + TABLE_NAME + "( "
                + ID + ", " + DATASED_ID + ", " + RATE_DATE + ", "
                + RATE + ", " + TIMESTAMP + ") VALUES (?,?,?,?,?,?,?,?)";
        try{
            Connection conn = database.getConnection();


        }catch (SQLException e){

        }


    }



}
