package com.company.api.retriever;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.json.JSONObject;

/**
 * Created by AKEJU  FATAI on 2017-05-24.
 */
public class Printer {

    public static void print(BasicDBObject basicDBObject){

        System.out.println("JSONObject" + basicDBObject.toString());

    }

}
