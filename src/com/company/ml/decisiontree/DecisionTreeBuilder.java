package com.company.ml.decisiontree;

import com.company.ml.model.TrainingSet;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class DecisionTreeBuilder {

    // the last column of the training examples contains the outcome or result
    public static DecisionTree build(TrainingSet trainingSet){

        int outcomeColumnIndex = trainingSet.getNumberOfColumns() - 1;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(outcomeColumnIndex); // getOutcomes(trainingSet);
        Node node = new Node();
        Map<Integer,Double> featureMappings = new HashMap<>();
        build(trainingSet,featureMappings,node,outcomes);

        DecisionTree decisionTree = new DecisionTree();
        decisionTree.setRoot(node);

        return decisionTree;

    }

    private static void build(TrainingSet trainingSet, Map<Integer,Double> featureMappings, Node node, List<Double> outcomes){

        Integer feature = getFeatureToSplitOn(trainingSet,featureMappings,outcomes);

        node.setFeature(feature);
        // List<Double> categories = getCategories(trainingSet,feature);
        List<Double> categories = getCategories(trainingSet,feature,featureMappings);
        for(Double category : categories){
            featureMappings.put(feature,category);
            if(isPure(trainingSet,featureMappings)){
                node.getOutcomeMappings().put(category,getOutcome(trainingSet,featureMappings));
            }
            else{
                int featureSize = trainingSet.getNumberOfColumns() - 1;
                if(featureMappings.size() == featureSize){
                    // all features have been exhausted or reached, and still no purity condition satisfied
                    Double outcome = getOutcomeAfterAllFeaturesExhausted(trainingSet,featureMappings);
                    node.getOutcomeMappings().put(category,outcome);
                }
                else{
                    Node childNode = new Node();
                    node.getNodeMappings().put(category,childNode);
                    build(trainingSet,featureMappings,childNode,outcomes);
                }
            }
            featureMappings.remove(feature);
        }

    }

    private static Double getOutcomeAfterAllFeaturesExhausted(TrainingSet trainingSet, Map<Integer,Double> featureMappings){

        List<Double> outcomes = new ArrayList<>();
        Set<Integer> keys = featureMappings.keySet();
        int outcomeIndex = trainingSet.getNumberOfColumns() - 1;
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            Double value = trainingSet.get(rowIndex,outcomeIndex);
            boolean status = true;
            for(Integer key : keys){
                if(!trainingSet.get(rowIndex,key).equals(featureMappings.get(key))){
                    status = false;
                    break;
                }
            }

            if(status){
                if(!outcomes.contains(value)){
                    outcomes.add(value);
                }
            }
        }

        return outcomes.get(0);

    }

    private static double getOutcome(TrainingSet trainingSet, Map<Integer,Double> featureMappings){

        int numberOfColumns = trainingSet.getNumberOfColumns();
        Set<Integer> keys = featureMappings.keySet();
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            boolean status = true;
            for(Integer keyItem : keys){
                //if(trainingRow[keyItem] != featureMappings.get(keyItem)){
                if(!trainingSet.get(rowIndex,keyItem).equals(featureMappings.get(keyItem))){
                    status = false;
                    break;
                }
            }
            if(status){
                return trainingSet.get(rowIndex,numberOfColumns-1);
            }
        }

        throw new IllegalArgumentException();
    }

    private static List<Double> getCategories(TrainingSet trainingSet, int feature){

        List<Double> categories = new ArrayList<>();
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
        //for(Double[] trainingRow : trainingSet){
            Double value = trainingSet.get(rowIndex,feature);
            if(!categories.contains(value)){
                categories.add(value);
            }
        }

        return categories;

    }

    private static List<Double> getCategories(TrainingSet trainingSet, int feature, Map<Integer,Double> featureMappings){

        List<Double> categories = new ArrayList<>();
        Set<Integer> keys = featureMappings.keySet();
        if(feature < 0){
            System.out.println("feature index < 0");
        }
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            Double value = trainingSet.get(rowIndex,feature);

            boolean status = true;
            for(Integer key : keys){
                if(!trainingSet.get(rowIndex,key).equals(featureMappings.get(key))){
                    status = false;
                    break;
                }
            }

            if(status){
                if(!categories.contains(value)){
                    categories.add(value);
                }
            }
        }

        return categories;

    }

    private static int getFeatureToSplitOn(TrainingSet trainingSet, Map<Integer,Double> previousMappings, List<Double> outcomes){

        int lastFeatureColumnIndex = trainingSet.getNumberOfColumns() - 1;
        Set<Integer> keys = previousMappings.keySet();
        Map<Integer,Double> featureInformationGainMappings = new HashMap<>();
        for(int feature = 0; feature < lastFeatureColumnIndex; feature++){
            if(!keys.contains(feature)){
                double informationGain = getInformationGain(trainingSet,previousMappings,feature,outcomes);
                featureInformationGainMappings.put(feature,informationGain);
            }
        }
        int featureWithMaximumInformationGain = getFeatureWithMaximumInformationGain(featureInformationGainMappings);

        return featureWithMaximumInformationGain;

    }

    private static int getFeatureWithMaximumInformationGain(Map<Integer,Double> featureInformationGainMappings){

        if(!featureInformationGainMappings.isEmpty()){
            Set<Integer> keys = featureInformationGainMappings.keySet();
            List<Integer> keysList = new ArrayList<>(keys);
            int featureWithMaximumInformationGain = keysList.get(0);
            double maximumInformationGain = featureInformationGainMappings.get(featureWithMaximumInformationGain);
            for(int index = 1; index < keysList.size(); index++){
                int currentKey = keysList.get(index);
                double currentInformationGain = featureInformationGainMappings.get(currentKey);
                if(maximumInformationGain < currentInformationGain){
                    featureWithMaximumInformationGain = currentKey;
                    maximumInformationGain = currentInformationGain;
                }
            }
            return featureWithMaximumInformationGain;
        }

        return -1;

    }

    private static double getInformationGain(TrainingSet trainingSet, Map<Integer,Double> previousMappings, int feature, List<Double> outcomes){

        double currentEntropy = getEntropy(trainingSet,previousMappings,outcomes);
        double childrenEntropy = 0;
        List<Double> categories = getCategories(trainingSet,feature);
        for(Double category : categories){
            previousMappings.put(feature,category);
            double childEntropy = getEntropy(trainingSet,previousMappings,outcomes);
            childrenEntropy = childrenEntropy + childEntropy;
            previousMappings.remove(feature);
        }

        double informationGain = currentEntropy - childrenEntropy;
        return informationGain;

    }

    private static double getEntropy(TrainingSet trainingSet, Map<Integer,Double> previousMappings, List<Double> outcomes){

        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        int mainTrainingSetCount = 0;
        Map<Double,Integer> outcomesWithCount = new HashMap<>();
        Set<Integer> keys = previousMappings.keySet();

        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            boolean status = true;
            for(Integer keyItem : keys){
                if(trainingSet.get(rowIndex,keyItem) != previousMappings.get(keyItem)){
                    status = false;
                    break;
                }
            }
            if(status){
                mainTrainingSetCount = mainTrainingSetCount + 1;
                double trainingRowOutcome = trainingSet.get(rowIndex,lastColumnIndex);
                if(outcomesWithCount.containsKey(trainingRowOutcome)){
                    int outcomeFrequency = outcomesWithCount.get(trainingRowOutcome);
                    outcomeFrequency++;
                    outcomesWithCount.put(trainingRowOutcome,outcomeFrequency);
                }
                else{
                    outcomesWithCount.put(trainingRowOutcome,1);
                }
            }
        }

        double entropy = computeEntropy(mainTrainingSetCount,outcomesWithCount);
        return entropy;

    }

    private static double computeEntropy(int mainTrainingSetCount, Map<Double,Integer> outcomesWithCount){

        double entropy = 0;
        int numberOfOutcome = outcomesWithCount.size();
        Set<Double> outcomes = outcomesWithCount.keySet();
        for(Double outcome : outcomes){
            double coefficient = outcomesWithCount.get(outcome) / mainTrainingSetCount;
            double logPart = Math.log10(coefficient) / Math.log10(numberOfOutcome);
            entropy = entropy - (coefficient * logPart);
        }

        return entropy;

    }

