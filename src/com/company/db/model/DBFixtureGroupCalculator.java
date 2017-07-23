package com.company.db.model;

/**
 * Created by AKEJU  FATAI on 2017-06-07.
 */
public class DBFixtureGroupCalculator {

    public static double getTeamPerformance(String teamName, DBFixtureGroup fixtures){

        double value = 0;
        int teamNameScore;
        int opponentScore;
        for(int index = 0; index < fixtures.size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName)){
                teamNameScore = fixture.getResult().getHomeTeamScoreFullTime();
                opponentScore = fixture.getResult().getAwayTeamScoreFullTime();
            }
            else{
                teamNameScore = fixture.getResult().getAwayTeamScoreFullTime();
                opponentScore = fixture.getResult().getHomeTeamScoreFullTime();
            }

            if(teamNameScore > opponentScore){
                value = value + 3;
            }
            else if(teamNameScore == opponentScore){
                value = value + 1;
            }
            else{
                // value = value + 0;
            }
        }
        return (value / fixtures.size());

    }

    public static double getGoalsScoredFullTime(String teamName, DBFixtureGroup fixtures){

        double value = 0;
        int score;
        for(int index = 0; index < fixtures.size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName)){
                score = fixture.getResult().getHomeTeamScoreFullTime();
            }
            else{
                score = fixture.getResult().getAwayTeamScoreFullTime();
            }

            value = value + score;
        }
        return (value / fixtures.size());

    }

    public static double getGoalsScoredHalfTime(String teamName, DBFixtureGroup fixtures){

        double value = 0;
        int score;
        for(int index = 0; index < fixtures.size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName)){
                score = fixture.getResult().getHomeTeamScoreHalfTime();
            }
            else{
                score = fixture.getResult().getAwayTeamScoreHalfTime();
            }

            value = value + score;
        }
        return (value / fixtures.size());

    }

    public static double getGoalsConcededFullTime(String teamName, DBFixtureGroup fixtures){

        double value = 0;
        int score;
        for(int index = 0; index < fixtures.size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName)){
                score = fixture.getResult().getAwayTeamScoreFullTime();
            }
            else{
                score = fixture.getResult().getHomeTeamScoreFullTime();
            }

            value = value + score;
        }
        return (value / fixtures.size());

    }

    public static double getGoalsConcededHalfTime(String teamName, DBFixtureGroup fixtures){

        double value = 0;
        int score;
        for(int index = 0; index < fixtures.size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName)){
                score = fixture.getResult().getAwayTeamScoreHalfTime();
            }
            else{
                score = fixture.getResult().getHomeTeamScoreHalfTime();
            }

            value = value + score;
        }
        return (value / fixtures.size());

    }

}
