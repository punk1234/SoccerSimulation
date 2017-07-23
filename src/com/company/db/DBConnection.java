package com.company.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Created by AKEJU  FATAI on 2017-06-17.
 */
public class DBConnection {

//    String host = DBConfig.DATABASE_HOSTNAME;
//    int port = DBConfig.DATABASE_PORTNUMBER;
//    String databaseName = DBConfig.DATABASE_NAME;

    public MongoClient connect(String host, int port, String databaseName){

        MongoClient mongoClient = new MongoClient(host,port);
        return mongoClient;

    }

    public void disconnect(MongoClient mongoClient){

        mongoClient.close();

    }

}
