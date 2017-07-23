package com.company.ml;

import com.company.ml.ann.ANN;
import com.company.ml.ann.Architecture;
import com.company.ml.decisiontree.DecisionTree;
import com.company.ml.decisiontree.DecisionTreeBuilder;
import com.company.ml.knn.KNN;
import com.company.ml.linearmodel.LinearModel;
import com.company.ml.linearmodel.gradientdescent.BatchGradientDescent;
import com.company.ml.linearmodel.gradientdescent.GradientDescent;
import com.company.ml.naivebayes.NaiveBayes;
import com.company.ml.randomforest.RandomForest;

/**
 * Created by AKEJU  FATAI on 2017-06-18.
 */

/**
 *This class builds the a collection of the machine learning teachniques to be used
 * Returns a MachineLearningTechniqueCollection object
 */
public class MachineLearningTechniqueCollectionBuilder {

    public static MachineLearningTechniqueCollection build(){

        MachineLearningTechniqueCollection machineLearningTechniqueCollection = new MachineLearningTechniqueCollection();

        GradientDescent gradientDescent = new BatchGradientDescent();

        Architecture architecture = new Architecture(5);
        architecture.setHiddenLayerSize(1,3);
        architecture.setHiddenLayerSize(2,5);
        architecture.setHiddenLayerSize(3,3);
        MachineLearningTechnique ann = new ANN(architecture);
        machineLearningTechniqueCollection.add(ann);

//        MachineLearningTechnique naiveBayes = new NaiveBayes();
//        MachineLearningTechnique knn = new KNN(4);
//        MachineLearningTechnique decisionTree = new DecisionTree();
//        MachineLearningTechnique randomForest = new RandomForest(5);
//        MachineLearningTechnique linearModel = new LinearModel(gradientDescent,1,"x");
//
//        machineLearningTechniqueCollection.add(naiveBayes);
//        machineLearningTechniqueCollection.add(knn);
//        machineLearningTechniqueCollection.add(decisionTree);
//        machineLearningTechniqueCollection.add(randomForest);
//        machineLearningTechniqueCollection.add(linearModel);

        return machineLearningTechniqueCollection;

    }

}