//    private static List<Double> getOutcomes(Double[][] trainingSet){
//
//        List<Double> outcomes = new ArrayList<>();
//        int lastColumnIndex = trainingSet[0].length - 1;
//        for(int i = 0; i < trainingSet.length; i++){
//            Double outcome = trainingSet[i][lastColumnIndex];
//            if(!outcomes.contains(outcome)){
//                outcomes.add(outcome);
//            }
//        }
//        return outcomes;
//
//    }

    // update this method
    private static boolean isPure(TrainingSet trainingSet, Map<Integer,Double> mappings){

        if(trainingSet.getNumberOfRows() > 0){
            List<Double> outcomes = new ArrayList<>();
            int outcomeColumnIndex = trainingSet.getNumberOfColumns() - 1;
            Set<Integer> keys = mappings.keySet();
            for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            //for(Double[] trainingRow : trainingSet){
                boolean status = true;
                for(Integer key : keys){
                    //if(trainingRow[key] != mappings.get(key)){
                    if(!trainingSet.get(rowIndex,key).equals(mappings.get(key))){
                        status = false;
                        break;
                    }
                }
                if(status){
                    outcomes.add(trainingSet.get(rowIndex,outcomeColumnIndex));
                }

            }


            if(hasSameValues(outcomes)){
                return true;
            }
            return false;
        }

        throw new IllegalArgumentException();

    }

    private static boolean hasSameValues(List<Double> items){

        if(!items.isEmpty()){
            double outcome = items.get(0);
            for(int index = 1; index < items.size(); index++){
                if(items.get(index) != outcome){
                    return false;
                }
            }
            return true;
        }

        throw new IllegalArgumentException();

    }

}
