package com.company.ml.randomforest;

import java.util.*;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.decisiontree.DecisionTree;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class RandomForest implements MachineLearningTechnique {

    private int n;
    private List<DecisionTree> decisionTrees;

    public RandomForest(int n){

        this.n = n;
        this.decisionTrees = new ArrayList<>();

    }

    public void add(DecisionTree decisionTree){

        decisionTrees.add(decisionTree);

    }

    public DecisionTree getDecisionTree(int index){

        if(index < decisionTrees.size()){
            return decisionTrees.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public int getNumberOfDecisionTree(){

        return decisionTrees.size();

    }

    @Override
    public void learn(TrainingSet trainingSet){

        RandomForest randomForest = RandomForestBuilder.build(trainingSet,n);
        List<DecisionTree> decisionTrees = new ArrayList<>();
        for(int index = 0; index < randomForest.getNumberOfDecisionTree(); index++){
            decisionTrees.add(randomForest.getDecisionTree(index));
        }
        this.decisionTrees = decisionTrees;

    }

    @Override
    public Double predict(TrainingRow predictionSet){

        Map<Double,Integer> outcomeFrequencyMappings = new HashMap<>();
        for(DecisionTree decisionTree : decisionTrees){
            Double outcome = decisionTree.predict(predictionSet);
            if(outcomeFrequencyMappings.containsKey(outcome)){
                int frequency = outcomeFrequencyMappings.get(outcome);
                frequency++;
                outcomeFrequencyMappings.replace(outcome,frequency);
            }
            else{
                outcomeFrequencyMappings.put(outcome,1);
            }
        }
        return getOutcomeWithMaximumFrequency(outcomeFrequencyMappings);

    }

    public String getName(){

        return "random_forest";

    }

    private static Double getOutcomeWithMaximumFrequency(Map<Double,Integer> outcomeFrequencyMappings){

        Set<Double> outcomeSet = outcomeFrequencyMappings.keySet();
        List<Double> outcomeList = new ArrayList<>(outcomeSet);
        Double currentOutcomeWithMaximumFrequency = outcomeList.get(0);
        Integer currentMaximumFrequency = outcomeFrequencyMappings.get(currentOutcomeWithMaximumFrequency);
        for(int index = 1; index < outcomeFrequencyMappings.size(); index++){
            Double outcome = outcomeList.get(index);
            Integer frequency = outcomeFrequencyMappings.get(outcome);
            if(frequency > currentMaximumFrequency){
                currentOutcomeWithMaximumFrequency = outcome;
                currentMaximumFrequency = frequency;
            }
        }
        return currentOutcomeWithMaximumFrequency;

    }

}
