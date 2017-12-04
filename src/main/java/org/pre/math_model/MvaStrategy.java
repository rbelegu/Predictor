package org.pre.math_model;


import javafx.beans.property.DoubleProperty;
import org.pre.pojo.Data;
import java.util.ArrayList;
import java.util.List;


public class MvaStrategy {
    private List<Data> dataList;
    private Integer mvaNr;
    private static final Integer DAY_PER_YEAR = 260;
    private double averageYield;
    private double accumulatedPL;
    private int countProfitTrades;
    private int countLossTrades;
    private double maxProfitTrade;
    private double maxLossTrade;
    private List<Mva> mvaList;

    MvaStrategy(Integer mvaNr, List<Data> dataList) {
        mvaList = new ArrayList<>();
        this.averageYield = 0;
        this.accumulatedPL = 0;
        this.countProfitTrades = 0;
        this.countLossTrades = 0;
        this.maxProfitTrade = 0;
        this.maxLossTrade = 0;
        this.dataList = dataList;
        this.mvaNr = mvaNr;
        createMvaRateList();
        calculateMvaStrategy();
        calculateAccumulatedPL();
        calculateAverageYield();
        calculateCountLossTrades();
        calculateCountProfitTrades();
        calculateCountLossTrades();
        calculateMaxLossTrade();
        calculateProfitTrade();
    }



    private void calculateMvaStrategy(){
        String lastTradingSignal = Signals.FLAT.toString();
        double lastEntryRate = 0;
        double accumulatedRealizedPL = 0;


        for (Mva mva : mvaList){
            if(mva.getMvaRate()> 0){
                // LONG SIGNAL
                if(mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.toString().equals(Signals.FLAT.toString())){
                    lastEntryRate = mva.getSpotRate();
                    lastTradingSignal = Signals.LONG.toString();
                    mva.setDirection(Signals.LONG.toString());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                }else if(mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())){
                    accumulatedRealizedPL += (lastEntryRate - mva.getSpotRate());
                    mva.setRealizedPl((lastEntryRate - mva.getSpotRate()));
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.LONG.toString();
                    mva.setDirection(Signals.LONG.toString());
                    lastEntryRate = mva.getSpotRate();
                }else if(mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())){
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.LONG.toString());
                }

                // SHORT SIGNAL
                else if(mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.FLAT.toString())){
                    lastEntryRate = mva.getSpotRate();
                    lastTradingSignal = Signals.SHORT.toString();
                    mva.setDirection(Signals.SHORT.toString());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                }else if(mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())){
                    accumulatedRealizedPL += (mva.getSpotRate() - lastEntryRate);
                    mva.setRealizedPl(mva.getSpotRate()- lastEntryRate);
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.SHORT.toString();
                    mva.setDirection(Signals.SHORT.toString());
                    lastEntryRate = mva.getSpotRate();
                }else if(mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())){
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.SHORT.toString());
                }

                // FLAT SIGNAL
                else if(mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())){
                    accumulatedRealizedPL += (mva.getSpotRate() - lastEntryRate);
                    mva.setRealizedPl(mva.getSpotRate()- lastEntryRate);
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.FLAT.toString();
                    mva.setDirection(Signals.FLAT.toString());
                    lastEntryRate = 0;
                }else if(mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())){
                    accumulatedRealizedPL += (lastEntryRate- mva.getSpotRate());
                    mva.setRealizedPl(lastEntryRate - mva.getSpotRate());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.FLAT.toString();
                    mva.setDirection(Signals.FLAT.toString());
                    lastEntryRate = 0;
                }else if(mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.FLAT.toString())){
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.FLAT.toString());
                }

         //   System.out.println("Spot=" + mva.getSpotRate() + ";" + " MVA=" + mva.getMvaRate() + ";" + " Direction=" + mva.getDirection() + ";" + " REALIZED PL" + mva.getAccumulatedRealizedPl());
            }




        }

    }

    private void calculateAccumulatedPL(){
            accumulatedPL = mvaList.get(mvaList.size()-1).getAccumulatedRealizedPl();
    }

    public double getAccumulatedPL(){
        return accumulatedPL;
    }

    private void calculateAverageYield(){
        int days = 0;
        double accumulatedSpotRate = 0;
        for (Mva mva : mvaList){
            if (mva.getMvaRate()> 0){
                days += 1;
                accumulatedSpotRate += mva.getSpotRate();
            }
        }
        if(days > 0){
           averageYield =  accumulatedPL * DAY_PER_YEAR / days * 100 / (accumulatedSpotRate / days);
        }
    }

    public double getAverageYield() {
        return averageYield;
    }




    private  void calculateCountProfitTrades(){
        for (Mva mva : mvaList) {
            if (mva.getRealizedPl() > 0) {
                countProfitTrades += 1;
            }
        }
    }

    public int getCountProfitTrades() {
        return countProfitTrades;
    }

    public int getCountLossTrades() {
        return countLossTrades;
    }

    private  void calculateCountLossTrades(){
        for (Mva mva : mvaList) {
            if (mva.getRealizedPl() < 0) {
                countLossTrades += 1;
            }
        }
    }

    private  void calculateProfitTrade(){
        for (Mva mva : mvaList) {
            if (mva.getRealizedPl() > maxProfitTrade) {
                maxProfitTrade = mva.getRealizedPl();
            }
        }
    }

    public double getMaxProfitTrade() {
        return maxProfitTrade;
    }

    private  void calculateMaxLossTrade(){
        for (Mva mva : mvaList) {
            if (mva.getRealizedPl() < maxLossTrade) {
                maxLossTrade = mva.getRealizedPl();
            }
        }
    }

    public double getMaxLossTrade() {
        return maxLossTrade;
    }

    private void createMvaRateList(){
        ArrayList<Double> spotList = new ArrayList<>();
        double sum = 0;
        for (Data data : dataList){
            spotList.add(data.getRate());
            Mva mva = new Mva();
            mva.setRateDate(data.getRateDate());
            mva.setSpotRate(data.getRate());

            if (spotList.size() == (mvaNr)) {
                sum += spotList.get(spotList.size()-1);
                mva.setMvaRate((sum / mvaNr));
                sum -= spotList.get(0);
                spotList.remove(0);
            } else {
                sum += spotList.get(spotList.size()-1);
            }
        mvaList.add(mva);

        }

    }

    public List<Mva> getMvaList() {
        return mvaList;
    }



}
