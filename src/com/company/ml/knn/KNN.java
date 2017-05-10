package com.company.ml.knn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-04-14.
 */
public class KNN {

    // this is done with the assumption that prediction values are converted to integer values
    // for example, in double chance, make 1x = 1, x2 = 2, and 12 = 3
    // the training set and the prediction set must have the same number of columns
    public static int predict(double[][] trainingSet, double[] predictionSet, int k){

        DistanceMap[] distanceMaps = new DistanceMap[trainingSet.length];
        for(int rowIndex = 0; rowIndex < trainingSet.length; rowIndex++){
            double distance = getEuclideanDistance(trainingSet[rowIndex],predictionSet);
            DistanceMap distanceMap = new DistanceMap(rowIndex,distance);
            distanceMaps[rowIndex] = distanceMap;
        }

        sort(distanceMaps);

        return -1;

    }

    private static void sort(DistanceMap[] distanceMaps){

        if(distanceMaps.length > 0){
            for(int forwardIndex = 1; forwardIndex < distanceMaps.length; forwardIndex++){
                for(int backwardIndex = forwardIndex; backwardIndex >= 1; backwardIndex--){
                    if(distanceMaps[backwardIndex-1].getDistance() <= distanceMaps[backwardIndex].getDistance()){
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

    private static double getEuclideanDistance(double[] trainingRow, double[] predictionSet){

        if(trainingRow.length == predictionSet.length){
            double sum = 0;
            for(int columnIndex = 0; columnIndex < trainingRow.length; columnIndex++){
                sum = sum + (Math.pow(trainingRow[columnIndex] - predictionSet[columnIndex],2));
            }
            double distance = Math.sqrt(sum);
            return distance;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

}
