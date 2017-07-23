package com.company.simulation.model;

import com.company.simulation.Location;

/**
 * Created by AKEJU  FATAI on 2017-06-24.
 */
public class Ball {

    private double radius;
    private Location location;

    public Ball(){

        radius = 1;
        location = new Location(0,0,0);

    }

    public Ball(double radius, Location location){

        this.radius = radius;
        this.location = location;

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
