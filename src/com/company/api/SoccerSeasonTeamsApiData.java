package com.company.api;

import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class SoccerSeasonTeamsApiData {

    private SoccerSeasonTeamsLinksApiData _links;
    private int count;
    private List<TeamApiData> teams;

    public SoccerSeasonTeamsApiData(){

    }

    public SoccerSeasonTeamsLinksApiData get_links() {
        return _links;
    }

    public void set_links(SoccerSeasonTeamsLinksApiData _links) {
        this._links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TeamApiData> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamApiData> teams) {
        this.teams = teams;
    }
}
