package com.company.api;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class Result {

    private Integer goalsHomeTeam;
    private Integer goalsAwayTeam;

    public Result(Integer goalsHomeTeam, Integer goalsAwayTeam){

        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;

    }

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
