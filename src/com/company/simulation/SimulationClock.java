package com.company.simulation;

/**
 * Created by AKEJU  FATAI on 2017-05-23.
 */
public class SimulationClock {

    private double time;

    public SimulationClock(){

        time = 0;

    }

    public double getTime(){

        return time;

    }

    public void setTime(double time){

        this.time = time;

    }

    public void update(double deltaTime){

        time = time + deltaTime;

    }

}
