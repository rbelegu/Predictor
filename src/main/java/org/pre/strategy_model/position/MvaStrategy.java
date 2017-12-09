package org.pre.strategy_model.position;

import org.pre.pojo.Data;

import org.pre.strategy_model.Signals;
import org.pre.strategy_model.pojo.Base;
import org.pre.strategy_model.pojo.Mva;

import java.util.ArrayList;
import java.util.List;

public class MvaStrategy extends Position {
    private List<Data> dataList;
    private Integer mvaNr;
    private List<Mva> mvaList;
    private List<? extends Base> baseList;

    public MvaStrategy(Integer mvaNr, List<Data> dataList) {
        mvaList = new ArrayList<>();
        baseList = mvaList;
        this.dataList = dataList;
        this.mvaNr = mvaNr;
        createPositionList();
        calculateStrategyPosition();
    }

    private void calculateStrategyPosition(){
        String lastTradingSignal = Signals.FLAT.toString();
        double lastEntryRate = 0;
        double accumulatedRealizedPL = 0;
        for (Mva mva : mvaList) {
            if (mva.getMvaRate() > 0) {
                // LONG SIGNAL
                if (mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.equals(Signals.FLAT.toString())) {
                    lastEntryRate = mva.getSpotRate();
                    lastTradingSignal = Signals.LONG.toString();
                    mva.setDirection(Signals.LONG.toString());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                } else if (mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())) {
                    accumulatedRealizedPL += (lastEntryRate - mva.getSpotRate());
                    mva.setRealizedPl((lastEntryRate - mva.getSpotRate()));
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.LONG.toString();
                    mva.setDirection(Signals.LONG.toString());
                    lastEntryRate = mva.getSpotRate();
                } else if (mva.getSpotRate() > mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())) {
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.LONG.toString());
                }

                // SHORT SIGNAL
                else if (mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.FLAT.toString())) {
                    lastEntryRate = mva.getSpotRate();
                    lastTradingSignal = Signals.SHORT.toString();
                    mva.setDirection(Signals.SHORT.toString());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                } else if (mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())) {
                    accumulatedRealizedPL += (mva.getSpotRate() - lastEntryRate);
                    mva.setRealizedPl(mva.getSpotRate() - lastEntryRate);
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.SHORT.toString();
                    mva.setDirection(Signals.SHORT.toString());
                    lastEntryRate = mva.getSpotRate();
                } else if (mva.getSpotRate() < mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())) {
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.SHORT.toString());
                }

                // FLAT SIGNAL
                else if (mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.LONG.toString())) {
                    accumulatedRealizedPL += (mva.getSpotRate() - lastEntryRate);
                    mva.setRealizedPl(mva.getSpotRate() - lastEntryRate);
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.FLAT.toString();
                    mva.setDirection(Signals.FLAT.toString());
                    lastEntryRate = 0;
                } else if (mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.SHORT.toString())) {
                    accumulatedRealizedPL += (lastEntryRate - mva.getSpotRate());
                    mva.setRealizedPl(lastEntryRate - mva.getSpotRate());
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    lastTradingSignal = Signals.FLAT.toString();
                    mva.setDirection(Signals.FLAT.toString());
                    lastEntryRate = 0;
                } else if (mva.getSpotRate() == mva.getMvaRate() && lastTradingSignal.equals(Signals.FLAT.toString())) {
                    mva.setAccumulatedRealizedPl(accumulatedRealizedPL);
                    mva.setRealizedPl(0);
                    mva.setDirection(Signals.FLAT.toString());
                }

          }
        }}


    private void createPositionList(){
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


    @Override
    public List<Mva> getStrategyPositionList(){
        return mvaList;
    }
    @Override
    public List<Base> getBasePositionList(){
        return (List<Base>) baseList;
    }

}
