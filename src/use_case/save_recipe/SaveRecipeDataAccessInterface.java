package use_case.save_recipe;

import entity.Recipe;
import interface_adapter.search_recipe.SearchedRecipe;

public interface SaveRecipeDataAccessInterface {
    boolean existsById(Integer identifier);
    void save(SearchedRecipe recipe);

}
