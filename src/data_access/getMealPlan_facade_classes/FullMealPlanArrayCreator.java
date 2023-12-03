package data_access.getMealPlan_facade_classes;

import app.UserInfoRetriever;
import data_access.SearchRecipeDataAccessObject;
import entity.CommonRecipe;
import entity.Recipe;
import interface_adapter.search_recipe.SearchedRecipe;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* This class takes in a half-filled Meal Plan Array, which holds the ID of recipes that have been assigned to
certain meals of certain days. Its create() function replaces this ID information with a HashMap of the
strings that will be displayed for each of these recipes: the recipe's name that is displayed (called simpleString),
and all the recipe's information that is shown when the User clicks on the recipe name (called detailedString).

An example of what the returned Meal Plan Array List should look like is shown below:

    [
    [["2023-11-28, Tuesday"], [{“simpleString”: “Shrimp Salad”, “recipeObject”: SearchedRecipe}], [], []],
    [["2023-11-29, Wednesday"], [], [{“simpleString”: “Easy Pasta”, “recipeObject”: SearchedRecipe}], []],
    [["2023-11-30, Thursday"], [], [], []],
    [["2023-12-01, Friday"], [], [], []],
    [["2023-12-02, Saturday"], [], [], []],
    [["2023-12-03, Sunday"], [], [], []],
    [["2023-12-04, Monday"], [], [], []],
    ]

*/

public class FullMealPlanArrayCreator {

    private final SavedRecipeToStringConverter savedRecipeToStringConverter;
    private final SearchRecipeDataAccessObject searchRecipeDAO;

    public FullMealPlanArrayCreator(SavedRecipeToStringConverter savedRecipeToStringConverter, SearchRecipeDataAccessObject searchRecipeDAO) {
        this.savedRecipeToStringConverter = savedRecipeToStringConverter;
        this.searchRecipeDAO = searchRecipeDAO;
    }
    public ArrayList<ArrayList<ArrayList>> create(ArrayList<ArrayList<ArrayList>> halfMealPlanArray) throws IOException {
        EmptyMealPlanArrayCreator emptyMPCreator= new EmptyMealPlanArrayCreator();

        Date startDate = new Date();
        ArrayList<ArrayList<ArrayList>> newMealPlanArray = emptyMPCreator.create(startDate);

        for (int numDay = 0; numDay < 7; numDay++) {
            for (int numMeal = 1; numMeal < 4; numMeal++) {
                if(!halfMealPlanArray.get(numDay).get(numMeal).isEmpty()) {
                    Integer recipeID = (Integer) halfMealPlanArray.get(numDay).get(numMeal).get(0);   //IntelliJ recognizes it as "Object" cuz I never specified it would be an Integer.

                    String simpleRecipeString = savedRecipeToStringConverter.convertToSimpleString(recipeID);
                    Recipe recipeObject = searchRecipeDAO.getByID(recipeID);
                    SearchedRecipe searchedRecipeObject = new SearchedRecipe(recipeObject);

                    HashMap recipeStringHashMap = new HashMap<String, Object>();
                    recipeStringHashMap.put("simpleString", simpleRecipeString);
                    recipeStringHashMap.put("recipeObject", searchedRecipeObject);   //recipeStringHashMap = {“simpleString”: “Shrimp Salad”, “recipeObject”: SearchedRecipe}

                    newMealPlanArray.get(numDay).get(numMeal).add(recipeStringHashMap);
                } else {
                    HashMap recipeStringHashMap = new HashMap<String, String>();
                    recipeStringHashMap.put("simpleString", "");
                    recipeStringHashMap.put("recipeObject", null);

                    newMealPlanArray.get(numDay).get(numMeal).add(recipeStringHashMap);
                }
            }
        }
        //TODO: delete this print line.
        System.out.println(newMealPlanArray);
        return newMealPlanArray;
    }
}
