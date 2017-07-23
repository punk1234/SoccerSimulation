package com.company.ml.linearmodel;

/**
 * Created by AKEJU  FATAI on 2017-05-16.
 */
public class SigmoidFunction {

    public static double pass(double value){

        double numerator = 1;
        double denominator = 1 + Math.exp(value);
        double result = numerator / denominator;
        return result;

    }

}
