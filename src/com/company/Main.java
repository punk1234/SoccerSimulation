package com.company;

import com.company.api.model.FootballApi;
import com.company.api.retriever.DBTester;
import com.company.api.retriever.DatabaseUpdater;
import com.company.db.DBFixtureRetriever;
import com.company.db.DBResultRetriever;
import com.company.db.MongoDB;
import com.company.db.model.DBFixture;
import com.company.db.model.DBFixtureGroup;
import com.company.db.test.DBTestBuilder;
import com.company.db.test.TestRunner;
import com.company.match.resultcategory.ResultCategoryCollection;
import com.company.match.resultcategory.ResultCategoryCollectionBuilder;
import com.company.ml.decisiontree.DecisionTreeBuilder;
import com.company.ml.decisiontree.*;
import com.company.ml.linearmodel.OneVersusAll;
import com.company.ml.linearmodel.gradientdescent.BatchGradientDescent;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;
import com.company.ml.naivebayes.NaiveBayes;

import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.linearmodel.polynomial.PolynomialFactory;
import com.company.ml.linearmodel.LinearModel;
import com.company.predictionanalyzer.PredictionAnalysis;
import com.company.predictionanalyzer.PredictionAnalysisPrinter;
import com.company.predictionanalyzer.PredictionAnalyzer;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // write your code here

        double[][] trainingSet = new double[8][5];
        double[] predictionSet = new double[4];

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
        //trainingSet[2][4] = 1.0;
        trainingSet[2][4] = 2.0;
        trainingSet[3][4] = 1.0;
        trainingSet[4][4] = 0.0;
        trainingSet[5][4] = 1.0;
        //trainingSet[6][4] = 0.0;
        trainingSet[6][4] = 2.0;
        trainingSet[7][4] = 1.0;

        predictionSet[0] = 1.0;
        predictionSet[1] = 1.0;
        predictionSet[2] = 1.0;
        predictionSet[3] = 1.0;

//        try {
//            System.out.println("OUTCOME" + NaiveBayes.predict(trainingSet,predictionSet));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            FootballApi.retrieveData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        TrainingSet linearTrainingSet = new TrainingSet(5,2);

        TrainingRow trainingRow1 = new TrainingRow(2);
        TrainingRow trainingRow2 = new TrainingRow(2);
        TrainingRow trainingRow3 = new TrainingRow(2);
        TrainingRow trainingRow4 = new TrainingRow(2);
        TrainingRow trainingRow5 = new TrainingRow(2);

        trainingRow1.set(0,1.0);
        trainingRow1.set(1,1.0);
        trainingRow2.set(0,2.0);
        trainingRow2.set(1,2.0);
        trainingRow3.set(0,3.0);
        trainingRow3.set(1,3.0);
        trainingRow4.set(0,4.0);
        trainingRow4.set(1,4.0);
        trainingRow5.set(0,5.0);
        trainingRow5.set(1,5.0);

        linearTrainingSet.set(0,trainingRow1);
        linearTrainingSet.set(1,trainingRow2);
        linearTrainingSet.set(2,trainingRow3);
        linearTrainingSet.set(3,trainingRow4);
        linearTrainingSet.set(4,trainingRow5);

//        linearTrainingSet[0][0] = 1;
//        linearTrainingSet[1][0] = 2;
//        linearTrainingSet[2][0] = 3;
//        linearTrainingSet[3][0] = 4;
//        linearTrainingSet[4][0] = 5;
//
//        linearTrainingSet[0][1] = 1;
//        linearTrainingSet[1][1] = 2;
//        linearTrainingSet[2][1] = 3;
//        linearTrainingSet[3][1] = 4;
//        linearTrainingSet[4][1] = 5;

//        BatchGradientDescent batchGradientDescent = new BatchGradientDescent();
//        LinearModel linearModel = new LinearModel(batchGradientDescent,1,"x");
//        linearModel.learn(linearTrainingSet);

        //OneVersusAll.execute(trainingSet);
        //System.out.println(OneVersusAll.predict(predictionSet));

        Double[][] dTrainingSet = new Double[14][5];

