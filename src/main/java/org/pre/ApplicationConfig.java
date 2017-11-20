package org.pre;

import org.pre.controller.MenuBarController;
import org.pre.controller.tab.*;
import org.pre.db.Database;
import org.pre.model.DataSetModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    public MvaStrategyController mvaStrategyController(DataSetModel dataSetModel) {
        return new MvaStrategyController(dataSetModel);
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
