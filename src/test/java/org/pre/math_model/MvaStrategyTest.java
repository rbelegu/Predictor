package org.pre.math_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.pre.pojo.Data;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MvaStrategyTest {
    @Test
    public void getAccumulatedPL() throws Exception {
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        dataList.add(new Data());


        MvaStrategy mvaStrategy = new MvaStrategy(2, dataList);
        Double accumulatedPL = mvaStrategy.getAccumulatedPL();
        assertEquals("Stock value not correct", 0, accumulatedPL, .00001);
    }

}