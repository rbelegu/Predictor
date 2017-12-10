package org.pre.dao.itf;

import javafx.collections.ObservableList;
import org.pre.pojo.DataSet;

import java.sql.SQLException;

public interface DataSetDAO {
    DataSet insertDataSet(DataSet dataSet) throws SQLException;
    void updateDataSet(DataSet dataSet) throws SQLException;
    ObservableList<DataSet> getDataSetList() throws SQLException;
}
