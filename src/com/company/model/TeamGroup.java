package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-04-11.
 */
public class TeamGroup {

    private List<Team> teams;

    public TeamGroup(){

        teams = new ArrayList<>();

    }

    public Team getTeam(int index){

        if(index >= getCount()){
            throw new IndexOutOfBoundsException();
        }
        Team team = teams.get(index);
        return team;

    }

    public int getCount(){

        return teams.size();

    }

    public void add(Team team){

        teams.add(team);

    }

}
