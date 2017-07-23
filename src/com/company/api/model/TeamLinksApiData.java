package com.company.api.model;

/**
 * Created by AKEJU  FATAI on 2017-05-04.
 */
public class TeamLinksApiData {

    private Link self;
    private Link fixtures;
    private Link players;

    public TeamLinksApiData(Link self, Link fixtures, Link players){

        this.self = self;
        this.fixtures = fixtures;
        this.players = players;

    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getFixtures() {
        return fixtures;
    }

    public void setFixtures(Link fixtures) {
        this.fixtures = fixtures;
    }

    public Link getPlayers() {
        return players;
    }

    public void setPlayers(Link players) {
        this.players = players;
    }

}
