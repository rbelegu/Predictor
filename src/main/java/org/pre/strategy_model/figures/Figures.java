package org.pre.strategy_model.figures;


import org.pre.strategy_model.pojo.Base;


import java.util.List;

public class Figures {
    private static final Integer DAY_PER_YEAR_WITHOUT_WE = 260;
    private double averageYield = 0;
    private double accumulatedPL = 0;
    private int countProfitTrades = 0;
    private int countLossTrades = 0;
    private double maxProfitTrade = 0;
    private double maxLossTrade = 0;
    private List<Base> baseList;

    public Figures(List<Base> baseList) {
        this.baseList = baseList;
        calculateAccumulatedPL();
        calculateAverageYield();
        countProfitAndLossTrades();
        calculateMaxProfitandLossTrade();
    }

    private void calculateAccumulatedPL(){
        try {
            accumulatedPL = baseList.get(baseList.size() - 1).getAccumulatedRealizedPl();
        }catch (ArrayIndexOutOfBoundsException ex){
            throw new ArrayIndexOutOfBoundsException("Your Data Set is empty");
        }
    }

    private void calculateAverageYield(){
        int days = 0;
        double accumulatedSpotRate = 0;
        for (Base base : baseList){
            if (base.getAccumulatedRealizedPl() != 0 || days > 0){
                days += 1;
                accumulatedSpotRate += base.getSpotRate();
            }
        }
        if(days > 0){
            averageYield =  accumulatedPL * DAY_PER_YEAR_WITHOUT_WE / days * 100 / (accumulatedSpotRate / days);
        }
    }

    private  void countProfitAndLossTrades(){
        for (Base base : baseList) {
            if (base.getRealizedPl() > 0) {
                countProfitTrades += 1;
            }
            else if (base.getRealizedPl() < 0) {
                countLossTrades += 1;
            }
        }
    }
    private  void calculateMaxProfitandLossTrade(){
        for (Base base : baseList) {
            if (base.getRealizedPl() > maxProfitTrade) {
                maxProfitTrade = base.getRealizedPl();
            }
            else if (base.getRealizedPl() < maxLossTrade) {
                maxLossTrade = base.getRealizedPl();
            }
        }
    }

    public double getAverageYield() {
        return averageYield;
    }
    public double getAccumulatedPL(){
        return accumulatedPL;
    }
    public int getCountProfitTrades() {
        return countProfitTrades;
    }
    public int getCountLossTrades() {
        return countLossTrades;
    }
    public double getMaxProfitTrade() {
        return maxProfitTrade;
    }
    public double getMaxLossTrade() {
        return maxLossTrade;
    }
}
