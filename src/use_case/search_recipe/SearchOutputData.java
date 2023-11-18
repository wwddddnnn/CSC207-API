package use_case.search_recipe;
import entity.Recipe;

import java.util.List;

public class SearchOutputData {
    private final List<Recipe> recipes;

    private final String cuisine;

    private final String query;

    private final String maxTime;

    public SearchOutputData(List<Recipe> recipes, String cuisine, String query, String maxTime) {

        this.recipes = recipes;
        this.cuisine = cuisine;
        this.query = query;
        this.maxTime = maxTime;
    }

    public String getRecipes() {
        return recipes.toString();
    }

    public String getQuery(){
        return query;
    }

    public String getCuisine(){
        return cuisine;
    }

    public String getMaxTime(){
        return maxTime;
    }
}
