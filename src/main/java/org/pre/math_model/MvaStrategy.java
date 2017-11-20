package org.pre.math_model;

import javafx.collections.ObservableList;
import org.pre.pojo.Data;

public class MvaStrategy {

    private ObservableList<Data> test;
    private Integer mva;
    public MvaStrategy(Integer mva, ObservableList<Data> dataRun) {
        test = dataRun;
        this.mva = mva;
    }


}
