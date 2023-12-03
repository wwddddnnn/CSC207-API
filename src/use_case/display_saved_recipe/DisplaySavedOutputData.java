package use_case.display_saved_recipe;

import interface_adapter.search_recipe.SearchedRecipe;

public class DisplaySavedOutputData {
    private final SearchedRecipe[] searchedRecipes;

    public DisplaySavedOutputData(SearchedRecipe[] searchedRecipes) {
        this.searchedRecipes = searchedRecipes;
    }

    public SearchedRecipe[] getSearchedRecipes() {
        return searchedRecipes;
    }
}
