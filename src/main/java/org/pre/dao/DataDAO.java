package org.pre.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pre.db.Database;
import org.pre.pojo.Data;
import org.pre.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;


public class DataDAO {
    private Database database;
    //Tabelle und Spalten name
    private final static String TABLE_NAME = "data";
    private final static String ID = "id";
    private final static String DATASET_ID = "dataset_id";
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
    public boolean insertDataList(List<Data> dataList) throws SQLException {
        Connection conn = database.getConnection();
        boolean flag = false;
        PreparedStatement insertData;
        String insertStatement = "INSERT INTO " + TABLE_NAME +
                "( " + DATASET_ID + ", " + RATE_DATE + ", "
                + RATE + ") VALUES (?,?,?)";

        try{
            insertData = conn.prepareStatement(insertStatement);
            for (Data data : dataList){
                insertData.setInt(1, data.getUnderlying_id());
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


    public List<Data> getDataListFromYahooApi(String symbol, LocalDate dateFrom, LocalDate dateTo, Integer underlying_id) throws IOException {
        Stock stock = null;
        try {
            stock = YahooFinance.get(symbol);
        } catch (IOException e) {
            e.printStackTrace();

        }

        assert stock != null;
        List<HistoricalQuote> HistQuotes = stock.getHistory(GregorianCalendar.from(dateFrom.atStartOfDay(ZoneId.systemDefault())), GregorianCalendar.from(dateTo.atStartOfDay(ZoneId.systemDefault())), Interval.DAILY);



        List<Data> list = FXCollections.observableArrayList();
        for (HistoricalQuote line : HistQuotes) {
            try
            {
          list.add(new Data(underlying_id, DateUtils.getLocalDatefromCalendar(line.getDate()), line.getClose().doubleValue()));
        }catch (NullPointerException e){
                System.out.println(line.getClose());
            e.printStackTrace();}
        }

        return list;
    }



    /**
     * BLA BLA
     */
    public ObservableList<Data> getDataList(Integer id) throws SQLException {
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        Connection conn = database.getConnection();
        ResultSet resultSet;
        PreparedStatement prestmt;
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
        return dataList;
    }



}
