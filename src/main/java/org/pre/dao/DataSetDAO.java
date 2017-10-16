package org.pre.dao;

import com.mysql.jdbc.PreparedStatement;
import org.pre.db.Database;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Map;

public class DataSetDAO {
    private Database database;
    private final static String TABLE_NAME = "dataset";

    @Autowired
    public void setDatabase(Database database){
        this.database = database;
    }


    /**
     * Prueft ob eine EMail bereits in der Datenbank vorhanden ist.
     * Die Pruefung erfolgt ohne Message, nur die Header-Informationen
     *
     * @param from			EMail Absender
     * @param recipient		EMail Ziel-Adresse
     * @param subject		Betreff
     * @param time			Datum als Long-DatenTyp
     * @return				true => wenn einen solchen Header in der DB vorhanden ist
     */
    public static void insertDataSet(Map yahoo, Integer dataset_id) throws SQLException{

    }
}
