package com.company.ml.matrix;

/**
 * Created by AKEJU  FATAI on 2016-12-31.
 */
public class MatrixSubtraction {

    public static Matrix perform(Matrix matrix1, Matrix matrix2){
        if(matrix1.getRowCount() == matrix2.getRowCount() && matrix1.getColumnCount() == matrix2.getColumnCount()){
            int rowCount = matrix1.getRowCount();
            int columnCount = matrix1.getColumnCount();
            double[][] matrixContent = new double[rowCount][columnCount];

            for(int i = 0; i < rowCount; i++){
                for(int j = 0; j < columnCount; j++){
                    matrixContent[i][j] = matrix1.getValueAt(i,j) - matrix2.getValueAt(i,j);
                }
            }

            return new Matrix(matrixContent);
        }

        return null;
    }

}
