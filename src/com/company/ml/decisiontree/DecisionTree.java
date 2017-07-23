package com.company.ml.decisiontree;

import java.util.*;

import com.company.ml.MachineLearningTechnique;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class DecisionTree implements MachineLearningTechnique {

    private Node root;

    public DecisionTree(){

    }

    public void setRoot(Node root){

        this.root = root;

    }

    public Node getRoot(){

        return root;

    }

    @Override
    public void learn(TrainingSet trainingSet){

        DecisionTree decisionTree = DecisionTreeBuilder.build(trainingSet);
        this.root = decisionTree.getRoot();

    }

    @Override
    public Double predict(TrainingRow predictionSet){

        return predict(predictionSet,root);

    }

    private Double predict(TrainingRow predictionSet, Node node){

        int feature = root.getFeature();
        Double nodeFeatureValue = predictionSet.get(feature);

        if(node.getOutcomeMappings().containsKey(nodeFeatureValue)){
            Double outcome = node.getOutcomeMappings().get(nodeFeatureValue);
            return outcome;
        }

        if(node.getNodeMappings().containsKey(nodeFeatureValue)){
            Node nextNode = node.getNodeMappings().get(nodeFeatureValue);
            return predict(predictionSet,nextNode);
        }

        if(!node.getNodeMappings().isEmpty()){
            List<Double> keys = new ArrayList<>(node.getNodeMappings().keySet());
            Node nextNode = node.getNodeMappings().get(keys.get(0));
            return predict(predictionSet,nextNode);
        }
        else{
            List<Double> keys = new ArrayList<>(node.getOutcomeMappings().keySet());
            Double outcome = node.getOutcomeMappings().get(keys.get(0));
            return outcome;
        }

        // throw new IllegalArgumentException();

    }

    @Override
    public String getName() {
        return "decision_tree";
    }

}
