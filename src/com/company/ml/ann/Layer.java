package com.company.ml.ann;

/**
 * Created by AKEJU  FATAI on 2017-05-08.
 */
public class Layer {

    private PerceptronUnit[] perceptronUnits;

    public Layer(PerceptronUnit[] perceptronUnits){

        this.perceptronUnits = perceptronUnits;

    }

    public PerceptronUnit get(int index){

        if(index < perceptronUnits.length){
            return perceptronUnits[index];
        }
        throw new IndexOutOfBoundsException();

    }

}
