package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.RecipeTag;
import entity.User;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveDataAccessObject implements SaveRecipeDataAccessInterface {
    private final File csvFileId;
    private final File csvFileName;
    private final File csvFileImage;
    private final File csvFileInstructions;
    private final File csvFileTags;
    private final File csvFileIngredients;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Recipe> savedRecipes = new HashMap<>();
    private RecipeFactory recipeFactory;
    public SaveDataAccessObject(String csvpath0, String csvpath1,
                                String csvpath2,String csvpath3,String csvpath4,String csvpath5,
                                RecipeFactory recipeFactory) throws IOException {
        this.recipeFactory = recipeFactory;

        csvFileId = new File(csvpath0);
        csvFileName = new File(csvpath1);
        csvFileImage = new File(csvpath2);
        csvFileInstructions = new File(csvpath3);
        csvFileTags = new File(csvpath4);
        csvFileIngredients = new File(csvpath5);
        headers.put("id", 0);
        headers.put("name", 1);
        headers.put("image", 2);
        headers.put("instructions", 3);
        headers.put("tags", 4);
        headers.put("ingredients", 5);



            }

    @Override
    public void save(Recipe recipe) {
        savedRecipes.put(recipe.getName(), recipe);
        this.save();

    }
    @Override
    public boolean existsByName(String identifier) {
        return savedRecipes.containsKey(identifier);
    }

    public void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFileId));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getName());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

