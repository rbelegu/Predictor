package org.pre.dao.factory;

import org.pre.dao.itf.DataDAO;
import org.pre.dao.itf.DataSetDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.itf.StrategyDAO;

/**
 * Abstract DAO Factory, stellt die Verbindung zu
 * den einzelnen DAO Factory Implementierung her und definiert
 * die zu implementierenden .
 *
 * @author D. Tsichlakis
 *
 */

public abstract class DAOFactory {

    // DAO Type welcher von der Factory unterstützt wird
    public static final int MYSQL = 1;

    // Die konkreten Factories müssen folgenden methoden implementieren.
    public abstract DataDAO getDataDAO();
    public abstract DataSetDAO getDataSetDAO();
    public abstract ResultDAO getResultDAO();
    public abstract StrategyDAO getStrategyDAO();


    /**
     * Gibt das konkrete DAOFactory Objekt zurueck
     *
     * @param whichFactory	Name bzw. Nummer der zu nutzenden DAOFactory
     * @return	Die konkreten DAOFactory
     */
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            default           :
                return null;
        }
    }
}

