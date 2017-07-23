package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class OverUnderHalfTimeProcessor  implements ResultProcessor {

    private static double value;
    private static final String over = "over";
    private static final String under = "under";

    private static final Double overValue = 1.0;
    private static final Double underValue = 3.0;

    private Map<Double,String> outcomeDoubleMapping;

    public OverUnderHalfTimeProcessor(int value){

        this.value = value;
        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(overValue,over);
        outcomeDoubleMapping.put(underValue,under);

    }

    @Override
    public String process(Result result){

        Double valueResult = getValue(result);
        return outcomeDoubleMapping.get(valueResult);

    }

    @Override
    public Double getValue(Result result){

        int homeTeamScoreHalfTime = result.getHomeTeamScoreHalfTime();
        int awayTeamScoreHalfTime = result.getAwayTeamScoreHalfTime();
        int totalGoalsHalfTime = homeTeamScoreHalfTime + awayTeamScoreHalfTime;
        if(totalGoalsHalfTime > value){
            return overValue;
        }
        else{
            return underValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
