package org.pre.dao.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface Datasource {
    static Connection getConnection() {
            return null;
    };
    static void closeConnectionPool(){} ;

}
