package com.company.ml.linearmodel;

import java.util.*;

import com.company.ml.linearmodel.factory.VariableValueMappingFactory;
import com.company.ml.linearmodel.gradientdescent.BatchGradientDescent;
import com.company.ml.linearmodel.polynomial.*;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-05-13.
 */
public class OneVersusAll {

    private static Map<Double,Polynomial> outcomePolynomialMapping = new HashMap<>();

    public static Map<Double,Polynomial> get(){

        return outcomePolynomialMapping;

    }

    public static void execute(TrainingSet trainingSet, String variablePrefix, int maximumDegree){

        int outcomeColumnIndex = trainingSet.getNumberOfColumns() - 1;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(outcomeColumnIndex); // getOutcomes(trainingSet);
        if(outcomes.size() > 2){
            int featureCount = trainingSet.getNumberOfColumns() - 1;
            // int maximumDegree = 2;
            for(Double outcome : outcomes){
                // create current training set of current outcome versus the rest
                TrainingSet currentTrainingSet = duplicate(trainingSet);
                updateTrainingSetUsingOutcome(currentTrainingSet,outcome);
                // use gradient descent to build the model
                Polynomial polynomial = PolynomialFactory.create(featureCount,maximumDegree,variablePrefix);
                BatchGradientDescent batchGradientDescent = new BatchGradientDescent();
                batchGradientDescent.execute(currentTrainingSet,polynomial);
                // use the model to predict the probability of the outcome
                // add outcome with probability to the map
                outcomePolynomialMapping.put(outcome,polynomial);
            }

            // choose the outcome with the highest probability
        }

    }

    public static double predict(TrainingRow predictionSet, String variablePrefix){

        Map<Double,Double> outcomeProbabilityMapping = new HashMap<>();
        // String variablePrefix = "x";
        Map<String,Double> variableMapping = VariableValueMappingFactory.get(predictionSet,variablePrefix);
        List<Double> outcomes = new ArrayList<>(outcomePolynomialMapping.keySet());
        for(Double outcome : outcomes){
            Polynomial polynomial = outcomePolynomialMapping.get(outcome);
            double polynomialValue = polynomial.getValue(variableMapping);
            double probability = SigmoidFunction.pass(polynomialValue);
            outcomeProbabilityMapping.put(outcome,probability);
        }

        return getOutcomeWithHighestProbability(outcomeProbabilityMapping);

    }

    private static double getOutcomeWithHighestProbability(Map<Double,Double> outcomeProbabilityMapping){

        Set<Double> keys = outcomeProbabilityMapping.keySet();
        List<Double> keyList = new ArrayList<>(keys);
        Double currentOutcome = keyList.get(0);
        Double currentProbability = outcomeProbabilityMapping.get(currentOutcome);
        keyList.remove(0);
        for(Double key : keyList){
            double keyProbability = outcomeProbabilityMapping.get(key);
            if(keyProbability > currentProbability){
                currentProbability = keyProbability;
                currentOutcome = key;
            }
        }
        return currentOutcome;

    }

//    private static Map<String,Double> createVariableValueMapping(TrainingRow trainingRow, String variablePrefix){
//
//        Map<String,Double> mappings = new HashMap<>();
//        int size = trainingRow.getSize();
//        for(int index = 0; index < size; index++){
//            String variableName = variablePrefix + (index + 1);
//            double variableValue = trainingRow.get(index); // [index-1];
//            mappings.put(variableName,variableValue);
//        }
//
//        return mappings;
//
//    }

    private static void updateTrainingSetUsingOutcome(TrainingSet trainingSet, double outcome){

        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            if(trainingSet.get(rowIndex,lastColumnIndex) == outcome){
                trainingSet.set(rowIndex,lastColumnIndex,1.0);
            }
            else{
                trainingSet.set(rowIndex,lastColumnIndex,0.0);
            }
        }

    }

    private static TrainingSet duplicate(TrainingSet trainingSet){

        int rowLength = trainingSet.getNumberOfRows();
        int columnLength = trainingSet.getNumberOfColumns();
        TrainingSet duplicateTrainingSet = new TrainingSet(rowLength,columnLength);
        for(int rowIndex = 0; rowIndex < rowLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < columnLength; columnIndex++){
                double value = trainingSet.get(rowIndex,columnIndex);
                duplicateTrainingSet.set(rowIndex,columnIndex,value); //[rowIndex][columnIndex] = trainingSet[rowIndex][columnIndex];
            }
        }
        return duplicateTrainingSet;

    }

//    private static List<Double> getOutcomes(double[][] trainingSet){
//
//        if(trainingSet.length > 0){
//            int lastColumnIndex = trainingSet[0].length - 1;
//            List<Double> outcomes = new ArrayList<>();
//            for(double[] trainingRow : trainingSet){
//                double outcome = trainingRow[lastColumnIndex];
//                if(!outcomes.contains(outcome)){
//                    outcomes.add(outcome);
//                }
//            }
//            return outcomes;
//        }
//        throw new IllegalArgumentException();
//
//    }

}
