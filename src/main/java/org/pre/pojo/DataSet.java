package org.pre.pojo;

import javafx.beans.property.*;


import java.sql.Timestamp;
import java.sql.Date;

public class DataSet {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty underlying = new SimpleStringProperty();
    private final ObjectProperty<Date> fromDate = new SimpleObjectProperty<Date>();
    private final ObjectProperty<Date> toDate = new SimpleObjectProperty<Date>();
    private final IntegerProperty datapoints = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<Timestamp> timestamp = new SimpleObjectProperty<Timestamp>();

    public DataSet(){};

    public DataSet(Integer id, String underlying, Date fromDate, Date toDate, Integer datapoints, String status, Timestamp timestmap) {
    setId(id);
    setUnderlying(underlying);
    setFromDate(fromDate);
    setToDate(toDate);
    setDatapoints(datapoints);
    setStatus(status);
    setTimestamp(timestmap);
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

    public String getUnderlying() {
        return underlying.get();
    }

    public StringProperty underlyingProperty() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying.set(underlying);
    }

    public Date getFromDate() {
        return fromDate.get();
    }

    public ObjectProperty<Date> fromDateProperty() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate.set(fromDate);
    }

    public Date gettoDate() {
        return toDate.get();
    }

    public ObjectProperty<Date> toDateProperty() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate.set(toDate);
    }

    public int getDatapoints() {
        return datapoints.get();
    }

    public IntegerProperty datapointsProperty() {
        return datapoints;
    }

    public void setDatapoints(int datapoints) {
        this.datapoints.set(datapoints);
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
        return "DataSet{" +
                "id=" + id +
                ", underlying=" + underlying +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", datapoints=" + datapoints +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
