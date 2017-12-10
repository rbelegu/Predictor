package org.pre.dao.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.pre.dao.preferences.MySqlPreferences;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDatasource implements Datasource {


    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        MySqlPreferences mySqlPreferences = new MySqlPreferences();
        config.setJdbcUrl( mySqlPreferences.getdbURL() );          //jdbc:mysql://localhost/predictor
        config.setUsername( mySqlPreferences.getdbUser() );        //root
        config.setPassword( mySqlPreferences.getdbPassword() );    //no Password
        config.setConnectionTestQuery("SELECT 1");
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

        try {
            ds = new HikariDataSource( config );
        }catch (Exception e){
            System.err.println((e.getMessage()));

        }
    }

    public MySqlDatasource() {
        try {
            createDataSetTable();
            createDataTable();
            createStrategyTable();
            createResultTable();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnectionPool() {
        try {
            ds.close();
        }catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
    }

    //******************************************************************
    // D A T E N B A N K - D E S I G N E   [ N E U   E R S T E L L E N ]
    //******************************************************************

    /**
     * Erstellt die DataSet Tabelle auf der Datenbank
     *
     * @throws SQLException
     */
    private void createDataSetTable() throws SQLException {
        Connection conn = getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS dataset"
                + "  (id INT NOT NULL AUTO_INCREMENT,"
                + "   underlying VARCHAR(30) NOT NULL ,"
                + "   from_date DATE,"
                + "   to_date DATE,"
                + "   data_points INT,"
                + "   status VARCHAR(30),"
                + "   timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP,"
                + "   UNIQUE KEY (underlying),"
                + "   PRIMARY KEY (id))" +
                "ENGINE=INNODB DEFAULT  CHARSET=UTF8";
        Statement stmt = getConnection().createStatement();
        stmt.execute(sqlCreate);
        conn.close();
    }

    /**
     * Erstellt die Data Tabelle auf der Datenbank
     *
     * @throws SQLException
     */
    private void createDataTable() throws SQLException {
        Connection conn = getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS data"
                + "  (id INT NOT NULL AUTO_INCREMENT,"
                + "   dataset_id INT NOT NULL,"
                + "   ratedate DATE NOT NULL,"
                + "   rate DOUBLE NOT NULL,"
                + "   timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP,"
                + "   UNIQUE KEY (dataset_id,ratedate),"
                + "   PRIMARY KEY (id),"
                + "   FOREIGN KEY (dataset_id) REFERENCES dataset(id))" +
                "ENGINE=INNODB DEFAULT  CHARSET=UTF8";
        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
        conn.close();
    }

    /**
     * Erstellt die Strategy Tabelle auf der Datenbank
     *
     * @throws SQLException
     */
    private void createStrategyTable() throws SQLException {
        Connection conn = getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS strategy"
                + "  (id INT NOT NULL AUTO_INCREMENT,"
                + "   dataset_id INT NOT NULL,"
                + "   type VARCHAR(30) NOT NULL,"
                + "   parameter VARCHAR(30) NOT NULL,"
                + "   status VARCHAR(30) NOT NULL,"
                + "   size INT,"
                + "   timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP,"
                + "   UNIQUE KEY (dataset_id, type, parameter),"
                + "   PRIMARY KEY (id),"
                + "   FOREIGN KEY (dataset_id) REFERENCES dataset(id))"
                + "   ENGINE=INNODB DEFAULT  CHARSET=UTF8";
        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
        conn.close();
    }

    /**
     * Erstellt die Strategy Tabelle auf der Datenbank
     *
     * @throws SQLException
     */
    private void createResultTable() throws SQLException {
        Connection conn = getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS result"
                + "  (id INT NOT NULL AUTO_INCREMENT,"
                + "   strategy_id INT NOT NULL,"
                + "   parameter VARCHAR(30) NOT NULL,"
                + "   average_yield DOUBLE NOT NULL,"
                + "   accumulated_pl DOUBLE NOT NULL,"
                + "   count_profit_trades INT NOT NULL,"
                + "   count_loss_trades INT NOT NULL,"
                + "   max_profit_trade DOUBLE NOT NULL,"
                + "   max_loss_trade DOUBLE NOT NULL,"
                + "   timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP,"
                + "   UNIQUE KEY (strategy_id,parameter),"
                + "   PRIMARY KEY (id),"
                + "   FOREIGN KEY (strategy_id) REFERENCES strategy(id))" +
                "ENGINE=INNODB DEFAULT  CHARSET=UTF8";
        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
        conn.close();
    }

}
