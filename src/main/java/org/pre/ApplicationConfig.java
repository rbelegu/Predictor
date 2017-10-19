package org.pre;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.pre.controller.MenuBarController;
import org.pre.controller.tab.*;
import org.pre.dao.DataSetDAO;
import org.pre.db.Database;
import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {


    @Bean(destroyMethod = "closeConnectionPool")
    public Database database() {
        return new Database();
    }

    @Bean
    public Executor executor() {
        return Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
    }

    @Bean
    public DataSetModel dataSetModel(){
        return new DataSetModel();
    }

    @Bean
    @Scope("prototype")
    public MenuBarController menuBarController() {
        return new MenuBarController();
    }

    @Bean
    @Scope("prototype")
    public ImportDataController importDataController(DataSetModel dataSetModel) {
        return new ImportDataController(dataSetModel);
    }

    @Bean
    @Scope("prototype")
    public MvaStrategyController mvaStrategyController() {
        return new MvaStrategyController();
    }

    @Bean
    @Scope("prototype")
    public DataManagerController dataManagerController(DataSetModel dataSetModel) {
        return new DataManagerController(dataSetModel);
    }

    @Bean
    @Scope("prototype")
    public StrategyManagerController strategyManagerController() {
        return new StrategyManagerController();
    }

    @Bean
    @Scope("prototype")
    public ResultAnalyserController resultAnalyserController() {
        return new ResultAnalyserController();
    }

}
