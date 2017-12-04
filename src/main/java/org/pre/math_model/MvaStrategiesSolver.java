package org.pre.math_model;

import org.pre.pojo.Data;
import org.pre.pojo.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MvaStrategiesSolver {

    private List<Data> dataList;
    private List<Result> resultList;
    private String parameter;
    private int minMva;
    private int maxMva;
    private int strategyId;
    private final static String SEPARATOR =";";

    public MvaStrategiesSolver(List<Data> dataList, String parameter, int strategyId)  {
        resultList = new ArrayList<>();
        this.dataList = dataList;
        this.parameter = parameter;
        this.strategyId = strategyId;
        setSplitParameter();
        dataList.sort((o1, o2) -> o1.getRateDate().compareTo(o2.getRateDate()));
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
            MvaStrategy mvaStrategy = new MvaStrategy(i, dataList);
            Result result = new Result();
            result.setParameter(Integer.toString(i));
            result.setStrategy_id(strategyId);
            result.setAccumulatedPl(mvaStrategy.getAccumulatedPL());
            result.setAverageYield(mvaStrategy.getAverageYield());
            result.setMaxLossTrade(mvaStrategy.getMaxLossTrade());
            result.setMaxProfitTrade(mvaStrategy.getMaxProfitTrade());
            result.setCountLossTrades(mvaStrategy.getCountLossTrades());
            result.setCountProfitTrades(mvaStrategy.getCountProfitTrades());
            result.setTimestamp(LocalDateTime.now());
            resultList.add(result);
        }


    }



    public List<Result> getResultList(){
        return  resultList;
    }
}
