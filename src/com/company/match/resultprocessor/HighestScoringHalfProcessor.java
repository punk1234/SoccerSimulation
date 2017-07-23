package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class HighestScoringHalfProcessor implements ResultProcessor {

    private static final String firstHalf = "1st";
    private static final String equal = "equal";
    private static final String secondHalf = "2nd";

    private static final Double firstHalfValue = 1.0;
    private static final Double equalValue = 2.0;
    private static final Double secondHalfValue = 3.0;

    private Map<Double,String> outcomeDoubleMapping;

    public HighestScoringHalfProcessor(){

        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(firstHalfValue,firstHalf);
        outcomeDoubleMapping.put(equalValue,equal);
        outcomeDoubleMapping.put(secondHalfValue,secondHalf);

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
        int homeTeamScoreFullTime = result.getHomeTeamScoreFullTime();
        int awayTeamScoreFullTime = result.getAwayTeamScoreFullTime();
        int totalGoalsHalfTime = homeTeamScoreHalfTime + awayTeamScoreHalfTime;
        int totalGoalsFullTime = homeTeamScoreFullTime + awayTeamScoreFullTime;
        if(totalGoalsHalfTime > totalGoalsFullTime){
            return firstHalfValue;
        }
        else if(totalGoalsHalfTime < totalGoalsFullTime){
            return secondHalfValue;
        }
        else{
            return equalValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
