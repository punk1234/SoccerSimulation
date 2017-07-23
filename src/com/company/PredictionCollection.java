package com.company;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-17.
 */
public class PredictionCollection {

    private Map<String,TechniquePrediction> predictions;

    public PredictionCollection(){

        predictions = new HashMap();

    }

    public void add(String category, TechniquePrediction techniquePrediction){

        predictions.put(category,techniquePrediction);

    }

    public TechniquePrediction get(String category){

        if(predictions.containsKey(category)){
            return predictions.get(category);
        }
        throw new IllegalArgumentException();

    }

    // public String

    public int size(){

        return predictions.size();

    }

    public List<String> getCategories(){

        Set<String> categorySet = predictions.keySet();
        List<String> categoryList = new ArrayList<>(categorySet);

        return categoryList;

    }

}
