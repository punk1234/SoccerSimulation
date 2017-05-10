package com.company;

import com.company.api.FootballApi;
import com.company.ml.naivebayes.NaiveBayes;

public class Main {

    public static void main(String[] args) {

        // write your code here

        Double[][] trainingSet = new Double[8][5];
        Double[] predictionSet = new Double[4];

        trainingSet[0][0] = 1.0;
        trainingSet[1][0] = 1.0;
        trainingSet[2][0] = 1.0;
        trainingSet[3][0] = 0.0;
        trainingSet[4][0] = 0.0;
        trainingSet[5][0] = 0.0;
        trainingSet[6][0] = 0.0;
        trainingSet[7][0] = 1.0;

        trainingSet[0][1] = 0.0;
        trainingSet[1][1] = 1.0;
        trainingSet[2][1] = 0.0;
        trainingSet[3][1] = 1.0;
        trainingSet[4][1] = 0.0;
        trainingSet[5][1] = 1.0;
        trainingSet[6][1] = 1.0;
        trainingSet[7][1] = 1.0;

        trainingSet[0][2] = 0.0;
        trainingSet[1][2] = 1.0;
        trainingSet[2][2] = 2.0;
        trainingSet[3][2] = 0.0;
        trainingSet[4][2] = 1.0;
        trainingSet[5][2] = 2.0;
        trainingSet[6][2] = 2.0;
        trainingSet[7][2] = 0.0;

        trainingSet[0][3] = 1.0;
        trainingSet[1][3] = 0.0;
        trainingSet[2][3] = 1.0;
        trainingSet[3][3] = 1.0;
        trainingSet[4][3] = 0.0;
        trainingSet[5][3] = 1.0;
        trainingSet[6][3] = 0.0;
        trainingSet[7][3] = 1.0;

        trainingSet[0][4] = 0.0;
        trainingSet[1][4] = 1.0;
        trainingSet[2][4] = 1.0;
        trainingSet[3][4] = 1.0;
        trainingSet[4][4] = 0.0;
        trainingSet[5][4] = 1.0;
        trainingSet[6][4] = 0.0;
        trainingSet[7][4] = 1.0;

        predictionSet[0] = 1.0;
        predictionSet[1] = 0.0;
        predictionSet[2] = 0.0;
        predictionSet[3] = 0.0;

//        try {
//            System.out.println("OUTCOME" + NaiveBayes.predict(trainingSet,predictionSet));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            FootballApi.retrieveData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
