package com.company.predictionanalyzer;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-26.
 */
public class CategoryStatCollection {

    private Map<String,PredictionStats> categoryStats;

    public CategoryStatCollection(){

        categoryStats = new HashMap<>();

    }

    public void add(String category, PredictionStats predictionStats){

        categoryStats.put(category,predictionStats);

    }

    public PredictionStats get(String category){

        return categoryStats.get(category);

    }

    public List<String> getCategorys(){

        Set<String> techniques = categoryStats.keySet();
        List<String> techniqueList = new ArrayList<>(techniques);
        return techniqueList;

    }

    public boolean containsKey(String key){

        if(categoryStats.containsKey(key)){
            return true;
        }
        return false;

    }

}
