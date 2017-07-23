package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class EvenOddProcessor implements ResultProcessor {

    private static final String evenGoals = "even";
    private static final String oddGoals = "odd";

    private static final Double evenGoalsValue = 0.0;
    private static final Double oddGoalsValue = 1.0;

    private Map<Double,String> outcomeDoubleMapping;

    public EvenOddProcessor(){

        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(evenGoalsValue,evenGoals);
        outcomeDoubleMapping.put(oddGoalsValue,oddGoals);

    }

    @Override
    public String process(Result result){

        Double valueResult = getValue(result);
        return outcomeDoubleMapping.get(valueResult);

    }

    @Override
    public Double getValue(Result result){

        int homeTeamScore = result.getHomeTeamScoreFullTime();
        int awayTeamScore = result.getAwayTeamScoreFullTime();
        int totalGoals = homeTeamScore + awayTeamScore;
        if(totalGoals % 2 == 0){
            return evenGoalsValue;
        }
        else{
            return oddGoalsValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
