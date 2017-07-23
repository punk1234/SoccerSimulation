package com.company.match.trainingpredictiondatabuilder;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-07-03.
 */
public class ClassificationTrainingSetConverter {

    public static void convert(TrainingSet trainingSet){

        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){

            double teamA_PerformanceClassification = trainingSet.get(rowIndex,1);
            trainingSet.set(rowIndex,1,toClassification(teamA_PerformanceClassification,4,3.0));
            double teamB_PerformanceClassification = trainingSet.get(rowIndex,2);
            trainingSet.set(rowIndex,2,toClassification(teamB_PerformanceClassification,4,3.0));
            double teamA_goalsScoredFullTimeClassification = trainingSet.get(rowIndex,3);
            trainingSet.set(rowIndex,3,toClassification(teamA_goalsScoredFullTimeClassification,4,4.0));
            double teamB_goalsScoredFullTimeClassification = trainingSet.get(rowIndex,4);
            trainingSet.set(rowIndex,4,toClassification(teamB_goalsScoredFullTimeClassification,4,4.0));
            double teamA_goalsConcededFullTimeClassification = trainingSet.get(rowIndex,5);
            trainingSet.set(rowIndex,5,toClassification(teamA_goalsConcededFullTimeClassification,4,4.0));
            double teamB_goalsConcededFullTimeClassification = trainingSet.get(rowIndex,6);
            trainingSet.set(rowIndex,6,toClassification(teamB_goalsConcededFullTimeClassification,4,4.0));

        }

    }

    public static void convert(TrainingRow trainingRow){

        double teamA_PerformanceClassification = trainingRow.get(1);
        trainingRow.set(1,toClassification(teamA_PerformanceClassification,4,3.0));
        double teamB_PerformanceClassification = trainingRow.get(2);
        trainingRow.set(2,toClassification(teamB_PerformanceClassification,4,3.0));
        double teamA_goalsScoredFullTimeClassification = trainingRow.get(3);
        trainingRow.set(3,toClassification(teamA_goalsScoredFullTimeClassification,4,4.0));
        double teamB_goalsScoredFullTimeClassification = trainingRow.get(4);
        trainingRow.set(4,toClassification(teamB_goalsScoredFullTimeClassification,4,4.0));
        double teamA_goalsConcededFullTimeClassification = trainingRow.get(5);
        trainingRow.set(5,toClassification(teamA_goalsConcededFullTimeClassification,4,4.0));
        double teamB_goalsConcededFullTimeClassification = trainingRow.get(6);
        trainingRow.set(6,toClassification(teamB_goalsConcededFullTimeClassification,4,4.0));

    }

    public static void convertGeneral(TrainingSet trainingSet){

        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){

            double teamA_PerformanceClassification = trainingSet.get(rowIndex,0);
            trainingSet.set(rowIndex,0,toClassification(teamA_PerformanceClassification,4,3.0));
            double teamB_PerformanceClassification = trainingSet.get(rowIndex,1);
            trainingSet.set(rowIndex,1,toClassification(teamB_PerformanceClassification,4,3.0));
            double teamA_goalsScoredFullTimeClassification = trainingSet.get(rowIndex,2);
            trainingSet.set(rowIndex,2,toClassification(teamA_goalsScoredFullTimeClassification,4,4.0));
            double teamB_goalsScoredFullTimeClassification = trainingSet.get(rowIndex,3);
            trainingSet.set(rowIndex,3,toClassification(teamB_goalsScoredFullTimeClassification,4,4.0));
            double teamA_goalsConcededFullTimeClassification = trainingSet.get(rowIndex,4);
            trainingSet.set(rowIndex,4,toClassification(teamA_goalsConcededFullTimeClassification,4,4.0));
            double teamB_goalsConcededFullTimeClassification = trainingSet.get(rowIndex,5);
            trainingSet.set(rowIndex,5,toClassification(teamB_goalsConcededFullTimeClassification,4,4.0));

        }

    }

    public static void convertGeneral(TrainingRow trainingRow){

        double teamA_PerformanceClassification = trainingRow.get(0);
        trainingRow.set(0,toClassification(teamA_PerformanceClassification,4,3.0));
        double teamB_PerformanceClassification = trainingRow.get(1);
        trainingRow.set(1,toClassification(teamB_PerformanceClassification,4,3.0));
        double teamA_goalsScoredFullTimeClassification = trainingRow.get(2);
        trainingRow.set(2,toClassification(teamA_goalsScoredFullTimeClassification,4,4.0));
        double teamB_goalsScoredFullTimeClassification = trainingRow.get(3);
        trainingRow.set(3,toClassification(teamB_goalsScoredFullTimeClassification,4,4.0));
        double teamA_goalsConcededFullTimeClassification = trainingRow.get(4);
        trainingRow.set(4,toClassification(teamA_goalsConcededFullTimeClassification,4,4.0));
        double teamB_goalsConcededFullTimeClassification = trainingRow.get(5);
        trainingRow.set(5,toClassification(teamB_goalsConcededFullTimeClassification,4,4.0));

    }

    private static double toClassification(double value, int division, double maximumPoint){

        // double maximumPoint = 3.0;
        for(int divisionIndex = 1; divisionIndex < division; divisionIndex++){
            double upperLimit = (double)(divisionIndex) / division * maximumPoint;
            if(value <= upperLimit){
                return divisionIndex;
            }
        }
        return division;

    }

}
