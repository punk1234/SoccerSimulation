package com.company.ml.naivebayes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AKEJU  FATAI on 2017-04-27.
 */
public class NaiveBayesTest {

    @Test
    public void checkForEmptyTrainingSetAndPredictionSet() throws Exception {

        Double[][] trainingSet = new Double[5][5];
        Double[] predictionSet = new Double[5];

        try {
            Double actual = NaiveBayes.predict(trainingSet,predictionSet);
        }catch (Exception e){}


    }
}