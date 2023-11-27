package use_case.save_recipe;

import entity.Recipe;

import java.util.List;

public class SaveInputData {
    private final List<Recipe> recipes;

    public SaveInputData(List<Recipe> recipes){
        this.recipes = recipes;
    }

}
