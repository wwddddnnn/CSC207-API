package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.RecipeTag;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveDataAccessObject implements SaveRecipeDataAccessInterface {
    //private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Recipe> savedRecipes = new HashMap<>();
    private RecipeFactory recipeFactory;
    public SaveDataAccessObject(String csvpath, RecipeFactory recipeFactory) throws IOException {
        this.recipeFactory = recipeFactory;

        //csvFile = new File(csvpath);
        headers.put("id", 0);
        headers.put("name", 1);
        headers.put("image", 2);
        headers.put("instructions", 3);
        headers.put("tags", 4);
        headers.put("ingredients", 5);



            }

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(Recipe recipe) {

    }
}

