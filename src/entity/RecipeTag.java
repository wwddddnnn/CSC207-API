package entity;

import java.util.ArrayList;

public class RecipeTag {
    private int recipeMinutes;
    private int servings;
    private ArrayList<String> cuisines;
    private boolean vegetarianBool;
    private boolean veganBool;
    private ArrayList<String> intolerances;

    public RecipeTag(int recipeMinutes, int servings, ArrayList<String> cuisines,
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
