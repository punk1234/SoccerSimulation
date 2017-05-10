package com.company.ml.naivebayes;

/**
 * Created by AKEJU  FATAI on 2017-04-28.
 */
public class OutcomeProbability {

    private Double outcome;
    private Double probability;

    public OutcomeProbability(Double outcome, Double probability){

        this.outcome = outcome;
        this.probability = probability;

    }

    public void setOutcome(Double outcome){

        this.outcome = outcome;

    }

    public void setProbability(Double probability){

        this.probability = probability;

    }

    public Double getOutcome(){

        return outcome;

    }

    public Double getProbability(){

        return probability;

    }

}
