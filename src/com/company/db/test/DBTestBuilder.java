package com.company.db.test;

import com.company.db.MongoDB;
import com.mongodb.*;

/**
 * Created by AKEJU  FATAI on 2017-06-12.
 */
public class DBTestBuilder {

    public static final String TEST_TABLE_NAME = "league_test_collection";
    public static final String MAIN_TABLE_NAME = "league_ht_collection";
    // public static final String TEST_TABLE_NAME = "league_test_collection";

    private static double percent = 0.8;

    public static void build(){

        BasicDBList basicDBList = MongoDB.getAll(MAIN_TABLE_NAME);
        for(Object object : basicDBList){
            BasicDBObject basicDBObject = (BasicDBObject) object;
            BasicDBList fixtureList = (BasicDBList) basicDBObject.get("fixtures");
            BasicDBList percentFixtures = getUpdatedFixtures(fixtureList);
            basicDBObject.replace("fixtures",percentFixtures);
            save(basicDBObject);
        }

    }

    private static BasicDBList getUpdatedFixtures(BasicDBList basicDBList){

        BasicDBList newFixtures = new BasicDBList();
        int midIndex = (int)(percent * basicDBList.size());
        int index;
        for(index = 0; index < midIndex; index++){
            newFixtures.add(basicDBList.get(index));
        }
        for(index = midIndex; index < basicDBList.size(); index++ ){
            BasicDBObject fixture = (BasicDBObject) basicDBList.get(index);
            fixture.replace("status","TIMED");
            BasicDBObject resultObject = (BasicDBObject) fixture.get("result");
            resultObject.replace("goalsHomeTeam",null);
            resultObject.replace("goalsAwayTeam",null);
            newFixtures.add(fixture);
        }
        return newFixtures;

    }

    private static BasicDBList getPercentFixtures(BasicDBList basicDBList){

        BasicDBList percentFixtures = new BasicDBList();
        int endIndex = (int)(percent * basicDBList.size());
        for(int index = 0; index < endIndex; index++){
            percentFixtures.add(basicDBList.get(index));
        }
        return percentFixtures;

    }

    private static void save(BasicDBObject basicDBObject){

        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection(TEST_TABLE_NAME,null);
        System.out.println("Collection created successfully");

        //System.out.println(db.getCollection("league_collection"));

        collection.insert(basicDBObject);
        System.out.println("Document inserted successfully");

    }

}
