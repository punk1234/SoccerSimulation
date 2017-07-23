package com.company.ml.ann;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-08.
 */
public class PerceptronUnit {

    private double activationValue;

    private double errorValue;

    public PerceptronUnit(){

    }

    public void setActivationValue(double activationValue){

        this.activationValue = activationValue;

    }

    public double getActivationValue(){

        return activationValue;

    }

    public void setErrorValue(double errorValue){

        this.errorValue = errorValue;

    }

    public double getErrorValue(){

        return errorValue;

    }

}
