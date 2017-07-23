package com.company.predictionanalyzer;

import java.util.*;

import com.company.ResultConverter;
import com.company.match.Result;
import com.company.db.DBConfig;
import com.company.db.DBFixtureRetriever;
import com.company.db.model.DBFixture;
import com.company.db.model.DBFixtureGroup;
import com.company.match.resultcategory.ResultCategoryCollection;
import com.company.match.resultprocessor.ResultProcessor;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Created by AKEJU  FATAI on 2017-06-22.
 */
public class PredictionAnalyzer {

    private ResultCategoryCollection resultCategoryCollection;

    public PredictionAnalyzer(ResultCategoryCollection resultCategoryCollection){

        this.resultCategoryCollection = resultCategoryCollection;

    }

    public PredictionAnalysis start(){

        /* pseudocode goes here */
        // 1. loop through each of the leagues (league ids)
        // 2. get all the fixtures with predictions from the league_test_collection
        // 3. get all the fixtures from the league_collection
        // 4. for each of the fixtures gotten from step 2 above, get corresponding fixture in from the fixtures gotten in step 3
        // 5. process the fixtures without prediction using the ResultProcessor class to get the match outcome in string
        // 6. build/generate the analysis report
        // 7. print the analysis report using a PredictionAnalysisPrinter class

        PredictionAnalysis predictionAnalysis = new PredictionAnalysis();

        DBFixtureRetriever dbTestFixtureRetriever = new DBFixtureRetriever(DBConfig.TEST_COLLECTION_NAME);
        DBFixtureRetriever dbMainFixtureRetriever = new DBFixtureRetriever(DBConfig.LEAGUE_COLLECTION_NAME);
        for(int leagueId : DBConfig.LEAGUE_IDS){
            BasicDBList predictedFixtures = dbTestFixtureRetriever.getFixturesWithPrediction(leagueId);
            DBFixtureGroup leagueFixtures = dbMainFixtureRetriever.getAllFixtures(leagueId);
            for(Object pFixture : predictedFixtures){
                BasicDBObject pFixtureObject = (BasicDBObject) pFixture;

                String homeTeamName = pFixtureObject.get("homeTeamName").toString();
                String awayTeamName = pFixtureObject.get("awayTeamName").toString();
                DBFixture mainCorrespondingFixture = leagueFixtures.getFixture(homeTeamName,awayTeamName);

                BasicDBObject predictionObject = ((BasicDBObject) pFixtureObject.get("prediction"));
                Set<String> categoryKeySet = predictionObject.keySet();
                List<String> categoryKeyList = new ArrayList<>(categoryKeySet);
                for(String category : categoryKeyList){
                    BasicDBObject mlTechniqueObject =  (BasicDBObject) predictionObject.get(category);
                    Set<String> mlTechniqueObjectKeySet = mlTechniqueObject.keySet();
                    List<String> mlTechniqueObjectKeyList = new ArrayList<>(mlTechniqueObjectKeySet);
                    for(String mlTechnique : mlTechniqueObjectKeyList ){
                        String predictionValue = mlTechniqueObject.get(mlTechnique).toString();
                        ResultProcessor resultProcessor = resultCategoryCollection.get(category);
                        System.out.println("************ " + leagueId);
                        System.out.println("= " + homeTeamName );
                        System.out.println("= " + awayTeamName);
                        if(mainCorrespondingFixture.getResult().getHomeTeamScoreFullTime() != null &&
                                mainCorrespondingFixture.getResult().getAwayTeamScoreFullTime() != null){
                            Result result = ResultConverter.convert(mainCorrespondingFixture.getResult());
                            String resultValue = resultProcessor.process(result);
                            updatePredictionAnalysis(predictionAnalysis,mlTechnique,category,predictionValue,resultValue);
                        }
                    }
                }
            }
        }

        return predictionAnalysis;

    }

    private void updatePredictionAnalysis(PredictionAnalysis predictionAnalysis, String mlTechnique, String category, String predictionValue,
                                          String resultValue){

        boolean value = false;
        if(predictionValue.equals(resultValue)){
            value = true;
        }
        predictionAnalysis.update(mlTechnique,category,value);

    }

}
