package com.company.match.trainingpredictiondatabuilder;

import com.company.db.model.*;
import com.company.match.Result;
import com.company.match.resultprocessor.ResultProcessor;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-06-20.
 */
public class TrainingPredictionDataBuilder {

    private int numberOfColumns;
    private ResultProcessor resultProcessor;

    public TrainingPredictionDataBuilder(int numberOfColumns, ResultProcessor resultProcessor){

        this.numberOfColumns = numberOfColumns;
        this.resultProcessor = resultProcessor;

    }

    public TrainingPredictionData build(String homeTeamName, String awayTeamName, String teamName, DBFixtureGroup fixtures) {

        // average builder
        int limit = 20;
        DBFixtureGroup teamNameFixtures = fixtures.get(teamName).getLast(limit);
        int fixtureSize = teamNameFixtures.size() - 1;

        TrainingSet trainingSet = new TrainingSet(fixtureSize,numberOfColumns);
        TrainingRow predictionSet = new TrainingRow(numberOfColumns-1);

        // build the training set
        DBFixtureGroup currentFixtures = new DBFixtureGroup();
        for(int fixtureIndex = 1; fixtureIndex <= fixtureSize; fixtureIndex++){
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


        // build the prediction set
        // performance
        String opponentName = getOpponentName(homeTeamName,awayTeamName,teamName);
        double teamNamePerformance = DBFixtureGroupCalculator.getTeamPerformance(teamName,currentFixtures); // currentFixtures.getTeamPerformance(teamName);
        double opponentPerformance = DBFixtureGroupCalculator.getTeamPerformance(opponentName,currentFixtures);
        DBFixtureGroup teamFixtures = fixtures.get(teamName).getLast(20);
        DBFixtureGroup opponentFixtures = fixtures.get(teamName).getLast(20);
        predictionSet.set(0,getStatus(homeTeamName,awayTeamName,teamName));
        predictionSet.set(1,teamNamePerformance);
        predictionSet.set(2,opponentPerformance);
        predictionSet.set(3,DBFixtureGroupCalculator.getGoalsScoredFullTime(teamName,teamFixtures));
        predictionSet.set(4,DBFixtureGroupCalculator.getGoalsScoredFullTime(opponentName,opponentFixtures));
        predictionSet.set(5,DBFixtureGroupCalculator.getGoalsConcededFullTime(teamName,teamFixtures));
        predictionSet.set(6,DBFixtureGroupCalculator.getGoalsConcededFullTime(opponentName,opponentFixtures));

        TrainingPredictionData trainingPredictionData = new TrainingPredictionData(trainingSet,predictionSet);

        return trainingPredictionData;
    }

    private TrainingSet buildTrainingSet(){

        int numOfRows = 1;
        int numOfColumns = 1;
        TrainingSet trainingSet = new TrainingSet(numOfRows,numOfColumns);
        return trainingSet;

    }

    public TrainingRow buildPredictionSet(String homeTeamName, String awayTeamName, TeamStatus mainTeamStatus, DBFixtureGroup fixtures){

        int size = 1;
        TrainingRow predictionSet = new TrainingRow(size);
        return predictionSet;

    }

    private Result convert(DBResult dbResult){

        Result result = new Result();
        result.setHomeTeamScoreFullTime(dbResult.getHomeTeamScoreFullTime());
        result.setAwayTeamScoreFullTime(dbResult.getAwayTeamScoreFullTime());
        result.setHomeTeamScoreHalfTime(dbResult.getHomeTeamScoreHalfTime());
        result.setAwayTeamScoreHalfTime(dbResult.getAwayTeamScoreHalfTime());
        return result;

    }

    public TrainingPredictionData buildGeneral(String homeTeamName, String awayTeamName, String teamName,DBFixtureGroup fixtures) {

        // normal builder
        DBFixtureGroup mainFixtures = fixtures.getLast(150);
        int fixtureSize = mainFixtures.size();
        TrainingSet trainingSet = new TrainingSet(fixtureSize,numberOfColumns);
        for(int fixtureIndex = 0; fixtureIndex < fixtureSize; fixtureIndex++){
            DBFixture currentFixture = mainFixtures.get(fixtureIndex);
            String hTeamName = currentFixture.getHomeTeamName();
            String aTeamName = currentFixture.getAwayTeamName();

            DBFixtureGroup homeTeamFixtures = fixtures.getFixturesBefore(hTeamName,aTeamName,TeamStatus.HOME,10);
            DBFixtureGroup awayTeamFixtures = fixtures.getFixturesBefore(hTeamName,aTeamName,TeamStatus.AWAY,10);

            TrainingRow trainingRow = new TrainingRow(numberOfColumns);
            trainingRow.set(0,DBFixtureGroupCalculator.getTeamPerformance(hTeamName,homeTeamFixtures));
            trainingRow.set(1,DBFixtureGroupCalculator.getTeamPerformance(aTeamName,awayTeamFixtures));
            trainingRow.set(2,DBFixtureGroupCalculator.getGoalsScoredFullTime(hTeamName,homeTeamFixtures));
            trainingRow.set(3,DBFixtureGroupCalculator.getGoalsScoredFullTime(aTeamName,awayTeamFixtures));
            trainingRow.set(4,DBFixtureGroupCalculator.getGoalsConcededFullTime(hTeamName,homeTeamFixtures));
            trainingRow.set(5,DBFixtureGroupCalculator.getGoalsConcededFullTime(aTeamName,awayTeamFixtures));
            trainingRow.set(6,resultProcessor.getValue(convert(currentFixture.getResult())));
            trainingSet.set(fixtureIndex,trainingRow);

        }

        // prediction set
        TrainingRow predictionSet = new TrainingRow(numberOfColumns - 1);

//        String opponentName = getOpponentName(homeTeamName,awayTeamName,teamName);
        DBFixtureGroup homeFixtures = fixtures.get(homeTeamName).getLast(20);
        DBFixtureGroup awayFixtures = fixtures.get(awayTeamName).getLast(20);
        double teamNamePerformance = DBFixtureGroupCalculator.getTeamPerformance(homeTeamName,homeFixtures); // currentFixtures.getTeamPerformance(teamName);
        double opponentPerformance = DBFixtureGroupCalculator.getTeamPerformance(awayTeamName,awayFixtures);
        // predictionSet.set(0,getStatus(homeTeamName,awayTeamName,teamName));
        predictionSet.set(0,teamNamePerformance);
        predictionSet.set(1,opponentPerformance);
        predictionSet.set(2,DBFixtureGroupCalculator.getGoalsScoredFullTime(homeTeamName,homeFixtures));
        predictionSet.set(3,DBFixtureGroupCalculator.getGoalsScoredFullTime(awayTeamName,awayFixtures));
        predictionSet.set(4,DBFixtureGroupCalculator.getGoalsConcededFullTime(homeTeamName,homeFixtures));
        predictionSet.set(5,DBFixtureGroupCalculator.getGoalsConcededFullTime(awayTeamName,awayFixtures));

        TrainingPredictionData trainingPredictionData = new TrainingPredictionData(trainingSet,predictionSet);

        return trainingPredictionData;

    }

    public String getOpponentName(String homeTeamName, String awayTeamName, String teamName){

        if(teamName.equals(homeTeamName)){
            return awayTeamName;
        }
        else if(teamName.equals(awayTeamName)){
            return homeTeamName;
        }
        throw new IllegalArgumentException();

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

    public double getStatus(String homeTeamName, String awayTeamName, String mainTeam){

        if(mainTeam.equals(homeTeamName)){
            // return TeamStatus.HOME;
            return 1;
        }
        else if(mainTeam.equals(awayTeamName)){
            // return TeamStatus.AWAY;
            return 5;
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
