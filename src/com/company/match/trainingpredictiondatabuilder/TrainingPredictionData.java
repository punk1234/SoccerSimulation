package com.company.match.trainingpredictiondatabuilder;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-06-20.
 */
public class TrainingPredictionData {

    private TrainingSet trainingSet;
    private TrainingRow predictionSet;

    public TrainingPredictionData(TrainingSet trainingSet, TrainingRow predictionSet){

        this.trainingSet = trainingSet;
        this.predictionSet = predictionSet;

    }

    public TrainingSet getTrainingSet(){

        return trainingSet;

    }

    public TrainingRow getPredictionSet(){

        return predictionSet;

    }

    public TrainingPredictionData convertToClassification(){

        return null;

    }

}
