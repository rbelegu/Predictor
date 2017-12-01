package org.pre.math_model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mva {

    private final ObjectProperty<LocalDate> rateDate = new SimpleObjectProperty<>();
    private final DoubleProperty spotRate = new SimpleDoubleProperty();
    private final DoubleProperty mvaRate = new SimpleDoubleProperty();
    private final StringProperty direction = new SimpleStringProperty();
    private final DoubleProperty realizedPl = new SimpleDoubleProperty();
    private final DoubleProperty accumulatedRealizedPl = new SimpleDoubleProperty();

    public LocalDate getRateDate() {
        return rateDate.get();
    }

    public ObjectProperty<LocalDate> rateDateProperty() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate.set(rateDate);
    }

    public double getSpotRate() {
        return spotRate.get();
    }

    public DoubleProperty spotRateProperty() {
        return spotRate;
    }

    public void setSpotRate(double spotRate) {
        this.spotRate.set(spotRate);
    }

    public double getMvaRate() {
        return mvaRate.get();
    }

    public DoubleProperty mvaRateProperty() {
        return mvaRate;
    }

    public void setMvaRate(double mvaRate) {
        this.mvaRate.set(mvaRate);
    }

    public String getDirection() {
        return direction.get();
    }

    public StringProperty directionProperty() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public double getRealizedPl() {
        return realizedPl.get();
    }

    public DoubleProperty realizedPlProperty() {
        return realizedPl;
    }

    public void setRealizedPl(double realizedPl) {
        this.realizedPl.set(realizedPl);
    }

    public double getAccumulatedRealizedPl() {
        return accumulatedRealizedPl.get();
    }

    public DoubleProperty accumulatedRealizedPlProperty() {
        return accumulatedRealizedPl;
    }

    public void setAccumulatedRealizedPl(double accumulatedRealizedPl) {
        this.accumulatedRealizedPl.set(accumulatedRealizedPl);
    }
}
