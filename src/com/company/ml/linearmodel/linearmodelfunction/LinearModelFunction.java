package com.company.ml.linearmodel.linearmodelfunction;

import com.company.ml.model.TrainingRow;

/**
 * Created by AKEJU  FATAI on 2017-05-26.
 */
public interface LinearModelFunction {

    public Double predict(TrainingRow predictionSet, String variablePrefix);

}
