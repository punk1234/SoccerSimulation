package com.company.match.resultcategory;

import com.company.match.resultprocessor.ResultProcessor;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public class ResultCategoryCollection {

    private List<ResultCategory> resultCategorys;

    public ResultCategoryCollection(){

        resultCategorys = new ArrayList<>();

    }

    public void add(ResultCategory resultCategory){

        resultCategorys.add(resultCategory);

    }

    public ResultCategory get(int index){

        if(index < count()){
            return resultCategorys.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public ResultProcessor get(String categoryName){

        for(ResultCategory resultCategory : resultCategorys){
            if(resultCategory.getName().equals(categoryName)){
                return resultCategory.getResultProcessor();
            }
        }
        throw new IllegalArgumentException();

    }

    public int count(){

        return resultCategorys.size();

    }

}
