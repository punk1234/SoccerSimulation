package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class StraightWinningProcessor implements ResultProcessor {

    private static final String homeWin = "1";
    private static final String draw = "x";
    private static final String awayWin = "2";

    private static final Double homeWinValue = 1.0;
    private static final Double drawValue = 2.0;
    private static final Double awayWinValue = 3.0;

    private Map<Double,String> outcomeDoubleMapping;

    public StraightWinningProcessor(){

        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(homeWinValue,homeWin);
        outcomeDoubleMapping.put(drawValue,draw);
        outcomeDoubleMapping.put(awayWinValue,awayWin);

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
        if(homeTeamScore > awayTeamScore){
            return homeWinValue;
        }
        else if(homeTeamScore < awayTeamScore){
            return awayWinValue;
        }
        else{
            return drawValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
