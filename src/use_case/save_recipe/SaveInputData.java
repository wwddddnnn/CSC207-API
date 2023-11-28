package use_case.save_recipe;

import entity.Recipe;

import java.util.List;

public class SaveInputData {
    private final Recipe savedRecipe;

    public SaveInputData(Recipe recipes){
        this.savedRecipe = recipes;
    }
    String getRecipeName(){
        return savedRecipe.getName();
    }
    Recipe getSavedRecipe(){
        return this.savedRecipe;
    }

}
