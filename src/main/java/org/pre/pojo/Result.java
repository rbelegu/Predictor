package org.pre.pojo;

import javafx.beans.property.*;


import java.time.LocalDateTime;

public class Result {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty strategy_id = new SimpleIntegerProperty();
    private final StringProperty parameter = new SimpleStringProperty();
    private final DoubleProperty averageYield = new SimpleDoubleProperty();
    private final DoubleProperty accumulatedPl = new SimpleDoubleProperty();
    private final DoubleProperty averagePlVol = new SimpleDoubleProperty();
    private final IntegerProperty countProfitTrades = new SimpleIntegerProperty();
    private final IntegerProperty countLossTrades = new SimpleIntegerProperty();
    private final DoubleProperty maxProfitTrade = new SimpleDoubleProperty();
    private final DoubleProperty maxLossTrade = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();

    public Result(){}
    public Result(Integer id, Integer strategy_id,String parameter, Double averageYield, Double accumulatedPL, Double averagePlVol, Integer countProfitTrades, Integer countLossTrader,
            Double maxProfitTrade, Double maxLossTrade, LocalDateTime timestamp) {
        setId(id);
        setStrategy_id(strategy_id);
        setParameter(parameter);
        setAverageYield(averageYield);
        setAccumulatedPl(accumulatedPL);
        setAveragePlVol(averagePlVol);
        setCountProfitTrades(countProfitTrades);
        setCountLossTrades(countLossTrader);
        setMaxLossTrade(maxLossTrade);
        setMaxProfitTrade(maxProfitTrade);
        setTimestamp(timestamp);
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

    public LocalDateTime getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<LocalDateTime> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp.set(timestamp);
    }
}
