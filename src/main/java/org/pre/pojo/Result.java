package org.pre.pojo;

import javafx.beans.property.*;

import java.sql.Timestamp;

public class Result {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty strategy_id = new SimpleIntegerProperty();
    private final StringProperty parameter = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final DoubleProperty averageYield = new SimpleDoubleProperty();
    private final DoubleProperty accumulatedPl = new SimpleDoubleProperty();
    private final DoubleProperty averagePlVol = new SimpleDoubleProperty();
    private final IntegerProperty countProfitTrades = new SimpleIntegerProperty();
    private final IntegerProperty countLossTrades = new SimpleIntegerProperty();
    private final DoubleProperty maxProfitTrade = new SimpleDoubleProperty();
    private final DoubleProperty maxLossTrade = new SimpleDoubleProperty();
    private final ObjectProperty<Timestamp> timestamp = new SimpleObjectProperty<Timestamp>();

    public Result(){

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

    public int getStrategy_id() {
        return strategy_id.get();
    }

    public IntegerProperty strategy_idProperty() {
        return strategy_id;
    }

    public void setStrategy_id(int strategy_id) {
        this.strategy_id.set(strategy_id);
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

    public Timestamp getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<Timestamp> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp.set(timestamp);
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", strategy_id=" + strategy_id +
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
