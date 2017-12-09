package org.pre.strategy_model.factory;

import org.pre.pojo.Data;
import org.pre.strategy_model.Strategies;

import java.util.List;

public class StrategySolverFactory {

    public static StrategySolver getStrategySolver(List<Data> dataList, String parameter, int strategyId, String strategyType){
        if(Strategies.MVA.toString().equals(strategyType)){
            return new MvaSolver(dataList, parameter, strategyId);
        }
        return null;
    }

}
