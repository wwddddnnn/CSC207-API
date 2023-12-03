package interface_adapter.search_recipe_results;

import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchedRecipe;

public class DisplayState {
    private SearchedRecipe searchedRecipe;
    public DisplayState() {}

    public void setSearchedRecipe(SearchedRecipe searchedRecipe){
        this.searchedRecipe = searchedRecipe;
    }

    public SearchedRecipe getSearchedRecipe(){
        return this.searchedRecipe;
    }
}
