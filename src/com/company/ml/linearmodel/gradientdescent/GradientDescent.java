package com.company.ml.linearmodel.gradientdescent;

import com.company.ml.linearmodel.polynomial.Polynomial;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public interface GradientDescent {

    public Polynomial execute(TrainingSet trainingSet, Polynomial polynomial);

}
