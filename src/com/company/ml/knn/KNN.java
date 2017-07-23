package com.company.ml.knn;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-04-14.
 */
public class KNN implements MachineLearningTechnique {

    private int k;
    private TrainingSet trainingSet;

    public KNN(int k){

        this.k = k;

    }

    @Override
    public void learn(TrainingSet trainingSet){

        this.trainingSet = trainingSet;

    }

    // this is done with the assumption that prediction values are converted to integer values
    // for example, in double chance, make 1x = 1, x2 = 2, and 12 = 3
    // the training set and the prediction set must have the same number of columns

    @Override
    public Double predict(TrainingRow predictionSet){

        int trainingSetRowCount = trainingSet.getNumberOfRows();
        DistanceMap[] distanceMaps = new DistanceMap[trainingSetRowCount];
        for(int rowIndex = 0; rowIndex < trainingSetRowCount; rowIndex++){
            double distance = getEuclideanDistance(rowIndex,trainingSet,predictionSet);
            DistanceMap distanceMap = new DistanceMap(rowIndex,distance);
            distanceMaps[rowIndex] = distanceMap;
        }

        sort(distanceMaps);

        Double predictionOutcome = getPredictionOutcome(distanceMaps,k,trainingSet);

        return predictionOutcome;

    }

    @Override
    public String getName() {
        return "knn";
    }

    private static Double getPredictionOutcome(DistanceMap[] distanceMaps, int k, TrainingSet trainingSet){

        Map<Double,Integer> outcomeFrequencyMappings = new HashMap();
        int outcomeColumnIndex = trainingSet.getNumberOfColumns() - 1;
        for(int index = 0; index < k; index++){
            int rowIndex = distanceMaps[index].getIndex();
            double outcome = trainingSet.get(rowIndex,outcomeColumnIndex);
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

    private static void sort(DistanceMap[] distanceMaps){

        if(distanceMaps.length > 0){
            for(int forwardIndex = 1; forwardIndex < distanceMaps.length; forwardIndex++){
                for(int backwardIndex = forwardIndex; backwardIndex >= 1; backwardIndex--){
                    // descending order
                    if(distanceMaps[backwardIndex-1].getDistance() >= distanceMaps[backwardIndex].getDistance()){
                        break;
                    }
                    else{
                        swap(distanceMaps,(backwardIndex-1),backwardIndex);
                    }
                }
            }
        }

    }

    private static void swap(DistanceMap[] distanceMaps, int index1, int index2){

        DistanceMap distanceMap1Copy = distanceMaps[index1];
        distanceMaps[index1] = distanceMaps[index2];
        distanceMaps[index2] = distanceMap1Copy;

    }

    private static double getEuclideanDistance(int rowIndex, TrainingSet trainingSet, TrainingRow predictionSet){

        int trainingSetColumnCount = trainingSet.getNumberOfColumns();
        if((trainingSetColumnCount-1) == predictionSet.getSize()){
            double sum = 0;
            for(int columnIndex = 0; columnIndex < predictionSet.getSize(); columnIndex++){
                double difference = trainingSet.get(rowIndex,columnIndex) - predictionSet.get(columnIndex);
                sum = sum + (Math.pow(difference,2));
            }
            double distance = Math.sqrt(sum);
            System.out.println("DISTANCE " + distance);
            return distance;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

}
