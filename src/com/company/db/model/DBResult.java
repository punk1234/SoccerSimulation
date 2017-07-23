package com.company.db.model;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBResult {

    private Integer homeTeamScoreHalfTime;
    private Integer awayTeamScoreHalfTime;
    private Integer homeTeamScoreFullTime;
    private Integer awayTeamScoreFullTime;

    public DBResult(){

    }

    public Integer getHomeTeamScoreHalfTime() {
        return homeTeamScoreHalfTime;
    }

    public void setHomeTeamScoreHalfTime(Integer homeTeamScoreHalfTime) {
        this.homeTeamScoreHalfTime = homeTeamScoreHalfTime;
    }

    public Integer getAwayTeamScoreHalfTime() {
        return awayTeamScoreHalfTime;
    }

    public void setAwayTeamScoreHalfTime(Integer awayTeamScoreHalfTime) {
        this.awayTeamScoreHalfTime = awayTeamScoreHalfTime;
    }

    public Integer getHomeTeamScoreFullTime() {
        return homeTeamScoreFullTime;
    }

    public void setHomeTeamScoreFullTime(Integer homeTeamScoreFullTime) {
        this.homeTeamScoreFullTime = homeTeamScoreFullTime;
    }

    public Integer getAwayTeamScoreFullTime() {
        return awayTeamScoreFullTime;
    }

    public void setAwayTeamScoreFullTime(Integer awayTeamScoreFullTime) {
        this.awayTeamScoreFullTime = awayTeamScoreFullTime;
    }
}
