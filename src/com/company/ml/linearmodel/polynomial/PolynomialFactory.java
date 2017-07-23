package com.company.ml.linearmodel.polynomial;

/**
 * Created by AKEJU  FATAI on 2017-04-02.
 */
public class PolynomialFactory {

    // for univariate, variateValue = 1
    // for multivariate, variateValue > 1, that is, 2,3,4,5,...
    public static Polynomial create(int variateValue, int maximumDegree, String variablePrefix){

        //String prefix = "x";
        String[] variableNames = createVariableNames(variablePrefix,variateValue);

        Polynomial polynomial = createPolynomial(variableNames,maximumDegree);

        return polynomial;

    }

    public static Polynomial create(String[] variableNames, int maximumDegree){

        Polynomial polynomial = createPolynomial(variableNames,maximumDegree);

        return polynomial;

    }

    private static Polynomial createPolynomial(String[] variableNames, int maximumDegree){

        Polynomial polynomial = new Polynomial();
        for(int degree = maximumDegree; degree >= 1; degree--){
            for(String variableName:variableNames){
                double coefficient = 1;
                PUnit p = new PUnit(coefficient,variableName,degree);
                polynomial.add(p);
            }
        }

        polynomial.setVariableNames(variableNames);

        return polynomial;

    }

    private static String[] createVariableNames(String prefix, int numberOfVariables){

        String[] variableNames = new String[numberOfVariables];
        for(int index = 0; index < numberOfVariables; index++){
            variableNames[index] = prefix + (index + 1);
        }
        return variableNames;

    }

}
