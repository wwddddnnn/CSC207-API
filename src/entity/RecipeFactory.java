package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface RecipeFactory {
    Recipe create(int id, String name, String[] image, RecipeTag recipeTag,
                  String instructions, HashMap<String, ArrayList<Object>> ingredients);
}
