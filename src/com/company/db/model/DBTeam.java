package com.company.db.model;

/**
 * Created by AKEJU  FATAI on 2017-05-28.
 */
public class DBTeam {

    private String name;
    private String code;
    private String shortName;

    public DBTeam(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
