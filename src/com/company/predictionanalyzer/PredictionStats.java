package com.company.predictionanalyzer;

/**
 * Created by AKEJU  FATAI on 2017-06-26.
 */
public class PredictionStats {

    private int successCount;
    private int failureCount;

    public PredictionStats(){

        successCount = 0;
        failureCount = 0;

    }

    public int getSuccessCount(){
        return successCount;
    }

    public int getFailureCount(){
        return failureCount;
    }

    public int getTotalCount(){
        return (successCount + failureCount);
    }

    public void incrementSuccessCount(){
        successCount = successCount + 1;
    }

    public void incrementFailureCount(){
        failureCount = failureCount + 1;
    }

    public void update(boolean value){

        if(value){
            incrementSuccessCount();
        }
        else{
            incrementFailureCount();
        }

    }

}