package use_case.display_saved_recipe;

import interface_adapter.search_recipe.SearchedRecipe;

import java.util.Map;

public interface DisplaySavedDataAccessInterface {
    SearchedRecipe[] getSavedRecipes();
}
