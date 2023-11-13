package use_case.search_recipe;

import entity.Recipe;

import java.util.List;

public interface SearchRecipeDataAccessInterface {

    void getByCuisine(String cuisine);

    List<Recipe> findRecipesByQuery(String query);
}
