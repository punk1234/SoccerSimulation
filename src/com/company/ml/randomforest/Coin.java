package com.company.ml.randomforest;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-22.
 */
public class Coin {

    private static Random random = new Random();
    private static double boundaryValue = 0.5;

    public static CoinSide flip(){

        double randomValue = random.nextDouble();
        if(randomValue < 0.5){
            return CoinSide.HEAD;
        }
        else{
            return CoinSide.TAIL;
        }

    }

}
