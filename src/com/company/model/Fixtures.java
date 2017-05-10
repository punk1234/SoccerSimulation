package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-04-11.
 */
public class Fixtures {

    private List<Fixture> value;

    public Fixtures(){

        value = new ArrayList<>();

    }

    public void add(Fixture fixture){

        value.add(fixture);

    }

    public void remove(Team homeTeam, Team awayTeam){

        for(Fixture fixture : value){
            if(fixture.between(homeTeam,awayTeam)){
                value.remove(fixture);
            }
        }

    }

    public Fixtures getFixtures(Team team){

        Fixtures fixtures = new Fixtures();
        for(Fixture fixture : value){
            if(fixture.include(team)){
                fixtures.add(fixture);
            }
        }
        return fixtures;

    }

    public int getCount(){

        return value.size();

    }

}
