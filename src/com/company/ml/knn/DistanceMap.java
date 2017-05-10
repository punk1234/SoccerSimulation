package com.company.ml.knn;

/**
 * Created by AKEJU  FATAI on 2017-04-14.
 */
public class DistanceMap {

    private int index;

    private double distance;

    public DistanceMap(int index, double distance){

        this.index = index;
        this.distance = distance;

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
