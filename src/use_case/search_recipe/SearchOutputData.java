package use_case.search_recipe;
import entity.Recipe;

import java.util.List;

public class SearchOutputData {
    private final List<Recipe> recipes;

    public SearchOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
