package com.company.predictionanalyzer;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-07-06.
 */
public class AnalysisReport {

    private Map<Integer,PredictionAnalysis> leagueAnalysisMap;

    public AnalysisReport(){

        leagueAnalysisMap = new HashMap<>();

    }

    public void add(int leagueId, PredictionAnalysis predictionAnalysis){

        leagueAnalysisMap.put(leagueId,predictionAnalysis);

    }

    public void remove(int leagueId){

        leagueAnalysisMap.remove(leagueId);

    }

}
