package com.company.ml.linearmodel.factory;

import com.company.ml.model.TrainingRow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-26.
 */
public class VariableValueMappingFactory {

    public static Map<String,Double> get(TrainingRow trainingRow, String variablePrefix){

        Map<String,Double> mappings = new HashMap<>();
        int size = trainingRow.getSize();
        for(int index = 0; index < size; index++){
            String variableName = variablePrefix + (index + 1);
            double variableValue = trainingRow.get(index); // [index-1];
            mappings.put(variableName,variableValue);
        }

        return mappings;

    }

}
