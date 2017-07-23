package com.company.simulation.event;

import com.company.simulation.event.Event;

/**
 * Created by AKEJU  FATAI on 2017-05-30.
 */
public class StopEvent extends Event {

    public StopEvent(double timeGenerated, double timeToOccur){

        super(timeGenerated, timeToOccur);

    }

    public void execute(){

    }

}
