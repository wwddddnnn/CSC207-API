package use_case.save_recipe;

import entity.Recipe;

import java.util.List;

public class SaveInputData {
    private final Recipe savedRecipe;


    public SaveInputData(Recipe recipes){
        this.savedRecipe = recipes;
    }
    Integer getRecipeId(){
        return savedRecipe.getId();
    }
    Recipe getSavedRecipe(){
        return this.savedRecipe;
    }

}
