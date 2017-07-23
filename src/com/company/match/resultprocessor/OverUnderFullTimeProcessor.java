package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class OverUnderFullTimeProcessor implements ResultProcessor {

    private double value;
    private static final String over = "over";
    private static final String under = "under";

    private static final Double overValue = 0.0;
    private static final Double underValue = 1.0;

    private Map<Double,String> outcomeDoubleMapping;

    public OverUnderFullTimeProcessor(int value){

        this.value = value;
        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(overValue,over);
        outcomeDoubleMapping.put(underValue,under);

    }

    @Override
    public String process(Result result) {

        Double valueResult = getValue(result);
        return outcomeDoubleMapping.get(valueResult);

    }

    @Override
    public Double getValue(Result result){

        int homeTeamScoreFullTime = result.getHomeTeamScoreFullTime();
        int awayTeamScoreFullTime = result.getAwayTeamScoreFullTime();
        int totalGoalsFullTime = homeTeamScoreFullTime + awayTeamScoreFullTime;
        if (totalGoalsFullTime > value) {
            return overValue;
        }
        else {
            return underValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
