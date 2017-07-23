package com.company;

import com.company.db.model.DBResult;
import com.company.match.Result;

/**
 * Created by AKEJU  FATAI on 2017-06-27.
 */
public class ResultConverter {

    public static Result convert(DBResult dbResult){

        Result result = new Result();
        result.setHomeTeamScoreFullTime(dbResult.getHomeTeamScoreFullTime());
        result.setAwayTeamScoreFullTime(dbResult.getAwayTeamScoreFullTime());
        result.setHomeTeamScoreHalfTime(dbResult.getHomeTeamScoreHalfTime());
        result.setAwayTeamScoreHalfTime(dbResult.getAwayTeamScoreHalfTime());
        return result;

    }

}
