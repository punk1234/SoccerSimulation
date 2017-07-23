package com.company.api.retriever;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */
public class FootballDataRetriever {

    //private int[] leagueIds = {426,430,433,436,438,439};

    //private static final String url = "http://api.football-data.org/v1/soccerseasons";

    public static Object retrieveData(String url,JsonFormat jsonFormat) throws Exception{

        URL urlObject = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer output = new StringBuffer();

            while((inputLine = bufferedReader.readLine()) != null){
                output.append(inputLine);
            }

            bufferedReader.close();

            if(jsonFormat == JsonFormat.OBJECT){
                return convertToJsonObject(output.toString());
            }
            else{
                return convertToJsonArray(output.toString());
            }
        }
        else{
            // something was wrong
            return null;
        }

    }

//    private static void convertToJSON(String input){
//
//        try {
//            JSONArray jo = new JSONArray(input);
//            System.out.println("JSON = " + jo);
//            //System.out.println(jo);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

    private static BasicDBObject convertToJsonObject(String input){

        BasicDBObject jsonObject = null;
        //try {
            //jsonObject = new JSONObject(input);
            jsonObject = (BasicDBObject) JSON.parse(input);
            System.out.println("JSON = " + input);
            Printer.print(jsonObject);
            return jsonObject;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //return jsonObject;

    }

    private static JSONArray convertToJsonArray(String input){

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(input);
            System.out.println("JSON = " + input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;

    }

}
