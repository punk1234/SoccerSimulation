package com.company.ml.naivebayes;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-04-15.
 */
public class NaiveBayes implements MachineLearningTechnique {

    private TrainingSet trainingSet;

    public NaiveBayes(){

    }

    @Override
    public void learn(TrainingSet trainingSet){

        this.trainingSet = trainingSet;

    }

    @Override
    public Double predict(TrainingRow predictionSet) {

        int outcomeColumnIndex = trainingSet.getNumberOfColumns() - 1;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(outcomeColumnIndex); // getOutcomes(trainingSet);
        OutcomeProbabilitySet outcomeProbabilitySet = new OutcomeProbabilitySet(outcomes.size());
        for(Double outcome : outcomes){
            double probability = getOutcomeProbability(trainingSet,outcome);
            System.out.println("probability " + probability);
            for(int featureIndex = 0;featureIndex < predictionSet.getSize();featureIndex++){
                probability = probability * getProbabilityWithOutcomeAndFeature(trainingSet,outcome,featureIndex,predictionSet.get(featureIndex));
            }
            OutcomeProbability outcomeProbability = new OutcomeProbability(outcome,probability);
            try {
                outcomeProbabilitySet.add(outcomeProbability);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("OUTCOME " + outcomeProbability.getOutcome());
            System.out.println("PROBABILITY " + outcomeProbability.getProbability());
        }

        return outcomeProbabilitySet.getOutcomeWithMaximumProbability().getOutcome();

    }

    @Override
    public String getName() {
        return "naive_bayes";
    }

    private static double getOutcomeProbability(TrainingSet trainingSet, Double outcome){

        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        int trainingSetWithOutcomeCount = 0;
        for(int i = 0; i < trainingSet.getNumberOfRows(); i++){
            double outcomeValue = trainingSet.get(i,lastColumnIndex); // trainingSet[i][lastColumnIndex];
            if(outcome == outcomeValue){
                trainingSetWithOutcomeCount = trainingSetWithOutcomeCount + 1;
            }
        }

        double probability = (double)(trainingSetWithOutcomeCount) / (double)(trainingSet.getNumberOfRows());
        return probability;

    }

    private static double getProbabilityWithOutcomeAndFeature(TrainingSet trainingSet, Double outcome, int featureColumnIndex, Double featureColumnValue){

        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        int trainingSetWithOutcomeCount = 0;
        int trainingSetWithOutcomeAndFeatureCount = 0;
        for(int i = 0; i < trainingSet.getNumberOfRows(); i++){
            double outcomeValue = trainingSet.get(i,lastColumnIndex); // trainingSet[i][lastColumnIndex];
            if(outcome == outcomeValue){
                trainingSetWithOutcomeCount = trainingSetWithOutcomeCount + 1;
                double trainingSetItem = trainingSet.get(i,featureColumnIndex); // trainingSet[i][featureColumnIndex];
                if(trainingSetItem == featureColumnValue){
                    trainingSetWithOutcomeAndFeatureCount = trainingSetWithOutcomeAndFeatureCount + 1;
                }
            }
        }

        double probability = (double)(trainingSetWithOutcomeAndFeatureCount) / (double)trainingSetWithOutcomeCount;
        return probability;

    }

}
