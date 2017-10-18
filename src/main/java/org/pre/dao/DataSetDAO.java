package org.pre.dao;


import com.mysql.jdbc.Statement;
import org.pre.db.Database;
import org.pre.pojo.Data;
import org.pre.pojo.DataSet;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.*;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

public class DataSetDAO {
    private static Database database;
    private final static String TABLE_NAME = "dataset";
    private final static String ID = "id";
    private final static String UNDERLYING = "underlying";
    private final static String FROM_DATE = "from_date";
    private final static String TO_DATE = "to_date";
    private final static String DATAPOINTS = "data_points";
    private final static String STATUS = "status";
    private final static String TIMESTAMP = "timestamp";

    @Autowired
    public void setDatabase(Database database){
        this.database = database;
    }


    /**
     * BLA BLA
     */
    public static int addDataSet(DataSet dataSet) throws SQLException {
        Connection conn = database.getConnection();
        ResultSet rs;
        int autoIncKeyFromApi = -1;
        PreparedStatement stmt;
        try {
            String insertStatement = "INSERT INTO " + TABLE_NAME +
                    "( " + UNDERLYING + ", " + FROM_DATE + ", "
                    + TO_DATE + ", " + DATAPOINTS + ", " + STATUS + ", " + TIMESTAMP + ") VALUES ( " + "?, ?, ?, ?, ?, ? )";
            stmt = conn.prepareStatement(insertStatement);
            stmt.setString(1,dataSet.getUnderlying());
            stmt.setDate(2, dataSet.getFromDate());
            stmt.setDate(3, dataSet.gettoDate());
            stmt.setDouble(4,dataSet.getDatapoints());
            stmt.setString(5, dataSet.getStatus());
            stmt.setTimestamp(6, dataSet.getCreationTimestamp());

            stmt.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return autoIncKeyFromApi;
    }

}
