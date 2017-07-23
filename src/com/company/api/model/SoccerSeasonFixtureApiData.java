package com.company.api.model;

import java.util.*;

/**
 * Created by AKEJU  FATAI on 2017-05-09.
 */
public class SoccerSeasonFixtureApiData {

    private SoccerSeasonTeamsLinksApiData _links;
    private int count;
    private List<FixtureApiData> fixtures;

    public SoccerSeasonFixtureApiData(){

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

    public List<FixtureApiData> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<FixtureApiData> fixtures) {
        this.fixtures = fixtures;
    }
}
