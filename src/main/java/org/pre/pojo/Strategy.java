package org.pre.pojo;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.util.Date;

public class Strategy {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty parameter = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final IntegerProperty size = new SimpleIntegerProperty();
    private final IntegerProperty underlying_id = new SimpleIntegerProperty();
    private final StringProperty underlying = new SimpleStringProperty();
    private final ObjectProperty<Date> fromDate = new SimpleObjectProperty<Date>();
    private final ObjectProperty<Date> tillDate = new SimpleObjectProperty<Date>();
    private final ObjectProperty<Timestamp> timestamp = new SimpleObjectProperty<Timestamp>();

    public Strategy(){
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public int getSize() {
        return size.get();
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
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

    public Date getTillDate() {
        return tillDate.get();
    }

    public ObjectProperty<Date> tillDateProperty() {
        return tillDate;
    }

    public void setTillDate(Date tillDate) {
        this.tillDate.set(tillDate);
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
        return "Strategy{" +
                "id=" + id +
                ", type=" + type +
                ", parameter=" + parameter +
                ", status=" + status +
                ", size=" + size +
                ", underlying_id=" + underlying_id +
                ", underlying=" + underlying +
                ", fromDate=" + fromDate +
                ", tillDate=" + tillDate +
                ", timestamp=" + timestamp +
                '}';
    }
}
