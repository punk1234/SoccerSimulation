package com.company.ml.linearmodel.gradientdescent;

import com.company.ml.linearmodel.polynomial.Polynomial;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public interface GradientDescent {

    public Polynomial execute(double[][] trainingSet, Polynomial polynomial);

}
