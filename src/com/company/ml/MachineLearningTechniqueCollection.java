package com.company.ml;

import com.company.TechniquePrediction;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public class MachineLearningTechniqueCollection {

    private List<MachineLearningTechnique> machineLearningTechniques;

    public MachineLearningTechniqueCollection(){

        machineLearningTechniques = new ArrayList<>();

    }

    public void add(MachineLearningTechnique machineLearningTechnique){

        machineLearningTechniques.add(machineLearningTechnique);

    }

    public MachineLearningTechnique get(int index){

        if(index < count()){
            return machineLearningTechniques.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public void learn(TrainingSet trainingSet){

        for(MachineLearningTechnique machineLearningTechnique : machineLearningTechniques){
            machineLearningTechnique.learn(trainingSet);
        }

    }

    public TechniquePrediction predict(TrainingRow trainingRow){

        TechniquePrediction techniquePrediction = new TechniquePrediction();
        for(MachineLearningTechnique machineLearningTechnique : machineLearningTechniques){
            Double result = machineLearningTechnique.predict(trainingRow);
            String techniqueName = machineLearningTechnique.getName();
            techniquePrediction.add(techniqueName,result.toString());
        }
        return techniquePrediction;

    }

    public int count(){

        return machineLearningTechniques.size();

    }

}