//        dTrainingSet[0][0] = 1.0;
//        dTrainingSet[1][0] = 1.0;
//        dTrainingSet[2][0] = 3.0;
//        dTrainingSet[3][0] = 1.0;
//        dTrainingSet[4][0] = 2.0;
//        dTrainingSet[5][0] = 3.0;
//        dTrainingSet[6][0] = 1.0;
//        dTrainingSet[7][0] = 2.0;
//        dTrainingSet[8][0] = 3.0;
//        dTrainingSet[9][0] = 1.0;
//        dTrainingSet[10][0] = 2.0;
//        dTrainingSet[11][0] = 3.0;
//        dTrainingSet[12][0] = 1.0;
//        dTrainingSet[13][0] = 2.0;

        dTrainingSet[0][0] = 0.0;
        dTrainingSet[1][0] = 0.0;
        dTrainingSet[2][0] = 0.0;
        dTrainingSet[3][0] = 0.0;
        dTrainingSet[4][0] = 0.0;
        dTrainingSet[5][0] = 1.0;
        dTrainingSet[6][0] = 1.0;
        dTrainingSet[7][0] = 1.0;
        dTrainingSet[8][0] = 1.0;
        dTrainingSet[9][0] = 1.0;
        dTrainingSet[10][0] = 1.0;
        dTrainingSet[11][0] = 0.0;
        dTrainingSet[12][0] = 0.0;
        dTrainingSet[13][0] = 1.0;

        dTrainingSet[0][1] = 0.0;
        dTrainingSet[1][1] = 0.0;
        dTrainingSet[2][1] = 1.0;
        dTrainingSet[3][1] = 2.0;
        dTrainingSet[4][1] = 2.0;
        dTrainingSet[5][1] = 2.0;
        dTrainingSet[6][1] = 1.0;
        dTrainingSet[7][1] = 0.0;
        dTrainingSet[8][1] = 0.0;
        dTrainingSet[9][1] = 2.0;
        dTrainingSet[10][1] = 0.0;
        dTrainingSet[11][1] = 1.0;
        dTrainingSet[12][1] = 1.0;
        dTrainingSet[13][1] = 2.0;

        dTrainingSet[0][2] = 1.0;
        dTrainingSet[1][2] = 1.0;
        dTrainingSet[2][2] = 1.0;
        dTrainingSet[3][2] = 1.0;
        dTrainingSet[4][2] = 0.0;
        dTrainingSet[5][2] = 0.0;
        dTrainingSet[6][2] = 0.0;
        dTrainingSet[7][2] = 1.0;
        dTrainingSet[8][2] = 0.0;
        dTrainingSet[9][2] = 0.0;
        dTrainingSet[10][2] = 0.0;
        dTrainingSet[11][2] = 1.0;
        dTrainingSet[12][2] = 0.0;
        dTrainingSet[13][2] = 1.0;

        dTrainingSet[0][3] = 0.0;
        dTrainingSet[1][3] = 1.0;
        dTrainingSet[2][3] = 0.0;
        dTrainingSet[3][3] = 0.0;
        dTrainingSet[4][3] = 0.0;
        dTrainingSet[5][3] = 1.0;
        dTrainingSet[6][3] = 1.0;
        dTrainingSet[7][3] = 0.0;
        dTrainingSet[8][3] = 0.0;
        dTrainingSet[9][3] = 0.0;
        dTrainingSet[10][3] = 1.0;
        dTrainingSet[11][3] = 1.0;
        dTrainingSet[12][3] = 0.0;
        dTrainingSet[13][3] = 1.0;

        dTrainingSet[0][4] = 0.0;
        dTrainingSet[1][4] = 0.0;
        dTrainingSet[2][4] = 1.0;
        dTrainingSet[3][4] = 1.0;
        dTrainingSet[4][4] = 1.0;
        dTrainingSet[5][4] = 0.0;
        dTrainingSet[6][4] = 1.0;
        dTrainingSet[7][4] = 0.0;
        dTrainingSet[8][4] = 1.0;
        dTrainingSet[9][4] = 1.0;
        dTrainingSet[10][4] = 1.0;
        dTrainingSet[11][4] = 1.0;
        dTrainingSet[12][4] = 1.0;
        dTrainingSet[13][4] = 0.0;

        //DecisionTree decisionTree = DecisionTreeBuilder.build(dTrainingSet);

        //MongoDB.connect();

//        try {
//            DatabaseUpdater.update();
//            //DBTester.test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        DBResultRetriever dbResultRetriever = new DBResultRetriever();
//        //List<DBFixture> dbFixtureList = dbFixtureRetriever.getFixtures(426);
//        DBFixtureGroup dbFixtureGroup = dbResultRetriever.getResults(426);
//        System.out.println("Result count " + dbFixtureGroup.size());

//        MatchPredictor matchPredictor = new MatchPredictor(426,"Chelsea FC","Middlesbrough FC");
//        matchPredictor.predict();

        // DBTestBuilder.build();

        TestRunner.run();

        ResultCategoryCollection resultCategoryCollection = ResultCategoryCollectionBuilder.build();
        PredictionAnalyzer predictionAnalyzer = new PredictionAnalyzer(resultCategoryCollection);
        PredictionAnalysis predictionAnalysis = predictionAnalyzer.start();
        PredictionAnalysisPrinter.print(predictionAnalysis);

    }
}
