package com.company.api.model;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class SoccerSeasonTeamsLinksApiData {

    private Link self;
    private Link soccerSeason;

    public SoccerSeasonTeamsLinksApiData(Link self, Link soccerSeason){

        this.self = self;
        this.soccerSeason = soccerSeason;

    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getSoccerSeason() {
        return soccerSeason;
    }

    public void setSoccerSeason(Link soccerSeason) {
        this.soccerSeason = soccerSeason;
    }

}
