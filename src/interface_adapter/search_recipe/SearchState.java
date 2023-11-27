package interface_adapter.search_recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchState {

    private String query = "";

    private String cuisine = "";

    private String maxTime = "";

    private String searchByFilterError = "";

    private int totalRecipeAmount;

    private HashMap<String, String> recipe = new HashMap<>();

    public SearchState(SearchState copy){
        query = copy.query;
        cuisine = copy.cuisine;
        maxTime = copy.maxTime;
        searchByFilterError = copy.searchByFilterError;
        recipe = copy.recipe;
        totalRecipeAmount = copy.totalRecipeAmount;
    }
    public SearchState(){}

    public String getQuery(){return query;}

    public String getCuisine(){return cuisine;}

    public String getMaxTime(){return maxTime;}

    public String getSearchByFilterError() {
        return searchByFilterError;
    }

    public HashMap<String, String> getRecipe(){return recipe;}

    public void setQuery(String query){this.query = query;}

    public void setCuisine(String cuisine){this.cuisine = cuisine;}

    public void setMaxTime(String maxTime){this.maxTime = maxTime;}

    public void setSearchByFilterError(String searchByFilterError) {
        this.searchByFilterError = searchByFilterError;
    }

    public void setRecipe(HashMap<String, String> recipe){
        this.recipe = recipe;
    }

    public void setTotalRecipeAmount(int amount){
        this.totalRecipeAmount = amount;
    }

    public int getTotalRecipeAmount(){
        return this.totalRecipeAmount;
    }

    public String toString(){
        return "SearchState{" +
                "query=" + query + ", cuisine=" + cuisine + ", maxTime=" + maxTime +
                ", \nresult = " + recipe;
    }
}
