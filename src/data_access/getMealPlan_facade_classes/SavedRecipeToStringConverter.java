package data_access.getMealPlan_facade_classes;

import entity.Recipe;
import entity.RecipeTag;
import entity.RecipeFactory;
import entity.RecipeTagFactory;

//TODO: Not implemented yet; look at details of the saved file to implement.

public class SavedRecipeToStringConverter {
    private RecipeFactory recipeFactory;
    private RecipeTagFactory recipeTagFactory;
    public SavedRecipeToStringConverter(RecipeFactory recipeFactory, RecipeTagFactory recipeTagFactory) {
        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;
    }
    public String convertToDetailedString(Integer id) {
        //get info and create RecipeTag, recipeInfo and Recipe from saved file.

        //RecipeTag recipeTag = recipeTagFactory.create();
        //Object[] recipeInfo = [recipeTag, instructions, ingredients];
        //Recipe recipe = this.recipeFactory.create();
        //return recipe.toString();
        return "This is a detailed string with all the recipe info.";
    }
    public String convertToSimpleString(Integer id) {
        //get info and return Recipe name from saved file.
        //return recipeName
        return "This is a simple string with just the recipe name.";
    }
}
