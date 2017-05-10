package com.company.model;

import java.util.Date;

/**
 * Created by AKEJU  FATAI on 2017-04-11.
 */
public class MatchResult extends Fixture {

    private int homeScore;

    private int awayScore;

    public MatchResult(Team homeTeam, Team awayTeam, Date date, int homeScore, int awayScore){

        super(homeTeam,awayTeam,date);
        this.homeScore = homeScore;
        this.awayScore = awayScore;

    }

    public int getHomeScore(){

        return homeScore;

    }

    public int getAwayScore(){

        return awayScore;

    }

}
