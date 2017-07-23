package com.company.ml.tester;

import com.company.ml.knn.KNN;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class KNNTester {

    private static int k = 5;

    public static Double execute(TrainingSet trainingSet, TrainingRow predictionSet){

        KNN knn = new KNN(k);
        knn.learn(trainingSet);
        Double outcome =  knn.predict(predictionSet);

        return outcome;

    }

}
