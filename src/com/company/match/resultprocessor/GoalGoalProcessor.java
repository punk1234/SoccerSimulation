package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public class GoalGoalProcessor implements ResultProcessor {

    private static final String GG = "Goal Goal";
    private static final String NG = "No Goal Goal";

    private static final Double GGValue = 0.0;
    private static final Double NGValue = 1.0;

    private Map<Double,String> outcomeDoubleMapping;

    public GoalGoalProcessor(){

        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(GGValue,GG);
        outcomeDoubleMapping.put(NGValue,NG);

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
        if(homeTeamScore > 0 && awayTeamScore > 0){
            return GGValue;
        }
        else{
            return NGValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
