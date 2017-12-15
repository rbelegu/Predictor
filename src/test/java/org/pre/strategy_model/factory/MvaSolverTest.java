package org.pre.strategy_model.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pre.pojo.Data;
import org.pre.pojo.Result;
import org.pre.strategy_model.pojo.Mva;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * MvaSolverTest ist ein JUnit Test welcher überprüft ob die
 * Kennzahlen der MVA Strategie korrekt berechnet wird.
 */
public class MvaSolverTest {
    private MvaSolver mvaSolver = null;


    /**
     *Test Mock, mit stetig steigenden Kursen und
     * nur einem zu berechnenden MVA.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // MVA Min and Max
        String parameter = "2;2";
        int strategyId = 1;
        List<Data> dataList = Arrays.asList(
                new Data(LocalDate.parse("2017-01-01"),1.20),
                new Data(LocalDate.parse("2017-01-02"),1.21),
                new Data(LocalDate.parse("2017-01-03"),1.22),
                new Data(LocalDate.parse("2017-01-04"),1.24),
                new Data(LocalDate.parse("2017-01-05"),1.24));

        mvaSolver = new MvaSolver(dataList, parameter, strategyId);
    }

    @Test
    public void testOnegetResultList() throws Exception {
    List<Result> resultList = mvaSolver.getResultList();
        Assert.assertEquals(1,resultList.size(),0);
        Assert.assertEquals(0.03,resultList.get(0).getAccumulatedPl(),0.001);
       Assert.assertEquals(629.0322,resultList.get(0).getAverageYield(),0.001);
    System.out.println(resultList.get(0).getAverageYield());


    }

}