package entity;

import java.util.ArrayList;

public class CommonRecipeTag implements RecipeTag{
    private final int recipeMinutes;
    private final int servings;
    private final ArrayList<String> cuisines;
    private final boolean vegetarianBool;
    private final boolean veganBool;
    private final ArrayList<String> intolerances;

    public CommonRecipeTag(int recipeMinutes, int servings, ArrayList<String> cuisines,
                     boolean vegetarianBool, boolean veganBool, ArrayList<String> intolerances) {
        this.recipeMinutes = recipeMinutes;
        this.servings = servings;
        this.cuisines = cuisines;
        this.vegetarianBool = vegetarianBool;
        this.veganBool = veganBool;
        this.intolerances = intolerances;
    }

    public int getRecipeMinutes() {
        return recipeMinutes;
    }

    public int getServings() {
        return servings;
    }

    public ArrayList<String> getCuisines() {
        return cuisines;
    }

    public boolean getVegetarianBool() {
        return vegetarianBool;
    }

    public boolean getVeganBool() {
        return veganBool;
    }

    public ArrayList<String> getIntolerances() {
        return intolerances;
    }



}
