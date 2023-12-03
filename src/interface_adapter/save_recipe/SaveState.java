package interface_adapter.save_recipe;

import entity.Recipe;
import interface_adapter.search_recipe.SearchedRecipe;

public class SaveState {
    private SearchedRecipe recipe;
    private String recipeError = null;
    public SaveState(SaveState copy){
        this.recipe = copy.recipe;
    }
    public SaveState(){
    }
    public SearchedRecipe getRecipe(){
        return this.recipe;
    }

    public void setRecipe(SearchedRecipe recipe){
        this.recipe = recipe;
    }

    public String getRecipeError() {
        return recipeError;
    }
    public void setRecipeError(String recipeError) {
        this.recipeError = recipeError;
    }
}