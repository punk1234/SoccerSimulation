package com.company.match;

/**
 * Created by AKEJU  FATAI on 2017-05-27.
 */
public class Match {

    private String homeTeamName;
    private String awayTeamName;
    private Result result;

    public Match(String homeTeamName, String awayTeamName, Result result){

        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.result = result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
