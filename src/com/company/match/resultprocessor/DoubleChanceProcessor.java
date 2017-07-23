package com.company.match.resultprocessor;

import com.company.match.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class DoubleChanceProcessor implements ResultProcessor {

    private static final String homeWinOrDraw = "1x";
    private static final String homeOrAwayWin = "12";
    private static final String awayWinOrDraw = "x2";

    private static final Double homeWinOrDrawValue = 1.0;
    private static final Double homeOrAwayWinValue = 2.0;
    private static final Double awayWinOrDrawValue = 3.0;

    private Map<Double,String> outcomeDoubleMapping;

    public DoubleChanceProcessor(){

        outcomeDoubleMapping = new HashMap<>();
        outcomeDoubleMapping.put(homeWinOrDrawValue,homeWinOrDraw);
        outcomeDoubleMapping.put(homeOrAwayWinValue,homeOrAwayWin);
        outcomeDoubleMapping.put(awayWinOrDrawValue,awayWinOrDraw);

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
        int difference = homeTeamScore - awayTeamScore;
        if(difference == 0){
            return awayWinOrDrawValue;
        }
        else if(difference == 1){
            return homeWinOrDrawValue;
        }
        else if(difference == -1){
            return awayWinOrDrawValue;
        }
        else{
            return homeOrAwayWinValue;
        }

    }

    @Override
    public String get(Double value) {

        return outcomeDoubleMapping.get(value);

    }

}
