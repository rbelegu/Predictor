package org.pre.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;
import org.pre.pojo.Data;
import org.pre.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.List;

import static org.pre.util.DateUtils.convertSQLDateToLocalDate;


public class DataDAO {
    //Tabelle und Spalten name
    private final static String TABLE_NAME = "data";
    private final static String ID = "id";
    private final static String DATASET_ID = "dataset_id";
    private final static String RATE_DATE = "ratedate";
    private final static String RATE = "rate";
    private final static String TIMESTAMP = "timestamp";


    /**
     * Schreibt die Daten von einem DatenSet in einem Stück (commit) in die DB.
     * -
     * @param dataList		Yahoo Data Run
     */
    public boolean insertDataList(List<Data> dataList) throws SQLException {
        Connection conn = Database.getConnection();
        boolean flag = false;
        PreparedStatement insertData;
        String insertStatement = "INSERT INTO " + TABLE_NAME +
                "( " + DATASET_ID + ", " + RATE_DATE + ", "
                + RATE + ") VALUES (?,?,?)";

        try{
            insertData = conn.prepareStatement(insertStatement);
            for (Data data : dataList){
                insertData.setInt(1, data.getDataSet_id());
                insertData.setDate(2, java.sql.Date.valueOf(data.getRateDate()));
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


    public List<Data> getDataListFromCsv(String path, Integer underlying_id) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        String CsvFile = path;
        String FieldDelimiter = ";";
        List<Data> list = FXCollections.observableArrayList();
        BufferedReader br;
        System.out.println("TEST");
        try {
            br = new BufferedReader(new FileReader(CsvFile));
            br.readLine(); // this will read the first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                list.add(new Data(underlying_id, LocalDate.parse(fields[0], formatter), Double.parseDouble(fields[1])));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Sort old to new
        list.sort((o1, o2) -> o1.getRateDate().compareTo(o2.getRateDate()));


        return list;
    }



    /**
     * BLA BLA
     */
    public ObservableList<Data> getDataList(Integer dataSetId) throws SQLException {
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        Connection conn = Database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
        try {
            String insertStatement = "SELECT " + ID + ", " + DATASET_ID + ", " + RATE_DATE + ", " + RATE + "," + TIMESTAMP + " FROM " + TABLE_NAME + " WHERE "
                    + DATASET_ID + " = " + dataSetId;
            prestmt = conn.prepareStatement(insertStatement);
            resultSet = prestmt.executeQuery();
            conn.commit();
            while (resultSet.next()){
                //Resultat Zwilenweise Auslesen und neus EMail-Objekt erstellen
                Data data = new Data();
                data.setId(resultSet.getInt(1));
                data.setDataSet_id(resultSet.getInt(2));
                data.setRateDate(convertSQLDateToLocalDate(resultSet.getDate(3)));
                data.setRate(resultSet.getDouble(4));
                data.setTimestamp(resultSet.getTimestamp(5).toLocalDateTime());
                dataList.add(data);
            }
        }  finally {
            conn.close();
        }
        return dataList;
    }



}
