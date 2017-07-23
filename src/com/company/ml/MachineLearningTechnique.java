package com.company.ml;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public interface MachineLearningTechnique {

    public void learn(TrainingSet trainingSet);
    public Double predict(TrainingRow predictionSet);
    public String getName();

}
