package org.pre.pojo;

import javafx.beans.property.*;

import java.sql.Timestamp;

public class Result {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty parameter = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private DoubleProperty averageYield = new SimpleDoubleProperty();
    private DoubleProperty accumulatedPl = new SimpleDoubleProperty();
    private DoubleProperty averagePlVol = new SimpleDoubleProperty();
    private IntegerProperty countProfitTrades = new SimpleIntegerProperty();
    private IntegerProperty countLossTrades = new SimpleIntegerProperty();
    private DoubleProperty maxProfitTrade = new SimpleDoubleProperty();
    private DoubleProperty maxLossTrade = new SimpleDoubleProperty();
    private ObjectProperty<Timestamp> timestamp = new SimpleObjectProperty<Timestamp>();

    public Result(){

    }

    public Result(StringProperty parameter, StringProperty status, DoubleProperty averageYield, DoubleProperty accumulatedPl, DoubleProperty averagePlVol, IntegerProperty countProfitTrades, IntegerProperty countLossTrades, DoubleProperty maxProfitTrade, DoubleProperty maxLossTrade, ObjectProperty<Timestamp> timestamp) {
        this.parameter = parameter;
        this.status = status;
        this.averageYield = averageYield;
        this.accumulatedPl = accumulatedPl;
        this.averagePlVol = averagePlVol;
        this.countProfitTrades = countProfitTrades;
        this.countLossTrades = countLossTrades;
        this.maxProfitTrade = maxProfitTrade;
        this.maxLossTrade = maxLossTrade;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getParameter() {
        return parameter.get();
    }

    public StringProperty parameterProperty() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter.set(parameter);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public double getAverageYield() {
        return averageYield.get();
    }

    public DoubleProperty averageYieldProperty() {
        return averageYield;
    }

    public void setAverageYield(double averageYield) {
        this.averageYield.set(averageYield);
    }

    public double getAccumulatedPl() {
        return accumulatedPl.get();
    }

    public DoubleProperty accumulatedPlProperty() {
        return accumulatedPl;
    }

    public void setAccumulatedPl(double accumulatedPl) {
        this.accumulatedPl.set(accumulatedPl);
    }

    public double getAveragePlVol() {
        return averagePlVol.get();
    }

    public DoubleProperty averagePlVolProperty() {
        return averagePlVol;
    }

    public void setAveragePlVol(double averagePlVol) {
        this.averagePlVol.set(averagePlVol);
    }

    public int getCountProfitTrades() {
        return countProfitTrades.get();
    }

    public IntegerProperty countProfitTradesProperty() {
        return countProfitTrades;
    }

    public void setCountProfitTrades(int countProfitTrades) {
        this.countProfitTrades.set(countProfitTrades);
    }

    public int getCountLossTrades() {
        return countLossTrades.get();
    }

    public IntegerProperty countLossTradesProperty() {
        return countLossTrades;
    }

    public void setCountLossTrades(int countLossTrades) {
        this.countLossTrades.set(countLossTrades);
    }

    public double getMaxProfitTrade() {
        return maxProfitTrade.get();
    }

    public DoubleProperty maxProfitTradeProperty() {
        return maxProfitTrade;
    }

    public void setMaxProfitTrade(double maxProfitTrade) {
        this.maxProfitTrade.set(maxProfitTrade);
    }

    public double getMaxLossTrade() {
        return maxLossTrade.get();
    }

    public DoubleProperty maxLossTradeProperty() {
        return maxLossTrade;
    }

    public void setMaxLossTrade(double maxLossTrade) {
        this.maxLossTrade.set(maxLossTrade);
    }

    public Timestamp gettimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<Timestamp> timestampProperty() {
        return timestamp;
    }

    public void settimestamp(Timestamp timestamp) {
        this.timestamp.set(timestamp);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", parameter=" + parameter +
                ", status=" + status +
                ", averageYield=" + averageYield +
                ", accumulatedPl=" + accumulatedPl +
                ", averagePlVol=" + averagePlVol +
                ", countProfitTrades=" + countProfitTrades +
                ", countLossTrades=" + countLossTrades +
                ", maxProfitTrade=" + maxProfitTrade +
                ", maxLossTrade=" + maxLossTrade +
                ", timestamp=" + timestamp +
                '}';
    }
}
