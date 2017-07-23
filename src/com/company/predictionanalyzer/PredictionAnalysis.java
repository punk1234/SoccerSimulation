package com.company.predictionanalyzer;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-26.
 */
public class PredictionAnalysis {

    private Map<String,CategoryStatCollection> content;

    public PredictionAnalysis(){

        content = new HashMap<>();

    }

    public void add(String technique, CategoryStatCollection categoryStatCollection){

        content.put(technique,categoryStatCollection);

    }

    public CategoryStatCollection get(String technique){

        return content.get(technique);

    }

    public boolean containsKey(String key){

        if(content.containsKey(key)){
            return true;
        }
        return false;

    }

    public List<String> getTechniques(){

        Set<String> techniques = content.keySet();
        List<String> techniqueList = new ArrayList<>(techniques);
        return techniqueList;

    }

    public int size(){

        return content.size();

    }

    public void update(String technique, String category, boolean value){

        if(content.containsKey(technique)){
            CategoryStatCollection categoryStatCollection = content.get(technique);
            if(categoryStatCollection.containsKey(category)){
                categoryStatCollection.get(category).update(value);
            }
            else{
                PredictionStats predictionStats = new PredictionStats();
                predictionStats.update(value);
                categoryStatCollection.add(category,predictionStats);
            }
        }
        else{
            PredictionStats predictionStats = new PredictionStats();
            predictionStats.update(value);
            CategoryStatCollection categoryStatCollection = new CategoryStatCollection();
            categoryStatCollection.add(category,predictionStats);
            add(technique,categoryStatCollection);
        }

    }

}
