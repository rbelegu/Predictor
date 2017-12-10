package org.pre.dao.datasource;

import java.sql.Connection;

public interface Datasource {
    static Connection getConnection() {
            return null;
    };
    static void closeConnectionPool(){};
}
