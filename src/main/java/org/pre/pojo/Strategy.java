package org.pre.pojo;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Strategy {
    private final BooleanProperty checked = new SimpleBooleanProperty();
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty parameter = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final IntegerProperty size = new SimpleIntegerProperty();
    private final IntegerProperty dataSet_id = new SimpleIntegerProperty();
    private final StringProperty underlying = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fromDate = new SimpleObjectProperty<LocalDate>();
    private final ObjectProperty<LocalDate> toDate = new SimpleObjectProperty<LocalDate>();
    private final ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();


    public Strategy() {
    }

    public boolean isChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
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

    public int getDataSet_id() {
        return dataSet_id.get();
    }

    public IntegerProperty dataSet_idProperty() {
        return dataSet_id;
    }

    public void setDataSet_id(int dataSet_id) {
        this.dataSet_id.set(dataSet_id);
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
