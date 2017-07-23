package com.company.api.retriever;

import com.company.db.MongoDB;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */
public class DatabaseUpdater {

    private static int[] leagueIds = {426,427,428,430,431,433,434,436,437,438,439,441};
    //private static int[] leagueIds = {426};
    private static final String baseUrl = "http://api.football-data.org/v1/";

    public static void update() throws Exception {

        for(int leagueId : leagueIds){
            String leagueUrl = baseUrl + "soccerseasons/" + leagueId;
            String leagueTeamsUrl = baseUrl + "soccerseasons/" + leagueId + "/teams";
            String leagueFixturesUrl = baseUrl + "soccerseasons/" + leagueId + "/fixtures";
            //String leaguePlayersUrl = baseUrl + "teams/" + leagueId + "/players";

            BasicDBObject leagueJsonObject = (BasicDBObject) FootballDataRetriever.retrieveData(leagueUrl,JsonFormat.OBJECT);
            BasicDBObject leagueTeamsJsonObject = (BasicDBObject) FootballDataRetriever.retrieveData(leagueTeamsUrl,JsonFormat.OBJECT);
            BasicDBObject leagueFixturesJsonObject = (BasicDBObject) FootballDataRetriever.retrieveData(leagueFixturesUrl,JsonFormat.OBJECT);

//            Printer.print(leagueJsonObject);
//            Printer.print(leagueTeamsJsonObject);
//            Printer.print(leagueFixturesJsonObject);

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            BasicDBObject basicDBObject = new BasicDBObject();
            updateLeague(basicDBObject,leagueJsonObject);
            updateLeagueTeams(basicDBObject,leagueTeamsJsonObject);
            updateLeagueFixtures(basicDBObject,leagueFixturesJsonObject);
            Printer.print(basicDBObject);

            //MongoDB.save(leagueJsonObject);
            MongoDB.save(basicDBObject);
        }

    }

    private static void updateLeague(BasicDBObject basicDBObject, BasicDBObject jsonObject){

        //try {
            basicDBObject
                    .append("caption", jsonObject.get("caption"))
                    .append("id", jsonObject.get("id")).
                    append("league", jsonObject.get("league")).
                    append("year", jsonObject.get("year")).
                    append("currentMatchday", jsonObject.get("currentMatchday")).
                    append("numberOfTeams", jsonObject.get("numberOfTeams")).
                    append("lastUpdated",jsonObject.get("lastUpdated"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    private static void updateLeagueTeams(BasicDBObject basicDBObject, BasicDBObject jsonObject){

        List<BasicDBObject> teams = new ArrayList<>();
        //try {
            BasicDBList basicDBList = (BasicDBList) jsonObject.get("teams");
            int teamCount = (int) jsonObject.get("count"); //jsonArray.length();
            basicDBObject.append("teamCount",teamCount);
            for(int index = 0; index < teamCount; index++){
                BasicDBObject teamObject = (BasicDBObject) basicDBList.get(index);// .getJSONObject(index);
                BasicDBObject team = new BasicDBObject();

                team
                        .append("name",teamObject.get("name"))
                        .append("code",teamObject.get("code"))
                        .append("shortName",teamObject.get("shortName"));

                teams.add(team);
            }

            basicDBObject.append("teams", teams);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    private static void updateLeagueFixtures(BasicDBObject basicDBObject, BasicDBObject jsonObject){

        List<BasicDBObject> fixtures = new ArrayList<>();
        //try {
            BasicDBList jsonArray = (BasicDBList) jsonObject.get("fixtures");
            int fixtureCount = (int) jsonObject.get("count");
            basicDBObject.append("fixtureCount",fixtureCount);
            for(int index = 0; index < fixtureCount; index++){
                BasicDBObject fixtureObject = (BasicDBObject)jsonArray.get(index); //.getJSONObject(index);
                BasicDBObject fixture = new BasicDBObject();

                BasicDBObject resultObject = new BasicDBObject();
                BasicDBObject jsonResult = (BasicDBObject) fixtureObject.get("result"); //.getJSONObject("result");
                resultObject
                        .append("goalsHomeTeam",jsonResult.get("goalsHomeTeam"))
                        .append("goalsAwayTeam",jsonResult.get("goalsAwayTeam"));

//                if(fixtureObject.get("status").equals("FINISHED")){
//                    BasicDBObject halfTimeResult = new BasicDBObject();
//                    BasicDBObject jsonHalfTime = (BasicDBObject)jsonResult.get("halfTime"); //.getJSONObject("halfTime");
//                    halfTimeResult
//                            .append("goalsHomeTeam",jsonHalfTime.get("goalsHomeTeam"))
//                            .append("goalsAwayTeam",jsonHalfTime.get("goalsAwayTeam"));
//
//                    resultObject.append("halfTime",halfTimeResult);
//                }

                fixture
                        .append("date",fixtureObject.get("date"))
                        .append("status",fixtureObject.get("status"))
                        .append("matchday",fixtureObject.get("matchday"))
                        .append("homeTeamName",fixtureObject.get("homeTeamName"))
                        .append("awayTeamName",fixtureObject.get("awayTeamName"))
                        .append("result",resultObject);

                fixtures.add(fixture);
            }

            basicDBObject.append("fixtures", fixtures);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

}
