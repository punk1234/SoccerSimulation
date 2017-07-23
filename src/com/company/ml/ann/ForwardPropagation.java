package com.company.ml.ann;

import com.company.ml.linearmodel.SigmoidFunction;
import com.company.ml.matrix.Matrix;
import com.company.ml.model.TrainingRow;

/**
 * Created by AKEJU  FATAI on 2017-07-13.
 */
public class ForwardPropagation {

    public static void execute(ANN ann, TrainingRow trainingRow){

        setupInputLayer(ann,trainingRow);

        for(int layerIndex = 1; layerIndex < ann.getLayerCount(); layerIndex++){
            updateLayerPerceptrons(ann,layerIndex);
        }

    }

    private static void setupInputLayer(ANN ann, TrainingRow trainingRow){

        LayerCollection layers = ann.getLayers();
        Layer inputLayer = layers.get(0);
        int inputLayerPerceptronCount = inputLayer.size();
        if(inputLayerPerceptronCount == (trainingRow.getSize()-1)){
            for(int index = 0; index < (inputLayerPerceptronCount-1); index++){
                double value = trainingRow.get(index);
                inputLayer.get(index).setActivationValue(value);
            }
        }

    }

    private static void updateLayerPerceptrons(ANN ann, int layerIndex){

        Layer currentLayer = ann.getLayers().get(layerIndex);
        Layer previousLayer = ann.getLayers().get(layerIndex - 1);

        for(int perceptronIndex = 0; perceptronIndex < (currentLayer.size()-1); perceptronIndex++){
            PerceptronUnit perceptronUnit = currentLayer.get(perceptronIndex);
            double value = computeActivationValue(previousLayer,perceptronIndex);
            perceptronUnit.setActivationValue(value);
        }

    }

    private static double computeActivationValue(Layer previousLayer, int perceptronIndex){

        double activationValue = 0;
        Matrix weightMatrix = previousLayer.getWeightMatrix();
        int previousLayerPerceptronCount = previousLayer.size();
        for(int index = 0; index < previousLayerPerceptronCount; index++){
            double currentActivationValue = previousLayer.get(index).getActivationValue();
            double currentWeight = weightMatrix.getValueAt(index,perceptronIndex);
            activationValue = activationValue + (currentActivationValue * currentWeight);
        }

        activationValue = SigmoidFunction.pass(activationValue);

        return activationValue;

    }

}
