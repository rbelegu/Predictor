package org.pre;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.pre.controller.MenuBarController;
import org.pre.controller.tab.*;
import org.pre.dao.DataDAO;
import org.pre.dao.DataSetDAO;
import org.pre.db.DBPreferences;
import org.pre.db.Database;
import org.pre.model.DataSetModel;
import org.pre.model.ResultAnalyserModel;
import org.pre.model.StrategyModel;
import org.pre.pojo.Strategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan
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
    public StrategyModel strategyModel(){
        return new StrategyModel();
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
    public MvaStrategyController mvaStrategyController(DataSetModel dataSetModel, StrategyModel strategyModel) {
        return new MvaStrategyController(dataSetModel, strategyModel);
    }

    @Bean
    @Scope("prototype")
    public DataManagerController dataManagerController(DataSetModel dataSetModel) {
        return new DataManagerController(dataSetModel);
    }

    @Bean
    @Scope("prototype")
    public StrategyManagerController strategyManagerController(StrategyModel strategyModel) {
        return new StrategyManagerController(strategyModel);
    }








}
