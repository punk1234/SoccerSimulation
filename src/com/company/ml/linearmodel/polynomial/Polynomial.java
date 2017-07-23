package com.company.ml.linearmodel.polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-04-02.
 */
public class Polynomial {

    private List<PUnit> pUnits;

    private String[] variableNames;

    private double constant = 0;

    public Polynomial(){

        pUnits = new ArrayList<>();

    }

    public Polynomial(List<PUnit> pUnits){

        this.pUnits = pUnits;

    }

    public void add(PUnit unit){

        pUnits.add(unit);

    }

    public PUnit getUnit(int index){

        if(index < pUnits.size()){
            return pUnits.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public double getConstant(){
        return constant;
    }

    public void setConstant(double constant){
        this.constant = constant;
    }

    public double getValue(Map<String,Double> values){

        double sum = 0;
        for(PUnit punit:pUnits){
            if(!values.containsKey(punit.getVariableName())){
                int b = 5;
                b = b + 2;
                System.out.println(b);
            }
            double variableValue = values.get(punit.getVariableName());
            double variablePower = punit.getPower();
            double unitValue = punit.getCoefficient() * Math.pow(variableValue,variablePower);
            sum = sum + unitValue;
        }
        sum = sum + constant;
        return sum;

    }

    public void setVariableNames(String[] variableNames){

        this.variableNames = variableNames;

    }

    public String[] getVariableNames(){

        return variableNames;

    }

    public void setCoefficient(double value){

        for(PUnit unit : pUnits){
            unit.setCoefficient(value);
        }

    }

    public int size(){

        return pUnits.size() + 1;

    }

}
