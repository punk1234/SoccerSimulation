package com.company.db.model;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBLeague {

    private String caption;
    private int id;
    private String league;
    private int year;
    private int currentMatchday;
    private int numberOfTeams;
    private Date lastUpdated;
    private List<DBTeam> teams;
    private List<DBFixture> fixtures;

    public DBLeague(){

        teams = new ArrayList<>();
        fixtures = new ArrayList<>();

    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<DBTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<DBTeam> teams) {
        this.teams = teams;
    }

    public List<DBFixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<DBFixture> fixtures) {
        this.fixtures = fixtures;
    }

    public void add(DBTeam dbTeam){

        teams.add(dbTeam);

    }

    public void add(DBFixture dbFixture){

        fixtures.add(dbFixture);

    }

}
