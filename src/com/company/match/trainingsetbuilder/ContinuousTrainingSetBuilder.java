package com.company.match.trainingsetbuilder;

import com.company.db.model.*;
import com.company.match.Result;
import com.company.match.resultprocessor.ResultProcessor;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-06-19.
 */
public class ContinuousTrainingSetBuilder implements TrainingSetBuilder {

    private int numberOfColumns;
    private ResultProcessor resultProcessor;

    public ContinuousTrainingSetBuilder(int numberOfColumns, ResultProcessor resultProcessor){

        this.numberOfColumns = numberOfColumns;
        this.resultProcessor = resultProcessor;

    }

    @Override
    public TrainingSet build(String teamName, DBFixtureGroup fixtures) {

        // average builder
        int limit = 20;
        DBFixtureGroup teamNameFixtures = fixtures.get(teamName).getLast(limit);
        int fixtureSize = teamNameFixtures.size() - 1;
        TrainingSet trainingSet = new TrainingSet(fixtureSize,numberOfColumns);
        DBFixtureGroup currentFixtures = new DBFixtureGroup();
        for(int fixtureIndex = 1; fixtureIndex < fixtureSize; fixtureIndex++){
            DBFixture mainFixture = teamNameFixtures.get(fixtureIndex);
            DBFixture dbFixture = teamNameFixtures.get(fixtureIndex-1);
            currentFixtures.add(dbFixture);
            TrainingRow trainingRow = new TrainingRow(numberOfColumns);

            // performance
            double teamNamePerformance = DBFixtureGroupCalculator.getTeamPerformance(teamName,currentFixtures); // currentFixtures.getTeamPerformance(teamName);
            TeamStatus mainTeamStatus = mainFixture.getTeamStatus(teamName);
            TeamStatus opponentTeamStatus;
            if(mainTeamStatus == TeamStatus.HOME){
                opponentTeamStatus = TeamStatus.AWAY;
            }
            else{
                opponentTeamStatus = TeamStatus.HOME;
            }
            String opponentName = getOpponentName(teamNameFixtures.get(fixtureIndex),teamName);
            DBFixtureGroup opponentFixtures = fixtures.get(opponentName).getFixturesBefore(mainFixture.getHomeTeamName(),mainFixture.getAwayTeamName(),opponentTeamStatus,fixtureIndex);
            double opponentPerformance = DBFixtureGroupCalculator.getTeamPerformance(opponentName,opponentFixtures);

            trainingRow.set(0,getStatus(teamName,dbFixture));
            trainingRow.set(1,teamNamePerformance);
            trainingRow.set(2,opponentPerformance);
            // goals scored full time
            trainingRow.set(3,DBFixtureGroupCalculator.getGoalsScoredFullTime(teamName,currentFixtures));
            trainingRow.set(4,DBFixtureGroupCalculator.getGoalsScoredFullTime(opponentName,opponentFixtures));
            // goals scored half time
            /* trainingRow.set(5,DBFixtureGroupCalculator.getGoalsScoredHalfTime(teamName,currentFixtures));
            trainingRow.set(6,DBFixtureGroupCalculator.getGoalsScoredHalfTime(opponentName,opponentFixtures)); */
            // goals conceded full time
            trainingRow.set(5,DBFixtureGroupCalculator.getGoalsConcededFullTime(teamName,currentFixtures));
            trainingRow.set(6,DBFixtureGroupCalculator.getGoalsConcededFullTime(opponentName,opponentFixtures));
            // goals conceded half time
            /* trainingRow.set(9,DBFixtureGroupCalculator.getGoalsConcededHalfTime(teamName,currentFixtures));
            trainingRow.set(10,DBFixtureGroupCalculator.getGoalsConcededHalfTime(opponentName,opponentFixtures)); */
            // result
            trainingRow.set(7,resultProcessor.getValue(convert(mainFixture.getResult())));

            trainingSet.set((fixtureIndex-1),trainingRow);

        }

        return trainingSet;
    }

    private Result convert(DBResult dbResult){

        Result result = new Result();
        result.setHomeTeamScoreFullTime(dbResult.getHomeTeamScoreFullTime());
        result.setAwayTeamScoreFullTime(dbResult.getAwayTeamScoreFullTime());
        result.setHomeTeamScoreHalfTime(dbResult.getHomeTeamScoreHalfTime());
        result.setAwayTeamScoreHalfTime(dbResult.getAwayTeamScoreHalfTime());
        return result;

    }

    public TrainingSet buildGeneral(DBFixtureGroup fixtures) {

        // normal builder
        int fixtureSize = fixtures.size();
        TrainingSet trainingSet = new TrainingSet(fixtureSize,numberOfColumns);
        for(int fixtureIndex = 1; fixtureIndex < fixtureSize; fixtureIndex++){
            DBFixture currentFixture = fixtures.get(fixtureIndex);
            String homeTeamName = currentFixture.getHomeTeamName();
            String awayTeamName = currentFixture.getAwayTeamName();

            DBFixtureGroup homeTeamFixtures = fixtures.getFixturesBefore(homeTeamName,awayTeamName,TeamStatus.HOME,10);
            DBFixtureGroup awayTeamFixtures = fixtures.getFixturesBefore(homeTeamName,awayTeamName,TeamStatus.AWAY,10);

            TrainingRow trainingRow = new TrainingRow(numberOfColumns);
            trainingRow.set(0,DBFixtureGroupCalculator.getTeamPerformance(homeTeamName,homeTeamFixtures));
            trainingRow.set(1,DBFixtureGroupCalculator.getTeamPerformance(awayTeamName,awayTeamFixtures));
            trainingRow.set(2,DBFixtureGroupCalculator.getGoalsScoredFullTime(homeTeamName,homeTeamFixtures));
            trainingRow.set(3,DBFixtureGroupCalculator.getGoalsScoredFullTime(awayTeamName,awayTeamFixtures));
            trainingRow.set(4,DBFixtureGroupCalculator.getGoalsConcededFullTime(homeTeamName,homeTeamFixtures));
            trainingRow.set(5,DBFixtureGroupCalculator.getGoalsConcededFullTime(awayTeamName,awayTeamFixtures));
            trainingRow.set(6,resultProcessor.getValue(convert(currentFixture.getResult())));
            trainingSet.set((fixtureIndex-1),trainingRow);

        }

        return trainingSet;
    }

    private String getOpponentName(DBFixture fixture, String teamName){

        if(!fixture.getHomeTeamName().equals(teamName)){
            return fixture.getHomeTeamName();
        }
        if(!fixture.getAwayTeamName().equals(teamName)){
            return fixture.getAwayTeamName();
        }
        throw new IllegalArgumentException();

    }

    private double getStatus(String teamName, DBFixture dbFixture){

        if(dbFixture.getHomeTeamName().equals(teamName)){
            return 1;
        }
        else if(dbFixture.getAwayTeamName().equals(teamName)){
            return 5;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

}
