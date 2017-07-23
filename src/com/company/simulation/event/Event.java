package com.company.simulation.event;

/**
 * Created by AKEJU  FATAI on 2017-05-23.
 */
public abstract class Event {

    protected double timeGenerated;
    protected double timeToOccur;

    public Event(double timeGenerated, double timeToOccur){

        this.timeGenerated = timeGenerated;
        this.timeToOccur = timeToOccur;

    }

    public double getTimeGenerated() {
        return timeGenerated;
    }

    public void setTimeGenerated(double timeGenerated) {
        this.timeGenerated = timeGenerated;
    }

    public double getTimeToOccur() {
        return timeToOccur;
    }

    public void setTimeToOccur(double timeToOccur) {
        this.timeToOccur = timeToOccur;
    }

    public abstract void execute();

}
