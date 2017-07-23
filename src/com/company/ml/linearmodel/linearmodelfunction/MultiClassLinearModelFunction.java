package com.company.ml.linearmodel.linearmodelfunction;

import com.company.ml.linearmodel.OneVersusAll;
import com.company.ml.linearmodel.factory.VariableValueMappingFactory;
import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.model.TrainingRow;

import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-26.
 */
public class MultiClassLinearModelFunction implements LinearModelFunction {

    private Map<Double,Polynomial> outcomePolynomialMapping;

    public MultiClassLinearModelFunction(Map<Double,Polynomial> outcomePolynomialMapping){

        this.outcomePolynomialMapping = outcomePolynomialMapping;

    }

    @Override
    public Double predict(TrainingRow predictionSet, String variablePrefix) {

        Map<String,Double> variableMapping = VariableValueMappingFactory.get(predictionSet,variablePrefix);
        Double value = OneVersusAll.predict(predictionSet,variablePrefix);
        return value;
    }

    public Map<Double,Polynomial> getPolynomialMapping(){

        return outcomePolynomialMapping;

    }

}
