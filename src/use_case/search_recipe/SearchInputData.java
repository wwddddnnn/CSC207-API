package use_case.search_recipe;

import java.util.ArrayList;
import java.util.List;


public class SearchInputData {
    private final String query;

    private final String cuisine;

    public SearchInputData(String query, String cuisine) {
        this.query = query;
        this.cuisine = cuisine;
    }

    public String getQuery() {
        return query;
    }

    public String getCuisine(){
        return cuisine;
    }
}
