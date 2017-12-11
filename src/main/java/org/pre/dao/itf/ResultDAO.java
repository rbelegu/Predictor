package org.pre.dao.itf;

import javafx.collections.ObservableList;
import org.pre.pojo.Result;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO {
    boolean insertResultList(List<Result> resultList) throws SQLException;
    boolean deleteResultList(int strategy_id) throws SQLException;
    ObservableList<Result> getResultList(int strategy_id) throws SQLException;
}
