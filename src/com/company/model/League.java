package com.company.model;

import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-03-07.
 */
public class League {

    private Integer id;

    private String name;

    private TeamGroup teams;

    private List<Fixture> fixtures;

    public League(TeamGroup teams){

        this.teams = teams;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamGroup getTeams() {
        return teams;
    }

    public void setTeams(TeamGroup teams) {
        this.teams = teams;
    }
}
