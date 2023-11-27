package use_case.search_recipe;
import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchOutputData {
    private final List<Recipe> recipes;

    private final String cuisine;

    private final String query;

    private final String maxTime;

    private final int totalRecipeAmount;

    public SearchOutputData(List<Recipe> recipes, String cuisine, String query, String maxTime, int totalRecipeAmount) {

        this.recipes = recipes;
        this.cuisine = cuisine;
        this.query = query;
        this.maxTime = maxTime;
        this.totalRecipeAmount = totalRecipeAmount;
    }

    public HashMap<String, String> getRecipes() {
        // HashMap<Recipe.getName(), Recipe.toString()>
        HashMap<String, String> recipeStringList = new HashMap<>(5);
        for (Recipe recipe : recipes) {
            String recipeString = recipe.toString();
            recipeStringList.put(recipe.getName(), recipeString);
        }
        return recipeStringList;
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

    public int getTotalRecipeAmount(){
        return this.totalRecipeAmount;
    }
}
