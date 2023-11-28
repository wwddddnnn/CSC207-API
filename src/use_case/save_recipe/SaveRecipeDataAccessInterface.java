package use_case.save_recipe;

import entity.Recipe;

public interface SaveRecipeDataAccessInterface {
    boolean existsByName(String identifier);
    void save(Recipe recipe);

}
