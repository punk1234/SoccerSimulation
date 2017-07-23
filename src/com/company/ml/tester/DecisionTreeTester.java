package com.company.ml.tester;

import com.company.ml.decisiontree.DecisionTree;
import com.company.ml.decisiontree.DecisionTreeBuilder;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class DecisionTreeTester {

    public static Double execute(TrainingSet trainingSet, TrainingRow predictionSet){

        DecisionTree decisionTree = DecisionTreeBuilder.build(trainingSet);
        Double outcome = decisionTree.predict(predictionSet);
        return outcome;

    }

}
