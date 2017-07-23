package com.company.ml.linearmodel;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.linearmodel.linearmodelfunction.LinearModelFunction;
import com.company.ml.linearmodel.linearmodelfunction.MultiClassLinearModelFunction;
import com.company.ml.linearmodel.linearmodelfunction.TwoClassLinearModelFunction;
import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.linearmodel.gradientdescent.GradientDescent;
import com.company.ml.linearmodel.polynomial.PolynomialFactory;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class LinearModel implements MachineLearningTechnique {

    private LinearModelFunction linearModelFunction;
    private GradientDescent gradientDescent;
    private int maximumDegree;
    private String variablePrefix;

    public LinearModel(GradientDescent gradientDescent, int maximumDegree, String variablePrefix){

        this.linearModelFunction = null;
        this.gradientDescent = gradientDescent;
        this.maximumDegree = maximumDegree;
        this.variablePrefix = variablePrefix;

    }

    public LinearModelFunction getLinearModelFunction() {
        return linearModelFunction;
    }

    public GradientDescent getGradientDescent() {
        return gradientDescent;
    }

    public int getMaximumDegree() {
        return maximumDegree;
    }

    public String getVariablePrefix() {
        return variablePrefix;
    }

    @Override
    public void learn(TrainingSet trainingSet){

        int numberOfColumns = trainingSet.getNumberOfColumns();
        int numberOfOutcomes = trainingSet.getDistinctColumnValues(numberOfColumns-1).size();
        int numberOfVariables = numberOfColumns - 1;
        if(numberOfOutcomes == 1){
            System.out.println("Just one outcome through out");
        }
        if(numberOfOutcomes == 2){
            Polynomial polynomial = PolynomialFactory.create(numberOfVariables,maximumDegree,variablePrefix);
            gradientDescent.execute(trainingSet,polynomial);
            linearModelFunction = new TwoClassLinearModelFunction(polynomial);
        }
        else{
            // Polynomial polynomial = PolynomialFactory.create(numberOfVariables,maximumDegree,variablePrefix);
            OneVersusAll.execute(trainingSet,variablePrefix,maximumDegree);
            Map<Double,Polynomial> outcomePolynomialMapping = OneVersusAll.get();
            linearModelFunction = new MultiClassLinearModelFunction(outcomePolynomialMapping);
        }

    }

    @Override
    public Double predict(TrainingRow predictionSet){

        return linearModelFunction.predict(predictionSet,variablePrefix);

    }

    @Override
    public String getName() {
        return "linear_model";
    }
}
