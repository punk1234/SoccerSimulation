package com.company.simulation.model;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-23.
 */
public class Team {

    String name;
    private List<Player> players;

    public Team(String name){

        this.name = name;
        this.players = new ArrayList<>();

    }

    public void add(Player player){

        players.add(player);

    }

    public void remove(Player player){

        players.remove(player);

    }

    public int count(){

        return players.size();

    }

}
