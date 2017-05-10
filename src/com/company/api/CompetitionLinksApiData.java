package com.company.api;

/**
 * Created by AKEJU  FATAI on 2017-04-29.
 */
public class CompetitionLinksApiData {

    private Link self;
    private Link teams;
    private Link fixtures;
    private Link leagueTable;

    public CompetitionLinksApiData(Link self, Link teams, Link fixtures, Link leagueTable){

        this.self = self;
        this.teams = teams;
        this.fixtures = fixtures;
        this.leagueTable = leagueTable;

    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getTeams() {
        return teams;
    }

    public void setTeams(Link teams) {
        this.teams = teams;
    }

    public Link getFixtures() {
        return fixtures;
    }

    public void setFixtures(Link fixtures) {
        this.fixtures = fixtures;
    }

    public Link getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(Link leagueTable) {
        this.leagueTable = leagueTable;
    }
}
