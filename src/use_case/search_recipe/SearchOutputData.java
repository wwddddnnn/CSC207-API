package use_case.search_recipe;
import entity.Recipe;

import java.util.List;

public class SearchOutputData {
    private final List<Recipe> recipes;

    private final String cuisine;

    private final String query;

    public SearchOutputData(List<Recipe> recipes, String cuisine, String query) {

        this.recipes = recipes;
        this.cuisine = cuisine;
        this.query = query;
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
}
