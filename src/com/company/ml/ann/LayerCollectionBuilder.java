package com.company.ml.ann;

/**
 * Created by AKEJU  FATAI on 2017-07-01.
 */
public class LayerCollectionBuilder {

    public static LayerCollection build(Architecture architecture){

        int size = architecture.size();

//        for(int index = 0; index < (size-1); index++){
//            int value = architecture.get(index);
//            value = value + 1;
//            architecture.set(index,value);
//        }

        LayerCollection layers = new LayerCollection(size);

        int outputIndex = size - 1;

        for(int index = 0; index < outputIndex; index++){

            int currentLayerSize = architecture.getLayerSize(index);

            int nextLayerSize = architecture.getLayerSize((index + 1));

            Layer layer = LayerFactory.produce((currentLayerSize+1),nextLayerSize);

            PerceptronUnit perceptronUnit = layer.get(currentLayerSize);

            perceptronUnit.setActivationValue(1);

            layers.set(index,layer);

        }

        Layer outputLayer = LayerFactory.produce(architecture.getOutputSize(),0);

        layers.set(outputIndex,outputLayer);

        return layers;

    }

}
