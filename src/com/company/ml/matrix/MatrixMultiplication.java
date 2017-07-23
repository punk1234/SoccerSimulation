package com.company.ml.matrix;

/**
 * Created by AKEJU  FATAI on 2016-12-31.
 */
public class MatrixMultiplication {

    public static Matrix perform(Matrix matrix1, Matrix matrix2){
        int rowCount = matrix1.getRowCount();
        int columnCount = matrix2.getColumnCount();
        double[][] matrixContent = new double[rowCount][columnCount];
        int minimumlength = getMinimumLength(matrix1, matrix2);

        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++){
                matrixContent[i][j] = 0;
                for(int k = 0; k < minimumlength; k++){
                    matrixContent[i][j] += matrix1.getValueAt(i,k) + matrix2.getValueAt(k,j);
                }
            }
        }

        return new Matrix(matrixContent);
    }

    private static int getMinimumLength(Matrix matrix1, Matrix matrix2){
        int leftMatrixColumnCount = matrix1.getColumnCount();
        int rightMatrixRowCount = matrix2.getRowCount();
        if(leftMatrixColumnCount < rightMatrixRowCount){
            return leftMatrixColumnCount;
        }
        return rightMatrixRowCount;
    }

}
