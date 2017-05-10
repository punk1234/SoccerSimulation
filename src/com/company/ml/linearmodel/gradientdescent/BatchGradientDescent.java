package com.company.ml.linearmodel.gradientdescent;

import com.company.ml.linearmodel.polynomial.Polynomial;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class BatchGradientDescent implements GradientDescent {

    private final double learningRate = 0.01;

    private final double epsilon = 0.0001;

    @Override
    public Polynomial execute(double[][] trainingSet, Polynomial polynomial) {

        double initialCoefficientValue = 0;
        polynomial.setCoefficient(initialCoefficientValue);

//        do{
//
//        }while (hasConverge());

        return null;
    }

    private boolean hasConverge(double[] oldValues, double[] newValues){

        if(oldValues.length == newValues.length){
            int size = oldValues.length;
            for(int i = 0; i < size;i++){
                double difference = newValues[i] - oldValues[i];
                if(Math.abs(difference) > epsilon){
                    return false;
                }
            }
            return true;
        }

        throw new IllegalArgumentException();

    }

}
