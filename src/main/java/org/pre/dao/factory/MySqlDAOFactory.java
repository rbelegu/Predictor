package org.pre.dao.factory;


import org.pre.dao.impl.MySqlDataSetDAO;
import org.pre.dao.impl.MySqlResultDAO;
import org.pre.dao.impl.MySqlStrategyDAO;
import org.pre.dao.itf.DataSetDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.itf.StrategyDAO;
import org.pre.dao.impl.MySqlDataDAO;
import org.pre.dao.itf.DataDAO;

public class MySqlDAOFactory extends DAOFactory {


    public DataDAO getDataDAO() {
        return new MySqlDataDAO();
    }

    public DataSetDAO getDataSetDAO() {
        return new MySqlDataSetDAO() ;
    }


    public ResultDAO getResultDAO() {
        return new MySqlResultDAO();
    }


    public StrategyDAO getStrategyDAO() {
        return new MySqlStrategyDAO();
    }
}
