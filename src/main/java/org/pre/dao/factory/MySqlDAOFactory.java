package org.pre.dao.factory;


import org.pre.dao.impl.MySqlDataSetDAO;
import org.pre.dao.impl.MySqlResultDAO;
import org.pre.dao.impl.MySqlStrategyDAO;
import org.pre.dao.itf.DataSetDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.itf.StrategyDAO;
import org.pre.dao.impl.MySqlDataDAO;
import org.pre.dao.itf.DataDAO;

/**
 * MySQL DAO Factory, mit der konktreten Implementierung
 * der einzelnen Methoden.
 *
 * @author D. Tsichlakis
 *
 */
public class MySqlDAOFactory extends DAOFactory {

    /**
     * Liefert das MySql DataDAO Objekt zurück.
     *
     * @return DataDAO
     */
    public DataDAO getDataDAO() {
        return new MySqlDataDAO();
    }

    /**
     * Liefert das DataSetDAO Objekt zurück.
     *
     * @return DataSetDAO
     */
    public DataSetDAO getDataSetDAO() {return new MySqlDataSetDAO() ; }

    /**
     * Liefert das ResultDAO Objekt zurück.
     *
     * @return ResultDAO
     */
    public ResultDAO getResultDAO() {
        return new MySqlResultDAO();
    }

    /**
     * Liefert das StrategyDAO Objekt zurück.
     *
     * @return StrategyDAO
     */
    public StrategyDAO getStrategyDAO() {
        return new MySqlStrategyDAO();
    }
}
