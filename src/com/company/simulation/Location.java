package com.company.simulation;

/**
 * Created by AKEJU  FATAI on 2017-06-24.
 */
public class Location {

    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z){

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
