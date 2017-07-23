package com.company.predictionanalyzer;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-26.
 */
public class PredictionAnalysisPrinter {

    public static void print(PredictionAnalysis predictionAnalysis){

        // printing code goes here
        List<String> techniques = predictionAnalysis.getTechniques();
        for(String technique : techniques){
            System.out.println("\nTECHNIQUE " + technique);
            CategoryStatCollection categoryStatCollection = predictionAnalysis.get(technique);
            List<String> categorys = categoryStatCollection.getCategorys();
            for(String category : categorys){
                PredictionStats predictionStats = categoryStatCollection.get(category);
                int successCount = predictionStats.getSuccessCount();
                int failureCount = predictionStats.getFailureCount();
                System.out.println(category + " :: " + successCount + " success " + failureCount + " failure " +
                        " ::: accuracy = " + ((successCount*100)/(successCount+failureCount)));
            }
        }

    }

}
