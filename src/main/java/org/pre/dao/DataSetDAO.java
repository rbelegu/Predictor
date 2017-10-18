package org.pre.dao;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.controlsfx.control.Notifications;
import org.pre.db.Database;
import org.pre.pojo.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.*;



public class DataSetDAO {
    private Database database;
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
    public int addDataSet(DataSet dataSet) throws SQLException {
        Connection conn = database.getConnection();
        ResultSet rs;
        int dataSetId = -1;
        PreparedStatement prestmt;
        try {
            String insertStatement = "INSERT INTO " + TABLE_NAME +
                    "( " + UNDERLYING + ", " + FROM_DATE + ", "
                    + TO_DATE + ", " + DATAPOINTS + ", " + STATUS + ", " + TIMESTAMP + ") VALUES ( " + "?, ?, ?, ?, ?, ? )";
            prestmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            prestmt.setString(1, dataSet.getUnderlying());
            prestmt.setDate(2, dataSet.getFromDate());
            prestmt.setDate(3, dataSet.gettoDate());
            prestmt.setDouble(4, dataSet.getDatapoints());
            prestmt.setString(5, dataSet.getStatus());
            prestmt.setTimestamp(6, dataSet.getCreationTimestamp());

            prestmt.executeUpdate();
            rs = prestmt.getGeneratedKeys();
            conn.commit();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }  finally {
            conn.close();
        }
        return dataSetId;
    }



}
