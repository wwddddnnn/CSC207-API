package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonRecipe implements Recipe{

    private final int id;
    private final String name;
    private final String[] image;
    private final RecipeTag recipeTag;
    private final String instructions;
    private final HashMap<String, ArrayList<Object>> ingredients;

    public CommonRecipe(int id, String name, String[] image, RecipeTag recipeTag,
                        String instructions, HashMap<String, ArrayList<Object>> ingredients) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.recipeTag = recipeTag;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getImage() { return image; }

    // image is final, it will not be changed once the recipe was created.
//    public void addImage(String imageURL, String imageType) {
//        this.image = new String[]{imageURL, imageType};
//    }

    // RecipeTag is final, it will not be changed once the recipe was created.
//    public void addRecipeTag(RecipeTag recipeTag) {
//        this.recipeTag = recipeTag;
//    }

//    image is final, it will not be changed once the recipe was created.
//    public void addInstructions(String instructions) {
//        this.instructions = instructions;
//    }

    // ingredients is final, it will not be changed once the recipe was created.
//    public void addIngredients(HashMap<String, ArrayList<Object>> ingredients) {
//        this.ingredients = ingredients;
//    }

    public String getInstructions(){
        return this.instructions;
    }

    public RecipeTag getRecipeTag(){
        return this.recipeTag;
    }

    public HashMap<String, ArrayList<Object>> getIngredients() {
        return this.ingredients;
    }
    @Override
    public String toString() {

        RecipeTag tags = getRecipeTag();
        Integer minutes = tags.getRecipeMinutes();
        Integer servings = tags.getServings();
        ArrayList<String> cuisines = tags.getCuisines();
        boolean vegetarianBool = tags.getVegetarianBool();
        boolean veganBool = tags.getVeganBool();
        ArrayList<String> intolerances = tags.getIntolerances();

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
        String genInfoString = "Minutes to make: " + minutes +
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

        String finalString = nameString + "\n" + genInfoString + "\n" + vegString + "\n" + intolString + "\n";
        return finalString;
    }
}
