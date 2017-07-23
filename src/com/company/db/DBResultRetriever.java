package com.company.db;

import com.company.db.model.DBFixture;
import com.company.db.model.DBFixtureGroup;
import com.company.db.model.DBResult;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBResultRetriever {

    private String collectionName;

    public DBResultRetriever(String collectionName){

        this.collectionName = collectionName;

    }

    public void getResults(){



    }

    public DBFixtureGroup getResults(int leagueId){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        // DBCollection collection = db.getCollection("league_collection");
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        DBFixtureGroup dbFixtures = new DBFixtureGroup();
        while (cursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
            for(int index = 0; index < basicDBList.size(); index++){
                BasicDBObject fixtureObject = (BasicDBObject) basicDBList.get(index);

                if(fixtureObject.get("status").toString().equals("FINISHED")){
                    BasicDBObject resultObject = (BasicDBObject) fixtureObject.get("result");

                    DBResult dbResult = new DBResult();
                    dbResult.setHomeTeamScoreFullTime(resultObject.getInt("goalsHomeTeam"));
                    dbResult.setAwayTeamScoreFullTime(resultObject.getInt("goalsAwayTeam"));
                    //dbResult.setHomeTeamScoreHalfTime();
                    //dbResult.setAwayTeamScoreHalfTime();

                    DBFixture dbFixture = new DBFixture();
                    dbFixture.setAwayTeamName(fixtureObject.get("awayTeamName").toString());
                    dbFixture.setHomeTeamName(fixtureObject.get("homeTeamName").toString());
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setStatus(fixtureObject.get("status").toString());
                    // dbFixture.setDate(new Date(fixtureObject.get("date").toString()));
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setResult(dbResult);
                    dbFixtures.add(dbFixture);
                }
            }
        }
        return dbFixtures;

    }

    public DBFixtureGroup getResults(int leagueId, String teamName){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        DBFixtureGroup dbFixtures = new DBFixtureGroup();
        while (cursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
            for(int index = 0; index < basicDBList.size(); index++){
                BasicDBObject fixtureObject = (BasicDBObject) basicDBList.get(index);

                if(fixtureObject.get("status").toString().equals("FINISHED") && (fixtureObject.get("homeTeamName").equals(teamName) || fixtureObject.get("awayTeamName").equals(teamName))){
                    BasicDBObject resultObject = (BasicDBObject) fixtureObject.get("result");

                    DBResult dbResult = new DBResult();
                    dbResult.setHomeTeamScoreFullTime(resultObject.getInt("goalsHomeTeam"));
                    dbResult.setAwayTeamScoreFullTime(resultObject.getInt("goalsAwayTeam"));
                    //dbResult.setHomeTeamScoreHalfTime();
                    //dbResult.setAwayTeamScoreHalfTime();

                    DBFixture dbFixture = new DBFixture();
                    dbFixture.setAwayTeamName(fixtureObject.get("awayTeamName").toString());
                    dbFixture.setHomeTeamName(fixtureObject.get("homeTeamName").toString());
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setStatus(fixtureObject.get("status").toString());
                    dbFixture.setDate((Date)fixtureObject.get("date"));
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setResult(dbResult);
                    dbFixtures.add(dbFixture);
                }
            }
        }
        return dbFixtures;

    }

}
