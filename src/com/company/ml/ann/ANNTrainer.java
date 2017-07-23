package com.company.ml.ann;

import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-07-13.
 */
public class ANNTrainer {

    public static void execute(ANN ann, TrainingSet trainingSet){

        int iterationCount = 20;

        for(int count = 0; count < iterationCount; count++){

            for(int index = 0; index < trainingSet.getNumberOfRows(); index++){

                TrainingRow trainingRow = trainingSet.get(index);

                ForwardPropagation.execute(ann,trainingRow);

                BackPropagation.execute(ann,trainingRow.get(trainingRow.getSize()-1));

            }

        }

    }

}
