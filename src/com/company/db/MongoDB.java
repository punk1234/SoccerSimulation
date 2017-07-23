package com.company.db;

import com.mongodb.*;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AKEJU  FATAI on 2017-05-15.
 */
public class MongoDB {

    public static void connect(){

        // To connect to the mongodb server
        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection("col1",null);
        System.out.println("Collection created successfully");

        System.out.println(db.getCollection("col1"));

        BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("url", "http://www.tutorialspoint.com/mongodb/").
                append("by", "tutorials point").
                append("prediction", "over 2.5");

        collection.insert(doc);
        System.out.println("Document inserted successfully");

        System.out.println(db.getCollection("col1"));

    }

    public void clearCollection(String collectionName){

        // To connect to the mongodb server
        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection(collectionName,null);
        System.out.println("Collection created successfully");

        BasicDBObject emptyQuery = new BasicDBObject();
        collection.remove(emptyQuery);

    }

    public static void save(JSONObject jsonObject){

        // To connect to the mongodb server
        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection("league_ht_collection",null);
        System.out.println("Collection created successfully");

        System.out.println(db.getCollection("league_ht_collection"));

        BasicDBObject doc = null;
        try {
            doc = new BasicDBObject("caption", jsonObject.get("caption")).
                    append("id", jsonObject.get("id")).
                    append("league", jsonObject.get("league")).
                    append("year", jsonObject.get("year")).
                    append("currentMatchday", jsonObject.get("currentMatchday")).
                    append("numberOfTeams", jsonObject.get("numberOfTeams")).
                    append("lastUpdated",jsonObject.get("lastUpdated"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        collection.insert(doc);
        System.out.println("Document inserted successfully");

        System.out.println(db.getCollection("col1"));

    }

    public static void save(BasicDBObject basicDBObject){

        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection("league_ht_collection",null);
        System.out.println("Collection created successfully");

        //System.out.println(db.getCollection("league_collection"));

        collection.insert(basicDBObject);
        System.out.println("Document inserted successfully");

    }

    public static void update(BasicDBObject basicDBObject){

        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection(DBConfig.TEST_COLLECTION_NAME,null);
        System.out.println("Collection created successfully");

        //System.out.println(db.getCollection("league_collection"));

        BasicDBObject query = new BasicDBObject();
        query.put("_id",basicDBObject.get("_id"));
        collection.update(query,basicDBObject);
        System.out.println("Document inserted successfully");

    }

    public static BasicDBList getAll(String collectionName){

        MongoClient mongoClient = new MongoClient("localhost",27017);

        // Now connect to the database
        DB db = mongoClient.getDB("soccer");
        System.out.println("Connect to database successfully");

        DBCollection collection = db.createCollection(collectionName,null);
        System.out.println("Collection created successfully");

        BasicDBList basicDBList = new BasicDBList();
        DBCursor dbCursor = collection.find();
        while(dbCursor.hasNext()){
            BasicDBObject basicDBObject = (BasicDBObject) dbCursor.next();
            basicDBList.add(basicDBObject);
        }

        return basicDBList;

    }

}
