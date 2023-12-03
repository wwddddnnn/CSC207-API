package use_case.save_recipe;

import entity.Recipe;

import interface_adapter.search_recipe.SearchedRecipe;


import java.util.List;

public class SaveInputData {

    private final SearchedRecipe savedRecipe;


    public SaveInputData(SearchedRecipe recipes){

        this.savedRecipe = recipes;
    }
    Integer getRecipeId(){
        return savedRecipe.getId();
    }

    SearchedRecipe getSavedRecipe(){

        return this.savedRecipe;
    }

}
