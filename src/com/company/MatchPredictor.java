package com.company;

import com.company.db.DBResultRetriever;
import com.company.db.model.DBFixtureGroup;
import com.company.match.resultcategory.ResultCategory;
import com.company.match.resultcategory.ResultCategoryCollection;
import com.company.match.resultprocessor.ResultProcessor;
import com.company.match.trainingpredictiondatabuilder.ClassificationTrainingSetConverter;
import com.company.match.trainingpredictiondatabuilder.TrainingPredictionData;
import com.company.match.trainingpredictiondatabuilder.TrainingPredictionDataBuilder;
import com.company.ml.MachineLearningTechnique;
import com.company.ml.MachineLearningTechniqueCollection;
import com.company.ml.model.TrainingRow;
import com.company.ml.model.TrainingSet;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */


/**
 * This class executes the machine learning algorithms and the specified prediction categories on a particular match
 * or fixture using the league_id home team_name and away team_name the prediction of the match, and returns a collection
 * of the predictions
 */
public class MatchPredictor {

    private int leagueId;
    private String homeTeamName;
    private String awayTeamName;
    private MachineLearningTechniqueCollection machineLearningTechniqueCollection;
    private ResultCategoryCollection resultCategoryCollection;

    public MatchPredictor(int leagueId, String homeTeamName, String awayTeamName,
                          MachineLearningTechniqueCollection machineLearningTechniqueCollection,
                          ResultCategoryCollection resultCategoryCollection){

        this.leagueId = leagueId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.machineLearningTechniqueCollection = machineLearningTechniqueCollection;
        this.resultCategoryCollection = resultCategoryCollection;

    }

    /**
     * This method performs the prediction for the match specified and builds the training set using the last 20 games for each teams
     * returns a prediction collection
     */
    public PredictionCollection run(){

        DBResultRetriever dbResultRetriever = new DBResultRetriever("league_ht_collection");
        DBFixtureGroup fixtures = dbResultRetriever.getResults(leagueId);
        PredictionCollection predictionCollection = new PredictionCollection();
        for(int categoryIndex = 0; categoryIndex < resultCategoryCollection.count(); categoryIndex++){
            ResultCategory resultCategory = resultCategoryCollection.get(categoryIndex);
            TechniquePrediction techniquePrediction = new TechniquePrediction();
            for(int mlTechniqueIndex = 0; mlTechniqueIndex < machineLearningTechniqueCollection.count(); mlTechniqueIndex++){
                MachineLearningTechnique machineLearningTechnique = machineLearningTechniqueCollection.get(mlTechniqueIndex);
                ResultProcessor resultProcessor = resultCategory.getResultProcessor();
                TrainingPredictionDataBuilder trainingPredictionDataBuilder = new TrainingPredictionDataBuilder(8,resultProcessor);
                TrainingPredictionData homeTeamTrainingPredictionData = trainingPredictionDataBuilder.build(homeTeamName,awayTeamName,homeTeamName,fixtures);
                TrainingPredictionData awayTeamTrainingPredictionData = trainingPredictionDataBuilder.build(homeTeamName,awayTeamName,awayTeamName,fixtures);

                TrainingSet homeTrainingSet = homeTeamTrainingPredictionData.getTrainingSet();
                TrainingRow homePredictionSet = homeTeamTrainingPredictionData.getPredictionSet();
                TrainingSet awayTrainingSet = awayTeamTrainingPredictionData.getTrainingSet();
                TrainingRow awayPredictionSet = awayTeamTrainingPredictionData.getPredictionSet();

//                ClassificationTrainingSetConverter.convert(homeTrainingSet);
//                ClassificationTrainingSetConverter.convert(awayTrainingSet);
//
//                ClassificationTrainingSetConverter.convert(homePredictionSet);
//                ClassificationTrainingSetConverter.convert(awayTrainingSet);

                machineLearningTechnique.learn(homeTrainingSet);
                Double homeResult = machineLearningTechnique.predict(homePredictionSet);
                machineLearningTechnique.learn(awayTrainingSet);
                Double awayResult = machineLearningTechnique.predict(awayPredictionSet);
                String result = resultProcessor.get(homeResult);
                techniquePrediction.add(machineLearningTechnique.getName(),result);
            }
            predictionCollection.add(resultCategory.getName(),techniquePrediction);
        }
        return predictionCollection;

    }

    /**
     * This method performs the prediction for the match specified and builds the training set using the last 100 games for each teams
     * returns a prediction collection
     */
    public PredictionCollection runGeneral(){

        DBResultRetriever dbResultRetriever = new DBResultRetriever("league_ht_collection");
        DBFixtureGroup fixtures = dbResultRetriever.getResults(leagueId);
        PredictionCollection predictionCollection = new PredictionCollection();
        for(int categoryIndex = 0; categoryIndex < resultCategoryCollection.count(); categoryIndex++){
            ResultCategory resultCategory = resultCategoryCollection.get(categoryIndex);
            TechniquePrediction techniquePrediction = new TechniquePrediction();
            for(int mlTechniqueIndex = 0; mlTechniqueIndex < machineLearningTechniqueCollection.count(); mlTechniqueIndex++){
                MachineLearningTechnique machineLearningTechnique = machineLearningTechniqueCollection.get(mlTechniqueIndex);
                ResultProcessor resultProcessor = resultCategory.getResultProcessor();
                TrainingPredictionDataBuilder trainingPredictionDataBuilder = new TrainingPredictionDataBuilder(7,resultProcessor);
                TrainingPredictionData homeTeamTrainingPredictionData = trainingPredictionDataBuilder.buildGeneral(homeTeamName,awayTeamName,homeTeamName,fixtures);
                TrainingPredictionData awayTeamTrainingPredictionData = trainingPredictionDataBuilder.buildGeneral(homeTeamName,awayTeamName,awayTeamName,fixtures);

                TrainingSet homeTrainingSet = homeTeamTrainingPredictionData.getTrainingSet();
                TrainingRow homePredictionSet = homeTeamTrainingPredictionData.getPredictionSet();
                TrainingSet awayTrainingSet = awayTeamTrainingPredictionData.getTrainingSet();
                TrainingRow awayPredictionSet = awayTeamTrainingPredictionData.getPredictionSet();

//                ClassificationTrainingSetConverter.convertGeneral(homeTrainingSet);
//                ClassificationTrainingSetConverter.convertGeneral(awayTrainingSet);
//
//                ClassificationTrainingSetConverter.convertGeneral(homePredictionSet);
//                ClassificationTrainingSetConverter.convertGeneral(awayTrainingSet);

                machineLearningTechnique.learn(homeTrainingSet);
                Double homeResult = machineLearningTechnique.predict(homePredictionSet);
                machineLearningTechnique.learn(awayTrainingSet);
                Double awayResult = machineLearningTechnique.predict(awayPredictionSet);
                String result = resultProcessor.get(homeResult);
                techniquePrediction.add(machineLearningTechnique.getName(),result);
            }
            predictionCollection.add(resultCategory.getName(),techniquePrediction);
        }
        return predictionCollection;

    }

}
