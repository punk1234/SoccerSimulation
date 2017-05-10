package com.company.ml.linearmodel;

import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.linearmodel.gradientdescent.BatchGradientDescent;
import com.company.ml.linearmodel.gradientdescent.GradientDescent;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class LinearModel {

    private Polynomial polynomial;
    private GradientDescent gradientDescent;

    public LinearModel(Polynomial polynomial, GradientDescent gradientDescent){

        this.polynomial = polynomial;
        this.gradientDescent = gradientDescent;

    }

    public void learn(double[][] trainngSet){

        polynomial = gradientDescent.execute(trainngSet,polynomial);

    }

    public Double predict(Double[] predictionSet){

        return null;

    }

}
