package org.pre.dao.factory;

import org.pre.dao.itf.DataDAO;
import org.pre.dao.itf.DataSetDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.itf.StrategyDAO;

public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final int MYSQL = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract DataDAO getDataDAO();
    public abstract DataSetDAO getDataSetDAO();
    public abstract ResultDAO getResultDAO();
    public abstract StrategyDAO getStrategyDAO();


    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            default           :
                return null;
        }
    }
}

