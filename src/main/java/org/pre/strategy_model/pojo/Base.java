package org.pre.strategy_model.pojo;

import java.time.LocalDate;

public class Base {

    private LocalDate rateDate;
    private double spotRate;
    private String direction;
    private double realizedPl;
    private double accumulatedRealizedPl;

    public LocalDate getRateDate() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate = rateDate;
    }

    public double getSpotRate() {
        return spotRate;
    }

    public void setSpotRate(double spotRate) {
        this.spotRate = spotRate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getRealizedPl() {
        return realizedPl;
    }

    public void setRealizedPl(double realizedPl) {
        this.realizedPl = realizedPl;
    }

    public double getAccumulatedRealizedPl() {
        return accumulatedRealizedPl;
    }

    public void setAccumulatedRealizedPl(double accumulatedRealizedPl) {
        this.accumulatedRealizedPl = accumulatedRealizedPl;
    }
}
