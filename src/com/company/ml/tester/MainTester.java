package com.company.ml.tester;

import com.company.ml.decisiontree.DecisionTree;
import com.company.ml.knn.KNN;
import com.company.ml.linearmodel.LinearModel;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;
import com.company.ml.naivebayes.NaiveBayes;
import com.company.ml.randomforest.RandomForest;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class MainTester {

    private Double decisionTreeResult;
    private Double randomForestResult;
    private Double knnResult;
    private Double linearModelResult;
    private Double naiveBayesResult;

    public MainTester(){

    }

    public void execute(TrainingSet trainingSet, TrainingRow predictionSet){

        decisionTreeResult = DecisionTreeTester.execute(trainingSet,predictionSet);
        randomForestResult = RandomForestTester.execute(trainingSet,predictionSet);
        knnResult = KNNTester.execute(trainingSet, predictionSet);
        linearModelResult = LinearModelTester.execute(trainingSet, predictionSet);
        naiveBayesResult = NaiveBayesTester.execute(trainingSet, predictionSet);

    }

    public Double getDecisionTreeResult() {
        return decisionTreeResult;
    }

    public Double getRandomForestResult() {
        return randomForestResult;
    }

    public Double getKnnResult() {
        return knnResult;
    }

    public Double getLinearModelResult() {
        return linearModelResult;
    }

    public Double getNaiveBayesResult() {
        return naiveBayesResult;
    }

}
