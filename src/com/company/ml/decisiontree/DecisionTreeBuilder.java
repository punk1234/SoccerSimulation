package com.company.ml.decisiontree;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class DecisionTreeBuilder {

    // the last column of the training examples contains the outcome or result
    public static DecisionTree build(Double[][] trainingSet){

        List<Double> outcomes = getOutcomes(trainingSet);
        Node node = new Node();
        Map<Integer,Double> featureMappings = new HashMap<>();
        build(trainingSet,featureMappings,node,outcomes);

        DecisionTree decisionTree = new DecisionTree();
        decisionTree.setRoot(node);

        return decisionTree;

    }

    private static void build(Double[][] trainingSet, Map<Integer,Double> featureMappings, Node node, List<Double> outcomes){

        Integer feature = getFeatureToSplitOn(trainingSet,featureMappings,outcomes);
        node.setFeature(feature);
        List<Double> categories = getCategories(trainingSet,feature);
        for(Double category : categories){
            featureMappings.put(feature,category);
            if(isPure(trainingSet,featureMappings)){
                node.getOutcomeMappings().put(category,getOutcome(trainingSet,featureMappings));
            }
            else{
                Node childNode = new Node();
                node.getNodeMappings().put(category,childNode);
                build(trainingSet,featureMappings,childNode,outcomes);
            }
            featureMappings.remove(feature);
        }

    }

    private static double getOutcome(Double[][] trainingSet, Map<Integer,Double> featureMappings){

        int numberOfColumns = trainingSet[0].length;
        Set<Integer> keys = featureMappings.keySet();
        for(Double[] trainingRow : trainingSet){
            boolean status = true;
            for(Integer keyItem : keys){
                if(trainingRow[keyItem] != featureMappings.get(keyItem)){
                    status = false;
                    break;
                }
            }
            if(status){
                return trainingRow[numberOfColumns-1];
            }
        }

        throw new IllegalArgumentException();
    }

    private static List<Double> getCategories(Double[][] trainingSet, int feature){

        List<Double> categories = new ArrayList<>();
        for(Double[] trainingRow : trainingSet){
            Double value = trainingRow[feature];
            if(!categories.contains(value)){
                categories.add(value);
            }
        }

        return categories;

    }

    private static int getFeatureToSplitOn(Double[][] trainingSet, Map<Integer,Double> previousMappings, List<Double> outcomes){

        int lastFeatureColumnIndex = trainingSet[0].length - 1;
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

    private static double getInformationGain(Double[][] trainingSet, Map<Integer,Double> previousMappings, int feature, List<Double> outcomes){

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

    private static double getEntropy(Double[][] trainingSet, Map<Integer,Double> previousMappings, List<Double> outcomes){

        int lastColumnIndex = trainingSet[0].length - 1;
        int mainTrainingSetCount = 0;
        Map<Double,Integer> outcomesWithCount = new HashMap<>();
        Set<Integer> keys = previousMappings.keySet();

        for(Double[] trainingRow:trainingSet){
            boolean status = true;
            for(Integer keyItem : keys){
                if(trainingRow[keyItem] != previousMappings.get(keyItem)){
                    status = false;
                    break;
                }
            }
            if(status){
                mainTrainingSetCount = mainTrainingSetCount + 1;
                double trainingRowOutcome = trainingRow[lastColumnIndex];
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

    // update this method
    private static boolean isPure(Double[][] trainingSet, Map<Integer,Double> mappings){

        if(trainingSet.length > 0){
            List<Double> outcomes = new ArrayList<>();
            int outcomeColumnIndex = trainingSet[0].length - 1;
            Set<Integer> keys = mappings.keySet();
            for(Double[] trainingRow : trainingSet){
                boolean status = true;
                for(Integer key : keys){
                    if(trainingRow[key] != mappings.get(key)){
                        status = false;
                        break;
                    }
                }
                if(status){
                    outcomes.add(trainingRow[outcomeColumnIndex]);
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
