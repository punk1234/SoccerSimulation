package com.company.api.model;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class FixtureLinksApiData extends SoccerSeasonTeamsLinksApiData {

    private Link homeTeam;
    private Link awayTeam;

    public FixtureLinksApiData(Link self, Link soccerSeason){

        super(self, soccerSeason);

    }

    public Link getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Link homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Link getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Link awayTeam) {
        this.awayTeam = awayTeam;
    }
}
