package org.pre.pojo;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.sql.Date;

public class Data {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty underlying_id = new SimpleIntegerProperty();
    private final ObjectProperty<Date> rateDate = new SimpleObjectProperty<Date>();
    private final DoubleProperty rate = new SimpleDoubleProperty();
    private final ObjectProperty<Timestamp> timestamp = new SimpleObjectProperty<Timestamp>();


    public Data() {
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

    public int getUnderlying_id() {
        return underlying_id.get();
    }

    public IntegerProperty underlying_idProperty() {
        return underlying_id;
    }

    public void setUnderlying_id(int underlying_id) {
        this.underlying_id.set(underlying_id);
    }

    public Date getRateDate() {
        return rateDate.get();
    }

    public ObjectProperty<Date> rateDateProperty() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
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
        return "Data{" +
                "id=" + id +
                ", underlying_id=" + underlying_id +
                ", rateDate=" + rateDate +
                ", rate=" + rate +
                ", timestamp=" + timestamp +
                '}';
    }
}
