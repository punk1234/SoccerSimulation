package com.company.ml.linearmodel.gradientdescent;

import com.company.ml.linearmodel.SigmoidFunction;
import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.linearmodel.polynomial.PUnit;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class BatchGradientDescent implements GradientDescent {

    private final double learningRate = 0.1;

    private final double epsilon = 80;

    @Override
    public Polynomial execute(TrainingSet trainingSet, Polynomial polynomial) {

        double initialCoefficientValue = 0;
        polynomial.setCoefficient(initialCoefficientValue);

        int trainingSetCount = trainingSet.getNumberOfRows(); // m
        int polynomialSize = polynomial.size();
        double[] oldValues = new double[polynomialSize];
        double[] newValues = new double[polynomialSize];

        do{
            copyNewToOld(oldValues,newValues,polynomialSize);
            int index;
            for(index = 0; index < (polynomialSize-1); index++){
                double g = getGradient(trainingSet,polynomial,index);
                newValues[index] = oldValues[index] - ((learningRate/trainingSetCount) * g);
            }
            index = -1;
            double gradient = getGradient(trainingSet,polynomial,index);
            newValues[polynomialSize-1] = oldValues[polynomialSize-1] - ((learningRate/trainingSetCount) * gradient);

            setPolynomialCoefficient(polynomial,newValues);
        }while (!hasConverge(oldValues,newValues));

        setPolynomialCoefficient(polynomial,newValues);

        return polynomial;
    }

    private void setPolynomialCoefficient(Polynomial polynomial, double[] values){

        int length = values.length - 1;
        int index;
        for(index = 0; index < length; index++){
            polynomial.getUnit(index).setCoefficient(values[index]);
        }
        polynomial.setConstant(values[index]);

    }

    private double getGradient(TrainingSet trainingSet, Polynomial polynomial, int index){

        if(trainingSet.getNumberOfRows() > 0){
            int numberOfColumn = trainingSet.getNumberOfColumns();
            double partial = 1;
            double gradient = 0;
            for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            //for(double[] trainingRow : trainingSet){
                double yValue = trainingSet.get(rowIndex,numberOfColumn-1);
                String variablePrefix = "x";
                Map<String,Double> mappings = createVariableValueMapping(trainingSet,rowIndex,numberOfColumn,variablePrefix);
                double costFunctionValue = polynomial.getValue(mappings);
                if(index != -1){
                    //String variableName = variablePrefix + (index + 1);
                    PUnit unit = polynomial.getUnit(index);
                    String variableName = unit.getVariableName();
                    double coefficient = 1;
                    partial = unit.getValue(coefficient,mappings.get(variableName));
                }

                // gradient = gradient + ((costFunctionValue - yValue) * partial);
                gradient = gradient + ((SigmoidFunction.pass(costFunctionValue) - yValue) * partial);
            }
            return gradient;
        }
        throw new IllegalArgumentException();

    }

    private Map<String,Double> createVariableValueMapping(TrainingSet trainingSet, int rowIndex, int size, String variablePrefix){

        Map<String,Double> mappings = new HashMap<>();
        for(int index = 1; index < size; index++){
            String variableName = variablePrefix + index;
            double variableValue = trainingSet.get(rowIndex,index-1);
            mappings.put(variableName,variableValue);
        }

        return mappings;

    }

    private void copyNewToOld(double[] oldValues, double[] newValues, int length){

        for(int index = 0; index < length; index++){
            oldValues[index] = newValues[index];
        }

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
