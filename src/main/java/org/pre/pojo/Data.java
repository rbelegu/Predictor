package org.pre.pojo;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Data {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty dataSet_id = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> rateDate = new SimpleObjectProperty<>();
    private final DoubleProperty rate = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();


    public Data() {
    }

    public Data(Integer dataSet_id, LocalDate rateDate, Double rate) {
    setDataSet_id(dataSet_id);
    setRateDate(rateDate);
    setRate(rate);
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

    public int getDataSet_id() {
        return dataSet_id.get();
    }

    public IntegerProperty dataSet_idProperty() {
        return dataSet_id;
    }

    public void setDataSet_id(int dataSet_id) {
        this.dataSet_id.set(dataSet_id);
    }

    public LocalDate getRateDate() {
        return rateDate.get();
    }

    public ObjectProperty<LocalDate> rateDateProperty() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate.set(rateDate);
    }

    public double getRate() {
        return rate.get();
    }

    public DoubleProperty rateProperty() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate.set(rate);
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
