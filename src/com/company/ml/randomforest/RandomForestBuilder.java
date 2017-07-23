package com.company.ml.randomforest;

import com.company.ml.decisiontree.*;
import com.company.ml.model.TrainingSet;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class RandomForestBuilder {

    private static Random random = new Random();

    public static RandomForest build(TrainingSet trainingSet, int numberOfDecisionTrees){

        RandomForest randomForest = new RandomForest(numberOfDecisionTrees);
        for(int count = 0; count < numberOfDecisionTrees; count++){
            TrainingSet randomTrainingSet = getRandomTrainingSet(trainingSet);
            DecisionTree decisionTree = DecisionTreeBuilder.build(randomTrainingSet);
            randomForest.add(decisionTree);
        }

        return randomForest;

    }

    private static TrainingSet getRandomTrainingSet(TrainingSet trainingSet){

        List<Integer> rowIndexList = new ArrayList<>();
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            if(Coin.flip() == CoinSide.HEAD){
                rowIndexList.add(rowIndex);
            }
        }

        return generate(trainingSet,rowIndexList);

    }

    private static TrainingSet generate(TrainingSet trainingSet, List<Integer> indexes){

        int numberOfRows = indexes.size();
        int numberOfColumns = trainingSet.getNumberOfColumns();
        TrainingSet newTrainingSet = new TrainingSet(numberOfRows,numberOfColumns);
        for(int rowIndex = 0; rowIndex < numberOfRows; rowIndex++){
            int index = indexes.get(rowIndex);
            for(int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++){
                double value = trainingSet.get(index,columnIndex);
                newTrainingSet.set(rowIndex,columnIndex,value);
            }
        }
        return newTrainingSet;

    }

}
