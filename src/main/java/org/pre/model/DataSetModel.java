package org.pre.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.dao.DataSetDAO;
import org.pre.pojo.DataSet;
import org.pre.util.ProgressStatus;
import org.springframework.beans.factory.annotation.Autowired;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Executor;

import static java.time.ZonedDateTime.from;
import static yahoofinance.histquotes.Interval.DAILY;

public class DataSetModel {
    private Executor exec;


    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }

    private final ObservableList<DataSet> dataSetList;


    public DataSetModel(){
        dataSetList = FXCollections.observableArrayList();
    }

    @PostConstruct
    public void init(){
        Task<ObservableList<DataSet>> loadTask = new Task<ObservableList<DataSet>>(){
            @Override
            public ObservableList<DataSet> call() throws SQLException {
                DataSetDAO newAccess = new DataSetDAO();
                return newAccess.getDataSetList();
            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
        });
        loadTask.setOnSucceeded(e -> dataSetList.addAll(loadTask.getValue()));
        exec.execute(loadTask);
    }

    public ObservableList<DataSet> getDataSetList() {
        return dataSetList;
    }

    public void addDataSet(DataSet dataSet){
        Task<DataSet> loadTask = new Task<DataSet>(){
            @Override
            public DataSet call() throws Exception {
                // Speichern des Objekts mit Running Status in DB und danach in Tabelle publishen.
                DataSetDAO newAccess = new DataSetDAO();
                DataSet currentDataSet = newAccess.insertDataSet(dataSet);
                dataSetList.addAll(currentDataSet);
                Thread.sleep(5000);
                Calendar from = Calendar.getInstance();
                Calendar to = Calendar.getInstance();
                from.add(Calendar.YEAR, -3); // from 1 year ago
                Stock stock = null;
                try {
                    stock = YahooFinance.get(currentDataSet.getUnderlying());
                } catch (IOException e) {
                    e.printStackTrace();

                }
                List<HistoricalQuote> HistQuotes = stock.getHistory(from, GregorianCalendar.from(currentDataSet.getToDate().atStartOfDay(ZoneId.systemDefault())) , Interval.DAILY);
                System.out.println(HistQuotes.size());
                //System.out.println(HistQuotes.toString());
                currentDataSet.setStatus(ProgressStatus.DONE.toString());
                newAccess.updateDataSet(currentDataSet);
                return null;

            }
        };
        loadTask.setOnFailed(event ->{
            Throwable exception = loadTask.getException();
            System.err.println(exception.getCause() + "\n" + exception.getMessage());
            Notifications.create().title("Error:").text(exception.getMessage()).hideAfter(Duration.minutes(5)).showError();
        });
        loadTask.setOnSucceeded(event -> System.out.println("OLA KALA"));
        exec.execute(loadTask);
    }


}
