package use_case.search_recipe;

import java.util.ArrayList;
import java.util.List;


public class SearchInputData {
    private final String query;

    private final String cuisine;
    private final String maxTime;

    public SearchInputData(String query, String cuisine, String maxTime) {
        this.query = query;
        this.cuisine = cuisine;
        this.maxTime = maxTime;
    }

    public String getQuery() {
        return query;
    }
    public String getCuisine(){
        return cuisine;
    }
    public String getMaxTime(){
        return maxTime;
    }
}
