package com.company.ml.ann;

/**
 * Created by AKEJU  FATAI on 2017-07-11.
 */
public class LayerFactory {

    public static Layer produce(int currentLayerPerceptronUnits, int nextLayerPerceptronUnits){

        Layer layer = new Layer(currentLayerPerceptronUnits,nextLayerPerceptronUnits);
        for(int count = 0; count < currentLayerPerceptronUnits; count++){
            PerceptronUnit perceptronUnit = new PerceptronUnit();
            layer.add(perceptronUnit);
        }

        return layer;

    }

}
