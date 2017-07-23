package com.company.db;

import com.company.db.model.DBFixture;
import com.company.db.model.DBFixtureGroup;
import com.company.db.model.DBResult;
import com.mongodb.*;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBFixtureRetriever {

    private String collection_name;

    public DBFixtureRetriever(String collection_name){

        this.collection_name = collection_name;

    }

    public void getFixtures(){

//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        DB db = mongoClient.getDB("soccer");
//        DBCollection collection = db.getCollection("league_collection");
//        DBCursor cursor = collection.find();
//        List<DBFixture> dbFixtures = new ArrayList<>();
//        while (cursor.hasNext()){
//            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
//            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
//            DBFixture dbFixture = new DBFixture();
//            dbFixture.setAwayTeamName();
//        }

    }

    public DBFixtureGroup getFixtures(int leagueId){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        DBCollection collection = db.getCollection(collection_name);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        DBFixtureGroup dbFixtures = new DBFixtureGroup();
        while (cursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
            for(int index = 0; index < basicDBList.size(); index++){
                BasicDBObject fixtureObject = (BasicDBObject) basicDBList.get(index);

                if(fixtureObject.get("status").toString().equals("TIMED")){
                    DBFixture dbFixture = new DBFixture();
                    dbFixture.setAwayTeamName(fixtureObject.get("awayTeamName").toString());
                    dbFixture.setHomeTeamName(fixtureObject.get("homeTeamName").toString());
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setStatus(fixtureObject.get("status").toString());
                    // dbFixture.setDate((Date)fixtureObject.get("date"));
                    dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                    dbFixture.setResult(null);
                    dbFixtures.add(dbFixture);
                }
            }
        }
        return dbFixtures;

    }

    public DBFixtureGroup getAllFixtures(int leagueId){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        DBCollection collection = db.getCollection(collection_name);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        DBFixtureGroup dbFixtures = new DBFixtureGroup();
        while (cursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
            for(int index = 0; index < basicDBList.size(); index++){
                BasicDBObject fixtureObject = (BasicDBObject) basicDBList.get(index);

                BasicDBObject resultObject = (BasicDBObject) fixtureObject.get("result");

                DBFixture dbFixture = new DBFixture();
                dbFixture.setAwayTeamName(fixtureObject.get("awayTeamName").toString());
                dbFixture.setHomeTeamName(fixtureObject.get("homeTeamName").toString());
                dbFixture.setMatchday((int)fixtureObject.get("matchday"));
                dbFixture.setStatus(fixtureObject.get("status").toString());
                // dbFixture.setDate((Date)fixtureObject.get("date"));
                dbFixture.setMatchday((int)fixtureObject.get("matchday"));

                DBResult dbResult = new DBResult();
                dbResult.setHomeTeamScoreFullTime((Integer)(resultObject.get("goalsHomeTeam")));
                dbResult.setAwayTeamScoreFullTime((Integer)(resultObject.get("goalsAwayTeam")));
                System.out.println("League_id = " + leagueId);
                System.out.println("matchday = " + (int)fixtureObject.get("matchday"));
                System.out.println(fixtureObject.get("homeTeamName").toString() + " vs " + fixtureObject.get("awayTeamName").toString());
                //dbResult.setHomeTeamScoreHalfTime();
                //dbResult.setAwayTeamScoreHalfTime();
                dbFixture.setResult(dbResult);

                dbFixtures.add(dbFixture);
            }
        }
        return dbFixtures;

    }

    public BasicDBObject getLeague(int leagueId){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        DBCollection collection = db.getCollection(collection_name);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        BasicDBObject leagueObject = null;
        if(cursor.hasNext()){
            leagueObject = (BasicDBObject) cursor.next();
        }
        return leagueObject;

    }

    public BasicDBList getFixturesWithPrediction(int leagueId){

        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("soccer");
        DBCollection collection = db.getCollection(collection_name);
        BasicDBObject whereClauseObject = new BasicDBObject("id",leagueId);
        DBCursor cursor = collection.find(whereClauseObject);
        BasicDBList dbFixtures = new BasicDBList();
        while (cursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) cursor.next();
            BasicDBList basicDBList = (BasicDBList) basicDBObject.get("fixtures");
            for(int index = 0; index < basicDBList.size(); index++){
                BasicDBObject fixtureObject = (BasicDBObject) basicDBList.get(index);
                if(fixtureObject.containsKey("prediction")){
                    dbFixtures.add(fixtureObject);
                }
            }
        }
        return dbFixtures;

    }

}
