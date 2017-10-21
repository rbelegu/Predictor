package org.pre.pojo;

import javafx.beans.property.*;



import java.time.LocalDate;
import java.time.LocalDateTime;


public class DataSet {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty underlying = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fromDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> toDate = new SimpleObjectProperty<>();
    private final IntegerProperty datapoints = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();

    public DataSet(){};

    public DataSet(Integer id, String underlying, LocalDate fromDate, LocalDate toDate, Integer datapoints, String status, LocalDateTime timestmap) {
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

    public LocalDate getFromDate() {
        return fromDate.get();
    }

    public ObjectProperty<LocalDate> fromDateProperty() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate.set(fromDate);
    }

    public LocalDate getToDate() {
        return toDate.get();
    }

    public ObjectProperty<LocalDate> toDateProperty() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
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

    public LocalDateTime getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<LocalDateTime> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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
