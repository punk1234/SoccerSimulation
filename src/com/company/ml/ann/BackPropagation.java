package com.company.ml.ann;

import com.company.ml.matrix.Matrix;

/**
 * Created by AKEJU  FATAI on 2017-07-13.
 */
public class BackPropagation {

    private static final double LEARNING_RATE = 1;

    public static void execute(ANN ann, double output){

        int outputLayerIndex = ann.getLayerCount() - 1;

        // compute error values for each perceptron in the layers ( hidden and output layers )
        for(int currentLayerIndex = outputLayerIndex; currentLayerIndex >= 1; currentLayerIndex--){
            Layer layer = ann.getLayers().get(currentLayerIndex);
            int perceptronSize = layer.size();
            if(currentLayerIndex != outputLayerIndex){
                perceptronSize = perceptronSize - 1;
            }
            for(int perceptronIndex = 0; perceptronIndex < perceptronSize; perceptronIndex++){
                computeErrorValue(ann,currentLayerIndex,perceptronIndex,output);
            }
        }

        for(int currentLayerIndex = 0; currentLayerIndex < outputLayerIndex; currentLayerIndex++){
            Layer layer = ann.getLayers().get(currentLayerIndex);
            Layer nextLayer = ann.getLayers().get(currentLayerIndex + 1);
            Matrix weightMatrix = layer.getWeightMatrix();
            int perceptronSize = layer.size();
            for(int perceptronIndex = 0; perceptronIndex < perceptronSize; perceptronIndex++){
                for(int weightIndex = 0; weightIndex < weightMatrix.getColumnCount(); weightIndex++){
                    double currentWeightValue = weightMatrix.getValueAt(perceptronIndex,weightIndex);
                    double errorValue = nextLayer.get(weightIndex).getErrorValue();
                    double activationValue = layer.get(perceptronIndex).getActivationValue();
                    currentWeightValue = currentWeightValue + (LEARNING_RATE * errorValue * activationValue);
                    weightMatrix.setValue(perceptronIndex,weightIndex,currentWeightValue);
                }
            }
        }

    }

    private static void computeErrorValue(ANN ann, int layerIndex, int perceptronIndex, double output){

        Layer layer = ann.getLayers().get(layerIndex);
        int lastLayerIndex = ann.getLayerCount() - 1;
        if(layerIndex == lastLayerIndex){
            computeLastLayerError(layer,perceptronIndex,output);
        }
        else{
            computeLayerError(ann,layerIndex,perceptronIndex);
        }

    }

    private static void computeLastLayerError(Layer layer, int perceptronUnitIndex, double output){

        PerceptronUnit perceptronUnit = layer.get(perceptronUnitIndex);
        double activationValue = perceptronUnit.getActivationValue();
        double yValue = 0;
        if((perceptronUnitIndex + 1) == output){
            yValue = 1;
        }
        double errorValue = yValue - activationValue;
        perceptronUnit.setErrorValue(errorValue);

    }


    private static void computeLayerError(ANN ann, int layerIndex, int perceptronUnitIndex){

        LayerCollection layers = ann.getLayers();
        Layer layer = layers.get(layerIndex);
        PerceptronUnit perceptronUnit = layer.get(perceptronUnitIndex);
        double activationValue = perceptronUnit.getActivationValue();

        double derivative = activationValue * (1 - activationValue);

        double weightErrorSum = 0;
        Layer nextLayer = layers.get(layerIndex + 1);
        for(int index = 0; index < nextLayer.size(); index++){
            weightErrorSum = weightErrorSum + layer.getWeightMatrix().getValueAt(perceptronUnitIndex,index);
        }

        double errorValue = weightErrorSum * derivative;
        perceptronUnit.setErrorValue(errorValue);

    }

    private void updateWeights(){



    }

}
