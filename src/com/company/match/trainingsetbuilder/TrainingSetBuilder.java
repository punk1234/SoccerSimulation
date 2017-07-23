package com.company.match.trainingsetbuilder;

import com.company.db.model.DBFixtureGroup;
import com.company.ml.model.TrainingSet;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-29.
 */
public interface TrainingSetBuilder {

    public TrainingSet build(String teamName, DBFixtureGroup fixtures);

}
