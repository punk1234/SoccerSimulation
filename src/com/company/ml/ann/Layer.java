package com.company.ml.ann;

import com.company.ml.matrix.Matrix;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-08.
 */
public class Layer {

    private PerceptronUnit[] perceptronUnits;
    private Matrix weightMatrix;
    private int count;

    public Layer(int size, int nextLayerSize){

        perceptronUnits = new PerceptronUnit[size];
        weightMatrix = new Matrix(generateMatrix(size,nextLayerSize));
        count = 0;

    }

    public Matrix getWeightMatrix(){

        return weightMatrix;

    }

    public void add(PerceptronUnit perceptronUnit){

        perceptronUnits[count++] = perceptronUnit;

    }

    public PerceptronUnit get(int index){

        if(index < perceptronUnits.length){
            return perceptronUnits[index];
        }
        throw new IndexOutOfBoundsException();

    }

    public int size(){

        return perceptronUnits.length;

    }

    private double[][] generateMatrix(int size, int nextLayerSize){

        double[][] matrix = new double[size][nextLayerSize];
        for(int rowIndex = 0; rowIndex < size; rowIndex++){
            for(int columnIndex = 0; columnIndex < nextLayerSize; columnIndex++){
                matrix[rowIndex][columnIndex] = 0.5;
            }
        }
        return matrix;

    }

}
