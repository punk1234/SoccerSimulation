package com.company.ml.tester;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;
import com.company.ml.naivebayes.NaiveBayes;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class NaiveBayesTester {

    public static Double execute(TrainingSet trainingSet, TrainingRow predictionSet){

        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.learn(trainingSet);
        Double outcome = naiveBayes.predict(predictionSet);

        return outcome;

    }

}
