package com.company.ml.randomforest;

import com.company.ml.decisiontree.*;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class RandomForestBuilder {

    public static RandomForest build(Double[][] trainingSet, int numberOfDecisionTrees){

        RandomForest randomForest = new RandomForest();
        for(int count = 0; count < numberOfDecisionTrees; count++){
            Double[][] randomTrainingSet = getRandomTrainingSet(trainingSet);
            DecisionTree decisionTree = DecisionTreeBuilder.build(randomTrainingSet);
            randomForest.add(decisionTree);
        }

        return randomForest;

    }

    private static Double[][] getRandomTrainingSet(Double[][] trainingSet){

        return null;

    }

}
