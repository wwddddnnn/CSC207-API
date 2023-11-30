package use_case.save_recipe;

import entity.Recipe;

public interface SaveRecipeDataAccessInterface {
    boolean existsById(Integer identifier);
    void save(Recipe recipe);

}
