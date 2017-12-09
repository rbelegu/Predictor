package org.pre.strategy_model.position;

import org.pre.strategy_model.pojo.Base;

import java.util.List;

public abstract class Position {
    public abstract List getStrategyPositionList();
    public abstract List<Base> getBasePositionList();
}
