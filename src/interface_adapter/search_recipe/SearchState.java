package interface_adapter.search_recipe;

import java.util.ArrayList;

public class SearchState {

    private String query = "";

    private String cuisine = "";

    private String maxTime = "";

    private String searchByFilterError = null;

    private ArrayList<String> recipe = null;

    public SearchState(SearchState copy){
        query = copy.query;
        cuisine = copy.cuisine;
        maxTime = copy.maxTime;
        searchByFilterError = copy.searchByFilterError;
        recipe = copy.recipe;
    }
    public SearchState(){}

    public String getQuery(){return query;}

    public String getCuisine(){return cuisine;}

    public String getMaxTime(){return maxTime;}

    public String getSearchByFilterError() {
        return searchByFilterError;
    }

    public ArrayList<String> getRecipe(){return recipe;}

    public void setQuery(String query){this.query = query;}

    public void setCuisine(String cuisine){this.cuisine = cuisine;}

    public void setMaxTime(String maxTime){this.maxTime = maxTime;}

    public void setSearchByFilterError(String searchByFilterError) {
        this.searchByFilterError = searchByFilterError;
    }

    public void setRecipe(ArrayList<String> recipe){this.recipe = recipe;}

    public String toString(){
        return "SearchState{" +
                "query=" + query + ", cuisine=" + cuisine + ", maxTime=" + maxTime +
                ", \nresult = " + recipe;
    }
}
