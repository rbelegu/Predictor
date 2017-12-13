package org.pre.model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.controller.tab.ResultAnalyserController;
import org.pre.dao.itf.DataDAO;
import org.pre.dao.itf.ResultDAO;
import org.pre.dao.factory.DAOFactory;
import org.pre.dao.itf.StrategyDAO;
import org.pre.pojo.Data;

import org.pre.pojo.Result;
import org.pre.pojo.Strategy;
import org.pre.strategy_model.factory.StrategySolver;
import org.pre.strategy_model.factory.StrategySolverFactory;
import org.pre.util.DateUtils;
import org.pre.util.ProgressStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.concurrent.Executor;


public class StrategyModel {

    private Executor exec;
    private DAOFactory daoFactory;
    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<Strategy> strategyList;

    public StrategyModel(){
        strategyList = FXCollections.observableArrayList();
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    }


    @PostConstruct
    public void init(){
        Task<ObservableList<Strategy>> loadTask = new Task<ObservableList<Strategy>>(){
            @Override
            public ObservableList<Strategy> call() throws SQLException {
                StrategyDAO strategyDAO = daoFactory.getStrategyDAO();
                return strategyDAO.getStrategyList();
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
        });
        loadTask.setOnSucceeded(e -> strategyList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }



    public ObservableList<Strategy> getStrategyList() {

        return strategyList;
    }

    public void showResults(Strategy strategy)  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/pre/view/tab/ResultAnalyser.fxml"));
         //   fxmlLoader.setControllerFactory(Main.getApplicationContext()::getBean);
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            ResultAnalyserController controller = fxmlLoader.getController();
            controller.initialize(strategy);
            stage.setTitle("Results    DataSet: " + strategy.getUnderlying() + "       Strategy Type: " + strategy.getType()
                    + "       Time Period:" + strategy.getFromDate().format(DateUtils.getCustomizedDateFormat()) + " - " +
                    strategy.getToDate().format(DateUtils.getCustomizedDateFormat()) );
            stage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Null Point wenn DB crashed, removed aus der liste über COntroller beim iterator sonst gibt runtime exception
    public void removeStrategy(Strategy strategy){
        Strategy lastShowOnSuccess = strategy;
        Task<Strategy> loadTask = new Task<Strategy>() {
            @Override
            public Strategy call() throws Exception {
                try {
                    StrategyDAO strategyDAO = daoFactory.getStrategyDAO();
                    strategyDAO.deleteStrategy(strategy.getId());
                }catch (SQLException | NullPointerException e){
                    Platform.runLater(() ->  strategyList.add(lastShowOnSuccess));
                    throw e;
                }
                return lastShowOnSuccess;
            }
        };
        loadTask.setOnFailed(event ->{
        Throwable exception = loadTask.getException();
        System.err.println(exception.getCause() + "\n" + exception.getMessage());
        Notifications.create().title("Error:").text("The following Strategy couldn't be removed" + "\n" + lastShowOnSuccess.getUnderlying()).hideAfter(Duration.minutes(1)).showError();
        });
        loadTask.setOnSucceeded(event -> Notifications.create().title("Strategy successfully removed").text(loadTask.getValue().getUnderlying() + "\n" + loadTask.getValue().getType()).hideAfter(Duration.minutes(1)).showInformation());
        exec.execute(loadTask);
    }

    public void addStrategy(Strategy strategy) {
        Task<Strategy> loadTask = new Task<Strategy>(){
            @Override
            public Strategy call() throws Exception {
                // Speichern des Objekts mit Running Status in DB und danach in Tabelle publishen.
                // Status auf Running setzen
                strategy.setStatus(ProgressStatus.RUNNING.toString());
                StrategyDAO strategyDAO = daoFactory.getStrategyDAO();
                Strategy currentStrategy = strategyDAO.insertStrategy(strategy);
                try {
                    Platform.runLater(() -> strategyList.addAll(currentStrategy));
                    DataDAO dataDAO = daoFactory.getDataDAO();
                    // Data holen
                    List<Data> dataList = dataDAO.getDataList(strategy.getDataSet_id());
                    // Solver Factory aufrufen und Result zurückerhalten
                    StrategySolver strategySolver = StrategySolverFactory.getStrategySolver(dataList, currentStrategy.getParameter(), currentStrategy.getId(), currentStrategy.getType());
                    assert strategySolver != null;
                    List<Result> resultList = strategySolver.getResultList();
                    ResultDAO resultDAO = daoFactory.getResultDAO();
                    resultDAO.insertResultList(resultList);
                    Platform.runLater(() -> currentStrategy.setSize(resultList.size()));
                    Platform.runLater(() -> currentStrategy.setStatus(ProgressStatus.DONE.toString()));
                    Platform.runLater(() -> {
                        try {
                            strategyDAO.updateStrategy(currentStrategy);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }catch (SQLException | ArrayIndexOutOfBoundsException | NullPointerException e){
                    Platform.runLater(() -> currentStrategy.setStatus(ProgressStatus.FAILED.toString()));
                    Platform.runLater(() -> {
                        try {
                            strategyDAO.updateStrategy(currentStrategy);
                        } catch (SQLException ex) {
                            ex.printStackTrace();

                        }
                    });
                    throw e;
                }
                return currentStrategy;
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
           System.err.println(exception.getCause() + "\n" + exception.getMessage());
            if(exception instanceof ArrayIndexOutOfBoundsException) {
                Notifications.create().title("Empty Data Set").text(exception.getMessage()).hideAfter(Duration.minutes(1)).showError();
            }else if(exception.getCause() instanceof SQLIntegrityConstraintViolationException) {
                Notifications.create().title("Dublicated entry not allowed:").text(exception.getMessage()).hideAfter(Duration.minutes(5)).showError();
            }else {
                Notifications.create().title("Error:").text("Oops something went wrong!").hideAfter(Duration.minutes(1)).showError();
            }
            });
        loadTask.setOnSucceeded(event -> Notifications.create().title("Strategy creation was successful").text("Data Set: " + loadTask.getValue().getUnderlying() + "\n"
                + "Strategy Type: " + loadTask.getValue().getType() + "\n" + "Parameter: " + loadTask.getValue().getParameter()).hideAfter(Duration.minutes(1)).showInformation());
        exec.execute(loadTask);
    }



}
