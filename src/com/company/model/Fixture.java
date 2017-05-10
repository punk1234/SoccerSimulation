package com.company.model;

import java.util.Date;

/**
 * Created by AKEJU  FATAI on 2017-03-07.
 */
public class Fixture {

    private Integer id;

    private Team homeTeam;

    private Team awayTeam;

    private Date date;

    public Fixture(Team homeTeam, Team awayTeam, Date date){

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean include(Team team){

        if(team.equal(homeTeam) || team.equal(awayTeam)){
            return true;
        }
        return false;

    }

    public boolean between(Team homeTeam, Team awayTeam){

        if(this.homeTeam == homeTeam && this.awayTeam == awayTeam){
            return true;
        }
        return false;

    }

}
