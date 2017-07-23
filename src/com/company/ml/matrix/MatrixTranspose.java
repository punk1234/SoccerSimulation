package com.company.ml.matrix;

/**
 * Created by AKEJU  FATAI on 2016-12-31.
 */
public class MatrixTranspose {

    public static Matrix perform(Matrix matrix){
        int rowCount = matrix.getRowCount();
        int columnCount = matrix.getColumnCount();
        double[][] matrixContent = new double[columnCount][rowCount];

        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++){
                matrixContent[i][j] = matrix.getValueAt(j,i);
            }
        }

        return new Matrix(matrixContent);
    }

}
