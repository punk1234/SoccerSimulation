package com.company.ml.model;

/**
 * Created by AKEJU  FATAI on 2017-05-19.
 */
public class TrainingRow {

    private Double[] content;
    private int size;

    public TrainingRow(int size){

        this.size = size;
        content = new Double[size];

    }

    public int getSize(){

        return size;

    }

    public void set(int index, Double value){

        if(index >= content.length){
            throw new IndexOutOfBoundsException();
        }
        content[index] = value;

    }

    public Double get(int index){

        if(index >= content.length){
            throw new IndexOutOfBoundsException();
        }
        return content[index];

    }

}
