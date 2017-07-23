package com.company;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-17.
 */
public class TechniquePrediction {

    private Map<String,String> resultPredictions;

    public TechniquePrediction(){

        resultPredictions = new HashMap<>();

    }

    public void add(String technique, String result){

        resultPredictions.put(technique,result);

    }

    public String get(String technique){

        if(resultPredictions.containsKey(technique)){
            return resultPredictions.get(technique);
        }
        throw new IllegalArgumentException();

    }

    public List<String> getTechniques(){

        Set<String> techniqueSet = resultPredictions.keySet();
        List<String> techniqueList = new ArrayList<>(techniqueSet);

        return techniqueList;

    }

}
