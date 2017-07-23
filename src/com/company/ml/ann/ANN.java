package com.company.ml.ann;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-05-07.
 */
public class ANN implements MachineLearningTechnique {

    private LayerCollection layers;

    private Architecture architecture;

    private Map<Integer,Double> outcomeMapping;

    public ANN(Architecture architecture){

        this.layers = null;

        this.architecture = architecture;

    }

    public LayerCollection getLayers(){

        return layers;

    }

    public int getLayerCount(){

        return layers.size();

    }

    @Override
    public void learn(TrainingSet trainingSet) {

        updateArchitecture(trainingSet);

        layers = LayerCollectionBuilder.build(architecture);

        generateOutcomeMapping(trainingSet);

        prepareTrainingSet(trainingSet);

        ANNTrainer.execute(this,trainingSet);

    }

    private void generateOutcomeMapping(TrainingSet trainingSet){

        outcomeMapping = new HashMap<>();
        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(lastColumnIndex);
        for(int index = 0; index < outcomes.size(); index++){
            int key = index + 1;
            double outcome = outcomes.get(index);
            outcomeMapping.put(key,outcome);
        }

    }

    private void prepareTrainingSet(TrainingSet trainingSet){

        int lastColumnIndex = trainingSet.getNumberOfColumns() - 1;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(lastColumnIndex);
        for(int rowIndex = 0; rowIndex < trainingSet.getNumberOfRows(); rowIndex++){
            TrainingRow trainingRow = trainingSet.get(rowIndex);
            Double lastColumnValue = trainingRow.get(lastColumnIndex);
            Double outcomeIndex = (double)(outcomes.indexOf(lastColumnValue));
            trainingRow.set(lastColumnIndex,outcomeIndex);
        }

    }

    private void updateArchitecture(TrainingSet trainingSet){

        int columnCount = trainingSet.getNumberOfColumns();
        int inputLayerSize = columnCount - 1;
        architecture.setInputLayerSize(inputLayerSize);
        int lastColumnIndex = inputLayerSize;
        List<Double> outcomes = trainingSet.getDistinctColumnValues(lastColumnIndex);
        int outputLayerSize = outcomes.size();
        architecture.setOutputLayerSize(outputLayerSize);

    }

    private double getMeanSquareError(){

        return 0.0;

    }

    @Override
    public Double predict(TrainingRow predictionSet) {

        ForwardPropagation.execute(this,predictionSet);

        int outcomePosition = getOutcomePosition();

        Double outcome = outcomeMapping.get(outcomePosition);

        return outcome;
    }

    private int getOutcomePosition(){

        Layer layer = layers.getOutputLayer();
        int outputPerceptronCount = layer.size();
        int index = 0;
        for(int perceptronIndex = 1; perceptronIndex < outputPerceptronCount; perceptronIndex++){
            PerceptronUnit perceptronUnit = layer.get(perceptronIndex);
            if(perceptronUnit.getActivationValue() > layer.get(index).getActivationValue()){
                index = perceptronIndex;
            }
        }
        int position = index + 1;
        return position;

    }

    @Override
    public String getName() {
        return "ann";
    }

}
