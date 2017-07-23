package com.company.match.resultcategory;

import com.company.match.resultprocessor.*;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */
public class ResultCategoryCollectionBuilder {

    public static ResultCategoryCollection build(){

        ResultCategoryCollection resultCategoryCollection = new ResultCategoryCollection();

        ResultCategory straightWinningResultCategory = new ResultCategory("straight winning", new StraightWinningProcessor());
        ResultCategory doubleChanceResultCategory = new ResultCategory("double chance", new DoubleChanceProcessor());
        ResultCategory evenOddResultCategory = new ResultCategory("even-odd", new EvenOddProcessor());
        ResultCategory goalGoalResultCategory = new ResultCategory("gg-ng", new GoalGoalProcessor());
        //ResultCategory highestScoringHalfResultCategory = new ResultCategory("highest scoring half", new HighestScoringHalfProcessor());
        ResultCategory overUnderFullTimeResultCategory = new ResultCategory("over-under ft", new OverUnderFullTimeProcessor(2));
        //ResultCategory overUnderHalfTimeResultCategory = new ResultCategory("over-under ht", new OverUnderHalfTimeProcessor(2));

        resultCategoryCollection.add(straightWinningResultCategory);
        resultCategoryCollection.add(doubleChanceResultCategory);
        resultCategoryCollection.add(evenOddResultCategory);
        resultCategoryCollection.add(goalGoalResultCategory);
        //resultCategoryCollection.add(highestScoringHalfResultCategory);
        resultCategoryCollection.add(overUnderFullTimeResultCategory);
        //resultCategoryCollection.add(overUnderHalfTimeResultCategory);

        return resultCategoryCollection;

    }

}
