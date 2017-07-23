package com.company.ml.tester;

/**
 * Created by AKEJU  FATAI on 2017-05-26.
 */
public class MainTesterPrinter {

    public static void print(MainTester mainTester){

        print("DECISION TREE", mainTester.getDecisionTreeResult());
        print("RANDOM FOREST", mainTester.getRandomForestResult());
        print("KNN", mainTester.getKnnResult());
        print("LINEAR MODEL", mainTester.getLinearModelResult());
        print("NAIVE BAYES", mainTester.getNaiveBayesResult());

    }

    private static void print(String techniqueName, Double outcome){

        System.out.println(techniqueName + " : " + outcome);

    }

}
