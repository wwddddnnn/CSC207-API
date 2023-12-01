package interface_adapter.save_recipe;

import entity.Recipe;
import interface_adapter.search_recipe.SearchedRecipe;

public class SaveState {
    private SearchedRecipe recipes;
    public SaveState(SaveState copy){
        this.recipes = copy.recipes;
    }
    public SaveState(){

    }
    public SearchedRecipe getRecipes(){
        return this.recipes;
    }

    public void setRecipes(SearchedRecipe recipe){
        this.recipes = recipe;
    }

}
