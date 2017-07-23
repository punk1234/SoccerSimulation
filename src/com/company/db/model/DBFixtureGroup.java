package com.company.db.model;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-06-01.
 */
public class DBFixtureGroup {

    private List<DBFixture> fixtures;

    public DBFixtureGroup(){

        fixtures = new ArrayList<>();

    }

    public void add(DBFixture dbFixture){

        fixtures.add(dbFixture);

    }

    public DBFixture get(int index){

        return fixtures.get(index);

    }

    public DBFixtureGroup get(String teamName){

        DBFixtureGroup dbFixtureGroup = new DBFixtureGroup();
        for(int index = 0; index < size(); index++){
            DBFixture fixture = fixtures.get(index);
            if(fixture.getHomeTeamName().equals(teamName) || fixture.getAwayTeamName().equals(teamName)){
                dbFixtureGroup.add(fixture);
            }
        }
        return dbFixtureGroup;

    }

    public DBFixtureGroup getLast(int limit){

        if(limit < size()){
            DBFixtureGroup dbFixtureGroup = new DBFixtureGroup();
            int startIndex = size() - 1;
            int endIndex = size() - limit;
            for(int index = startIndex; index >= endIndex; index--){
                DBFixture dbFixture = fixtures.get(index);
                dbFixtureGroup.add(dbFixture);
            }
            return dbFixtureGroup;
        }
        throw new IllegalArgumentException();

    }

    public DBFixtureGroup getFixturesBefore(String homeTeamName, String awayTeamName, TeamStatus teamStatus, int limit){

        DBFixtureGroup dbFixtureGroup  = new DBFixtureGroup();
        String mainTeam = getTeam(homeTeamName,awayTeamName,teamStatus);
        for(int index = fixtures.size()-1; index >= 0; index--){
            DBFixture dbFixture = fixtures.get(index);
            if(dbFixture.getHomeTeamName().equals(homeTeamName) && dbFixture.getAwayTeamName().equals(awayTeamName)){
                for(index = index-1; index >= 0; index--){
                    DBFixture dbFixtureMain = fixtures.get(index);
                    if(dbFixtureGroup.size() < limit){
                        if(dbFixtureMain.contains(mainTeam)){
                            dbFixtureGroup.add(dbFixtureMain);
                        }
                    }
                    else{
                        break;
                    }
                }
                break;
            }
        }
        return dbFixtureGroup;

    }

    private String getTeam(String homeTeamName, String awayTeamName, TeamStatus teamStatus){

        if(teamStatus == TeamStatus.HOME){
            return homeTeamName;
        }
        return awayTeamName;

    }

    public DBFixture getFixture(String homeTeamName, String awayTeamName){

        for(DBFixture dbFixture : fixtures){
            if(dbFixture.getHomeTeamName().equals(homeTeamName) && dbFixture.getAwayTeamName().equals(awayTeamName)){
                return dbFixture;
            }
        }
        throw new IllegalArgumentException();

    }

    public int size(){

        return fixtures.size();

    }

    public double getTeamPerformance(String teamName){

        double value = 0;
        int teamNameScore;
        int opponentScore;
        for(DBFixture fixture : fixtures){
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

}
