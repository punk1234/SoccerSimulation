package com.company.match.resultcategory;

import com.company.match.resultprocessor.ResultProcessor;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public class ResultCategory {

    private String name;
    private ResultProcessor resultProcessor;

    public ResultCategory(String name, ResultProcessor resultProcessor){

        this.name = name;
        this.resultProcessor = resultProcessor;

    }

    public String getName(){

        return name;

    }

    public ResultProcessor getResultProcessor(){

        return resultProcessor;

    }

}
