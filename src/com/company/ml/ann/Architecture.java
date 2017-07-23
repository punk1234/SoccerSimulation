package com.company.ml.ann;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-07-01.
 */
public class Architecture {

    private int[] layers;

    public Architecture(int size){

        if(size < 2){
            throw new IllegalArgumentException();
        }
        layers = new int[size];

    }

    public void setInputLayerSize(int inputLayerSize){

        layers[0] = inputLayerSize;

    }

    public void setOutputLayerSize(int outputLayerSize){

        int outputIndex = layers.length - 1;
        layers[outputIndex] = outputLayerSize;

    }

    public int getInputLayerSize(){

        return layers[0];

    }

    public int getOutputSize(){

        int outputIndex = layers.length - 1;
        return layers[outputIndex];

    }

    public int getLayerSize(int index){

        if(index >= size()){
            throw new IllegalArgumentException();
        }
        return layers[index];

    }

    public int size(){

        return layers.length;

    }

    public void setHiddenLayerSize(int position, int size){

        if(position > 0 && position < (layers.length - 1)){
            layers[position] = size;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public int getHiddenLayersSize(){

        return layers.length - 2;

    }

    public int getHiddenLayerSize(int position){

        if(position <= (layers.length - 2)){
            return layers[position];
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public void set(int index, int value){

        layers[index] = value;

    }

    public int get(int index){

        return layers[index];

    }

}
