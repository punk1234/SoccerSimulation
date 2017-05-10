package com.company.model;

/**
 * Created by AKEJU  FATAI on 2017-03-07.
 */
public class Team {

    private Integer id;

    private String name;

    public Team(Integer id, String name){

        this.id = id;
        this.name = name;

    }

    public void setId(int id){

        this.id = id;

    }

    public Integer getId(){

        return id;

    }

    public void setName(String name){

        this.name = name;

    }

    public String getName(){

        return name;

    }

    public boolean equal(Team team){

        if(id == team.getId() && name == team.getName()){
            return true;
        }
        return false;

    }

}
