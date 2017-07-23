package com.company.db.model;

import java.util.Date;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBFixture {

    private Date date;
    private String status;
    private int matchday;
    private String homeTeamName;
    private String awayTeamName;
    private DBResult result;

    public DBFixture(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public DBResult getResult() {
        return result;
    }

    public void setResult(DBResult result) {
        this.result = result;
    }

    public boolean contains(String teamName){

        if(teamName.equals(homeTeamName) || teamName.equals(awayTeamName)){
            return true;
        }
        return false;

    }

    public TeamStatus getTeamStatus(String teamName){

        if(teamName.equals(homeTeamName)){
            return TeamStatus.HOME;
        }
        else if(teamName.equals(awayTeamName)){
            return TeamStatus.AWAY;
        }
        else{
            return null;
        }

    }

}
