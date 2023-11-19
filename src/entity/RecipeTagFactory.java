package entity;

import java.util.ArrayList;

public interface RecipeTagFactory {

    RecipeTag create(int recipeMinutes, int servings, ArrayList<String> cuisines,
                     boolean vegetarianBool, boolean veganBool, ArrayList<String> intolerances);

}
