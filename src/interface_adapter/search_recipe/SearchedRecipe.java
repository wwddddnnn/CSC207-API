package interface_adapter.search_recipe;

import entity.Recipe;
import entity.RecipeTag;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchedRecipe {
    private int id;
    private String name;
    private String[] image;
    private String instructions;
    private HashMap<String, ArrayList<Object>> ingredients;
    private int recipeMinutes;
    private int servings;
    private ArrayList<String> cuisines;
    private boolean vegetarianBool;
    private boolean veganBool;
    private ArrayList<String> intolerances;

    public SearchedRecipe(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.image = recipe.getImage();
        this.instructions = recipe.getInstructions();
        this.ingredients = recipe.getIngredients();
        RecipeTag recipeTag = recipe.getRecipeTag();
        this.recipeMinutes = recipeTag.getRecipeMinutes();
        this.servings = recipeTag.getServings();
        this.cuisines = recipeTag.getCuisines();
        this.vegetarianBool = recipeTag.getVegetarianBool();
        this.veganBool = recipeTag.getVeganBool();
        this.intolerances = recipeTag.getIntolerances();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getImageURL() { return image[0]; }

    public String toString(){

        //name string
        String nameString = "Name: " + getName();

        //general info string
        String cuisinesString = "";
        for (String cuisine : cuisines) {
            if (cuisinesString.isEmpty()) {
                cuisinesString = cuisinesString + cuisine;
            } else {
                cuisinesString = cuisinesString + ", " + cuisine;
            }
        }
        String genInfoString = "Minutes to make: " + recipeMinutes +
                "\nServings: " + servings +
                "\nCuisines:" + cuisinesString;

        //vegetarian and vegan string
        String vegetarianString;
        String veganString;
        if (vegetarianBool) {
            vegetarianString = "Yes";
        } else {
            vegetarianString = "No";
        }
        if (veganBool) {
            veganString = "Yes";
        } else {
            veganString = "No";
        }
        String vegString = "Vegetarian: " + vegetarianString +
                "\nVegan: " + veganString;

        //intolerances string
        String intolerancesString = "";
        for (String intolerance : intolerances) {
            if (intolerancesString.isEmpty()) {
                intolerancesString = intolerancesString + intolerance;
            } else {
                intolerancesString = intolerancesString + ", " + intolerance;
            }
        }
        String intolString = "Intolerances: " + intolerancesString;

        return nameString + "\n" + genInfoString + "\n" + vegString + "\n" + intolString + "\n" + instructions;
    }
}
