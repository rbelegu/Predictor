package org.pre.dao.itf;

import javafx.collections.ObservableList;
import org.pre.pojo.Strategy;

import java.sql.SQLException;

public interface StrategyDAO {
    ObservableList<Strategy> getStrategyList() throws SQLException;
    void updateStrategy(Strategy strategy) throws SQLException;
    boolean deleteStrategy(int strategy_id) throws SQLException;
    Strategy insertStrategy(Strategy strategy) throws SQLException;
}
