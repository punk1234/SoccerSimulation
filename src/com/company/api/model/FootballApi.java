package com.company.api.model;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AKEJU  FATAI on 2017-04-29.
 */
public class FootballApi {

    private int[] leagueIds = {};

    private static final String url = "http://api.football-data.org/v1/soccerseasons";

    public static void retrieveData() throws Exception{

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

            //System.out.println("RESPONSE CODE " + responseCode);
            System.out.println("OUTPUT = " + output.toString());
            convertToJSON(output.toString());
        }
        else{
            // something was wrong
        }

    }

    private static void convertToJSON(String input){

        try {
            JSONArray jo = new JSONArray(input);
            System.out.println("JSON = " + jo);
            //System.out.println(jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
