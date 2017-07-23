package com.company.ml.linearmodel.polynomial;

/**
 * Created by AKEJU  FATAI on 2017-04-02.
 */
public class PUnit {

    private double coefficient;
    private String variableName;
    private double power;

    public PUnit(double coefficient, String variableName, double power){

        this.coefficient = coefficient;
        this.variableName = variableName;
        this.power = power;

    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getValue(double variableValue){

        return getValue(coefficient,variableValue);

    }

    public double getValue(double coefficient, double variableValue){

        double result = coefficient * Math.pow(variableValue,power);
        return result;

    }
}
