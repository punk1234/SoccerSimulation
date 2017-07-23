package com.company.api.retriever;

import com.company.db.MongoDB;
import com.mongodb.BasicDBObject;

/**
 * Created by AKEJU  FATAI on 2017-05-24.
 */
public class DBTester {

    public static void test(){

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject
                .append("first_name","fatai")
                .append("last_name","akeju")
                .append("sex","male")
                .append("enemy","null");

        MongoDB.save(basicDBObject);

    }

}
