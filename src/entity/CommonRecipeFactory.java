package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonRecipeFactory implements RecipeFactory{
    @Override
    public Recipe create(int id, String name, String[] image, RecipeTag recipeTag,
                         String instructions, HashMap<String, ArrayList<Object>> ingredients) {
        return new CommonRecipe(id, name, image, recipeTag, instructions, ingredients);
    }
}
