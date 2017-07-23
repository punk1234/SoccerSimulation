package com.company.ml.tester;

import com.company.ml.linearmodel.LinearModel;
import com.company.ml.linearmodel.gradientdescent.GradientDescent;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import com.company.ml.linearmodel.polynomial.*;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class LinearModelTester {

    private static GradientDescent gradientDescent;
    private static String variablePrefix;
    private static int maximumDegree;

    public static Double execute(TrainingSet trainingSet, TrainingRow predictionSet){

        LinearModel linearModel = new LinearModel(gradientDescent,maximumDegree,variablePrefix);
        linearModel.learn(trainingSet);
        Double outcome = linearModel.predict(predictionSet);
        return outcome;

    }

}
