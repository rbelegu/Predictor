package org.pre.pojo;

import java.util.Date;

public class Data {
    private Double rate;
    private Date rateDate;

    public Data(Double rate, Date rateDate) {
        this.rate = rate;
        this.rateDate = rateDate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    @Override
    public String toString() {
        return "Data{" +
                "rate=" + rate +
                ", rateDate=" + rateDate +
                '}';
    }

}
