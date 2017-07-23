package com.company.ml.matrix;

/**
 * Created by AKEJU  FATAI on 2016-12-31.
 */
public class Matrix {

    private double[][] content;

    public Matrix(double[][] content){

        this.content = content;

    }

    public double getValueAt(int rowIndex, int columnIndex){

        return content[rowIndex][columnIndex];

    }

    public void setValue(int rowIndex, int columnIndex, double value){

        this.content[rowIndex][columnIndex] = value;

    }

    public int getRowCount(){

        return content.length;

    }

    public int getColumnCount(){

        if(content.length > 0){

            int firstRowIndex = 0;

            return content[firstRowIndex].length;

        }

        return -1;
    }

    public boolean isSquare(){

        int rowLength = getRowCount();

        int columnLength = getColumnCount();

        if(rowLength == columnLength){

            return true;

        }

        return false;
    }

}
