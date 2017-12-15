package org.pre.dao.factory;

import org.pre.dao.itf.DataDAO;
import org.pre.dao.itf.DataSetDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.itf.StrategyDAO;

/**
 * Abstract DAO Factory, stellt die Verbindung zu
 * der gewünschten DAO Factory Implementierung her und definiert
 * die zu implementierenden Methoden.
 *
 * @author D. Tsichlakis
 *
 */
public abstract class DAOFactory {

    // DAO Typen welche von der Factory unterstützt werden.
    public static final int MYSQL = 1;

    // Die konkreten Factories müssen folgenden Methoden implementieren.
    public abstract DataDAO getDataDAO();
    public abstract DataSetDAO getDataSetDAO();
    public abstract ResultDAO getResultDAO();
    public abstract StrategyDAO getStrategyDAO();


    /**
     * Gibt das konkrete DAOFactory Objekt zurueck
     *
     * @param whichFactory	Name bzw. Nummer der zu nutzenden DAOFactory
     * @return	DAOFactory
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

