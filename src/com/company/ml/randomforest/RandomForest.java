package com.company.ml.randomforest;

import java.util.*;
import com.company.ml.decisiontree.DecisionTree;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class RandomForest {

    private List<DecisionTree> decisionTrees;

    public RandomForest(){

    }

    public void add(DecisionTree decisionTree){

        decisionTrees.add(decisionTree);

    }

    public DecisionTree getDecisionTree(int index){

        if(index < decisionTrees.size()){
            return decisionTrees.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public int getNumberOfDecisionTree(){

        return decisionTrees.size();

    }

}
