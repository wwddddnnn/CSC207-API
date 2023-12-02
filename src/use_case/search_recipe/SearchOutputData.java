package use_case.search_recipe;
import interface_adapter.search_recipe.SearchedRecipe;

import java.util.HashMap;
import java.util.List;

public class SearchOutputData {
    private final List<SearchedRecipe> recipes;

    private final String cuisine;

    private final String query;

    private final String maxTime;

    private final int totalRecipeAmount;

    public SearchOutputData(List<SearchedRecipe> recipes, String cuisine, String query, String maxTime, int totalRecipeAmount) {

        this.recipes = recipes;
        this.cuisine = cuisine;
        this.query = query;
        this.maxTime = maxTime;
        this.totalRecipeAmount = totalRecipeAmount;
    }

    public HashMap<String, SearchedRecipe> getRecipes() {
        // HashMap<Recipe.getName(), Recipe.toString()>
        HashMap<String, SearchedRecipe> recipeStringList = new HashMap<>(5);
        for (SearchedRecipe recipe : recipes) {
            String recipeString = recipe.toString();
            recipeStringList.put(recipe.getName(), recipe);
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
