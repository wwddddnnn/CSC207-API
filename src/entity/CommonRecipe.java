package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonRecipe implements Recipe{

    private final int id;
    private final String name;
    private String[] image;
    private RecipeTag recipeTag;
    private String instructions;
    private HashMap<String, ArrayList<Object>> ingredients;

    public CommonRecipe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addImage(String imageURL, String imageType) {
        this.image = new String[]{imageURL, imageType};
    }

    public String[] getImage() { return image; }

    public void addRecipeTag(RecipeTag recipeTag) {
        this.recipeTag = recipeTag;
    }

    public void addInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void addIngredients(HashMap<String, ArrayList<Object>> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions(){
        return this.instructions;
    }

    public RecipeTag getRecipeTag(){
        return this.recipeTag;
    }

    public HashMap<String, ArrayList<Object>> getIngredients() {
        return this.ingredients;
    }
}
