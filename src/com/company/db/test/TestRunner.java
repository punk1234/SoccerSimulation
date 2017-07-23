package com.company.db.test;

import java.util.*;

import com.company.MatchPredictor;
import com.company.PredictionCollection;
import com.company.TechniquePrediction;
import com.company.db.DBConfig;
import com.company.db.DBFixtureRetriever;
import com.company.db.MongoDB;
import com.company.db.model.DBFixture;
import com.company.db.model.DBFixtureGroup;
import com.company.match.resultcategory.ResultCategoryCollection;
import com.company.match.resultcategory.ResultCategoryCollectionBuilder;
import com.company.ml.MachineLearningTechniqueCollection;
import com.company.ml.MachineLearningTechniqueCollectionBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Created by AKEJU  FATAI on 2017-06-17.
 */
public class TestRunner {

    public static void run(){

        DBFixtureRetriever fixtureRetriever = new DBFixtureRetriever(DBConfig.TEST_COLLECTION_NAME);
        for(int leagueId : DBConfig.LEAGUE_IDS){
            BasicDBObject leagueObject = fixtureRetriever.getLeague(leagueId);
            DBFixtureGroup timedFixtures = fixtureRetriever.getFixtures(leagueId);
            for(int index = 0; index < timedFixtures.size(); index++){
                DBFixture currentFixture = timedFixtures.get(index);

                MachineLearningTechniqueCollection machineLearningTechniqueCollection = MachineLearningTechniqueCollectionBuilder.build();
                ResultCategoryCollection resultCategoryCollection = ResultCategoryCollectionBuilder.build();

                MatchPredictor matchPredictor = new MatchPredictor(leagueId,currentFixture.getHomeTeamName(),currentFixture.getAwayTeamName(),
                        machineLearningTechniqueCollection,resultCategoryCollection);
                PredictionCollection predictionCollection = matchPredictor.run();

                // save fixture with prediction info.
                BasicDBList fixtures =  (BasicDBList) leagueObject.get("fixtures");
                BasicDBObject fixtureObject = getFixture(fixtures,currentFixture.getHomeTeamName(),currentFixture.getAwayTeamName());
                //fixtureObject.put("prediction",predictionCollection);
                fixtureObject.put("prediction",convert(predictionCollection));
                // fixtureObject.replace("fixtures",fixtures);

                MongoDB.update(leagueObject);
            }
            // System.out.println("Timed Fixtures Count " + timedFixtures.size());
        }

    }

    private static BasicDBObject convert(PredictionCollection predictionCollection){

        BasicDBObject predictionCollectionObject = new BasicDBObject();
        List<String> categories = predictionCollection.getCategories();
        for(String category : categories){
            // build the corresponding TechniquePrediction object
            BasicDBObject techniquePredictionObject = new BasicDBObject();
            TechniquePrediction techniquePrediction = predictionCollection.get(category);
            List<String> techniques = techniquePrediction.getTechniques();
            for(String technique : techniques){
                // setup the technique with its corresponding result
                String result = techniquePrediction.get(technique);
                techniquePredictionObject.put(technique,result);
            }
            predictionCollectionObject.put(category,techniquePredictionObject);
        }

        return predictionCollectionObject;

    }

    private static BasicDBObject getFixture(BasicDBList fixtures, String homeTeamName, String awayTeamName){

        for(Object object : fixtures){
            BasicDBObject fixture = (BasicDBObject)object;
            String currentHomeTeamName = fixture.get("homeTeamName").toString();
            String currentAwayTeamName = fixture.get("awayTeamName").toString();

            if(currentHomeTeamName.equals(homeTeamName) && currentAwayTeamName.equals(awayTeamName)){
                return fixture;
            }
        }
        throw new IllegalArgumentException();

    }

}
