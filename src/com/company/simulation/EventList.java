package com.company.simulation;

import com.company.simulation.event.Event;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-23.
 */
public class EventList {

    private List<Event> events;

    public EventList(){

        events = new ArrayList<>();

    }

    public void add(Event event){

        events.add(event);

    }

    public Event getNextEvent() throws EmptyEventListException {

        if(events.size() > 0){
            Event nextEvent = events.remove(0);
            return nextEvent;
        }
        throw new EmptyEventListException();

    }

}
