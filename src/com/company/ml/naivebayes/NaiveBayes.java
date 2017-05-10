package com.company.ml.naivebayes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-04-15.
 */
public class NaiveBayes {


    public static Double predict(Double[][] trainingSet, Double[] predictionSet) throws Exception {

        List<Double> outcomes = getOutcomes(trainingSet);
        OutcomeProbabilitySet outcomeProbabilitySet = new OutcomeProbabilitySet(outcomes.size());
        for(Double outcome : outcomes){
            double probability = getOutcomeProbability(trainingSet,outcome);
            System.out.println("probability " + probability);
            for(int featureIndex = 0;featureIndex < predictionSet.length;featureIndex++){
                probability = probability * getProbabilityWithOutcomeAndFeature(trainingSet,outcome,featureIndex,predictionSet[featureIndex]);
            }
            OutcomeProbability outcomeProbability = new OutcomeProbability(outcome,probability);
            outcomeProbabilitySet.add(outcomeProbability);
            System.out.println("OUTCOME " + outcomeProbability.getOutcome());
            System.out.println("PROBABILITY " + outcomeProbability.getProbability());
        }

        return outcomeProbabilitySet.getOutcomeWithMaximumProbability().getOutcome();

    }

    private static List<Double> getOutcomes(Double[][] trainingSet){

        List<Double> outcomes = new ArrayList<>();
        int lastColumnIndex = trainingSet[0].length - 1;
        for(int i = 0; i < trainingSet.length; i++){
            Double outcome = trainingSet[i][lastColumnIndex];
            if(!outcomes.contains(outcome)){
                outcomes.add(outcome);
            }
        }
        return outcomes;

    }

    private static double getOutcomeProbability(Double[][] trainingSet, Double outcome){

        int lastColumnIndex = trainingSet[0].length - 1;
        int trainingSetWithOutcomeCount = 0;
        for(int i = 0; i < trainingSet.length; i++){
            double outcomeValue = trainingSet[i][lastColumnIndex];
            if(outcome == outcomeValue){
                trainingSetWithOutcomeCount = trainingSetWithOutcomeCount + 1;
            }
        }

        double probability = (double)(trainingSetWithOutcomeCount) / (double)(trainingSet.length);
        return probability;

    }

    private static double getProbabilityWithOutcomeAndFeature(Double[][] trainingSet, Double outcome, int featureColumnIndex, Double featureColumnValue){

        int lastColumnIndex = trainingSet[0].length - 1;
        int trainingSetWithOutcomeCount = 0;
        int trainingSetWithOutcomeAndFeatureCount = 0;
        for(int i = 0; i < trainingSet.length; i++){
            double outcomeValue = trainingSet[i][lastColumnIndex];
            if(outcome == outcomeValue){
                trainingSetWithOutcomeCount = trainingSetWithOutcomeCount + 1;
                double trainingSetItem = trainingSet[i][featureColumnIndex];
                if(trainingSetItem == featureColumnValue){
                    trainingSetWithOutcomeAndFeatureCount = trainingSetWithOutcomeAndFeatureCount + 1;
                }
            }
        }

        double probability = (double)(trainingSetWithOutcomeAndFeatureCount) / (double)trainingSetWithOutcomeCount;
        return probability;

    }

}
