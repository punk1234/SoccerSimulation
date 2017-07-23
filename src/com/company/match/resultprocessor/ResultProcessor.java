package com.company.match.resultprocessor;

import com.company.match.Result;

/**
 * Created by AKEJU  FATAI on 2017-06-08.
 */
public interface ResultProcessor {

    public String process(Result result);
    public Double getValue(Result result);
    public String get(Double value);

}
