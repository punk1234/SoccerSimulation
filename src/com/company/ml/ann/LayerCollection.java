package com.company.ml.ann;

/**
 * Created by AKEJU  FATAI on 2017-07-13.
 */
public class LayerCollection {

    private Layer[] layers;

    public LayerCollection(int size){

        if(size < 2){
            throw new IllegalArgumentException();
        }

        layers = new Layer[size];

    }

    public int size(){

        return layers.length;

    }

    public void set(int index, Layer layer){

        layers[index] = layer;

    }

    public Layer get(int index){

        if(index >= size() ){
            throw new IllegalArgumentException();
        }
        return layers[index];

    }

    public Layer getInputLayer(){

        return get(0);

    }

    public Layer getOutputLayer(){

        int lastColumnIndex = layers.length - 1;
        return get(lastColumnIndex);

    }

}
