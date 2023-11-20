package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface Recipe {
    int getId();

    String getName();

//    void addImage(String imageURL, String imageType);

    String[] getImage();

//    void addRecipeTag(RecipeTag recipeTag);

//    void addInstructions(String instructions);

//    void addIngredients(HashMap<String, ArrayList<Object>> ingredients);

    String getInstructions();

    RecipeTag getRecipeTag();

    HashMap<String, ArrayList<Object>> getIngredients();
    String toString();
}
