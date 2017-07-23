package com.company.ml.tester;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;
import com.company.ml.randomforest.RandomForest;
import com.company.ml.randomforest.RandomForestBuilder;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class RandomForestTester {

    private static int numberOfDecisionTrees = 5;

    public static Double execute(TrainingSet trainingSet, TrainingRow predictionSet){

        RandomForest randomForest = RandomForestBuilder.build(trainingSet,numberOfDecisionTrees);
        Double outcome =  randomForest.predict(predictionSet);
        return outcome;

    }

}
