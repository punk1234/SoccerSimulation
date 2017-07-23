package com.company.ml.decisiontree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AKEJU  FATAI on 2017-04-19.
 */
public class Node {

    private int feature;
    private Map<Double,Node> nodeMappings;
    private Map<Double,Double> outcomeMappings;

    public Node(){

        nodeMappings = new HashMap<>();
        outcomeMappings = new HashMap<>();

    }

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    public Map<Double, Node> getNodeMappings() {
        return nodeMappings;
    }

    public void setNodeMappings(Map<Double, Node> nodeMappings) {
        this.nodeMappings = nodeMappings;
    }

    public Map<Double, Double> getOutcomeMappings() {
        return outcomeMappings;
    }

    public void setOutcomeMappings(Map<Double, Double> outcomeMappings) {
        this.outcomeMappings = outcomeMappings;
    }
}
