package com.company.ml.linearmodel.linearmodelfunction;

import com.company.ml.linearmodel.factory.VariableValueMappingFactory;
import com.company.ml.linearmodel.gradientdescent.GradientDescent;
import com.company.ml.model.TrainingRow;

import com.company.ml.linearmodel.polynomial.Polynomial;

import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-26.
 */
public class TwoClassLinearModelFunction implements LinearModelFunction {

    private Polynomial polynomial;

    public TwoClassLinearModelFunction(Polynomial polynomial){

        this.polynomial = polynomial;

    }

    @Override
    public Double predict(TrainingRow predictionSet, String variablePrefix) {

        Map<String,Double> variableMapping = VariableValueMappingFactory.get(predictionSet,variablePrefix);
        Double value =  polynomial.getValue(variableMapping);
        if(value < 0){
            System.out.println("value is less zero" + value);
        }
        else {
            System.out.println(" ============= value is not less zero" + value);
        }
//        if(value < 0){
//            return 1.0;
//        }
//        else{
//            return 3.0;
//        }
        if(value < 0){
            return 0.0;
        }
        else{
            return 1.0;
        }
        // return value;

    }

    public Polynomial getPolynomial(){

        return polynomial;

    }

}
