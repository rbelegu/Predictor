package org.pre.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

public class DataSetModel {
    private Executor exec;


    @Autowired
    public void setExecutor(Executor exec){
        this.exec = exec;
    }




}
