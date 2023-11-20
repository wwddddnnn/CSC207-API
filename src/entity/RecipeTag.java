package entity;

import java.util.ArrayList;

public interface RecipeTag {
    int getRecipeMinutes();

    int getServings();

    ArrayList<String> getCuisines();

    boolean getVegetarianBool();

    boolean getVeganBool();

    ArrayList<String> getIntolerances();

}
