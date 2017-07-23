package com.company.ml.model;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-19.
 */
public class TrainingSet {

    private Double[][] content;
    private int numberOfColumns;
    private int numberOfRows;

    public TrainingSet(int numberOfRows, int numberOfColumns){

        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        content = new Double[numberOfRows][numberOfColumns];

    }

    public int getNumberOfRows(){

        return numberOfRows;

    }

    public int getNumberOfColumns(){

        return numberOfColumns;

    }

    public void set(int rowIndex, TrainingRow trainingRow){

        if(rowIndex >= numberOfRows){
            throw new IndexOutOfBoundsException();
        }
        int trainingRowSize = trainingRow.getSize();
        if(trainingRowSize == numberOfColumns){
            for(int index = 0; index < trainingRowSize; index++){
                content[rowIndex][index] = trainingRow.get(index);
            }
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public void set(int rowIndex, int columnIndex, Double value){

        content[rowIndex][columnIndex] = value;

    }

    public Double get(int rowIndex, int columnIndex){

        if(rowIndex < numberOfRows && columnIndex < getNumberOfColumns()){
            return content[rowIndex][columnIndex];
        }
        throw new IndexOutOfBoundsException();

    }

    public TrainingRow get(int index){

        TrainingRow trainingRow = new TrainingRow(numberOfColumns);
        for(int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++){
            Double value = content[index][columnIndex];
            trainingRow.set(columnIndex,value);
        }
        return trainingRow;

    }

    public List<Double> getDistinctColumnValues(int columnIndex){

        List<Double> values = new ArrayList<>();
        if(columnIndex < numberOfColumns){
            for(int rowIndex = 0; rowIndex < numberOfRows; rowIndex++){
                Double currentRowValue = content[rowIndex][columnIndex];
                if(!values.contains(currentRowValue)){
                    values.add(currentRowValue);
                }
            }
            return values;
        }
        throw new IndexOutOfBoundsException();

    }

}
