package org.pre.dao.itf;

import javafx.collections.ObservableList;
import org.pre.pojo.Data;

import java.sql.SQLException;
import java.util.List;

public interface DataDAO {
    boolean insertDataList(List<Data> dataList) throws SQLException;
    ObservableList<Data> getDataList(Integer dataSetId) throws SQLException;
}
