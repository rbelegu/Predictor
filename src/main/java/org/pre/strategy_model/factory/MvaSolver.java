package org.pre.strategy_model.factory;

import org.pre.pojo.Data;
import org.pre.pojo.Result;

import org.pre.strategy_model.figures.Figures;
import org.pre.strategy_model.position.MvaStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MvaSolver extends StrategySolver{

    private List<Data> dataList;
    private List<Result> resultList;
    private String parameter;
    private int minMva;
    private int maxMva;
    private int strategyId;
    private final static String SEPARATOR =";";

    MvaSolver(List<Data> dataList, String parameter, int strategyId){
        resultList = new ArrayList<>();
        this.dataList = dataList;
        this.parameter = parameter;
        this.strategyId = strategyId;
        setSplitParameter();
        dataList.sort(Comparator.comparing(Data::getRateDate));
        createResultList();
    }

    private void setSplitParameter(){
        String string = parameter;
        String[] parts = string.split(SEPARATOR);
        minMva = Integer.parseInt(parts[0]);
        maxMva = Integer.parseInt(parts[1]);
    }

    private void createResultList(){
        for(int i=minMva; i<=maxMva; i++){
            Result result = new Result();
            MvaStrategy mvaStrategy = new MvaStrategy(i, dataList);
            Figures figures = new Figures(mvaStrategy.getBasePositionList());
            result.setParameter(Integer.toString(i));
            result.setStrategy_id(strategyId);
            result.setAccumulatedPl(figures.getAccumulatedPL());
            result.setAverageYield(figures.getAverageYield());
            result.setMaxLossTrade(figures.getMaxLossTrade());
            result.setMaxProfitTrade(figures.getMaxProfitTrade());
            result.setCountLossTrades(figures.getCountLossTrades());
            result.setCountProfitTrades(figures.getCountProfitTrades());
            result.setTimestamp(LocalDateTime.now());
            resultList.add(result);
        }
    }


    @Override
    public List<Result> getResultList(){
        return  resultList;
    }

}
