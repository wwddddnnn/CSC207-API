package entity;

import java.util.ArrayList;

public class CommonRecipeTagFactory implements RecipeTagFactory{
    @Override
    public RecipeTag create(int recipeMinutes, int servings, ArrayList<String> cuisines, boolean vegetarianBool, boolean veganBool, ArrayList<String> intolerances) {
        return new CommonRecipeTag(recipeMinutes, servings, cuisines, vegetarianBool, veganBool, intolerances);
    }
}
