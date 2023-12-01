package data_access;

import entity.Recipe;
import entity.RecipeTag;
import entity.RecipeFactory;
import entity.RecipeTagFactory;
import use_case.search_recipe.SearchRecipeDataAccessInterface;

import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchRecipeDataAccessObject implements SearchRecipeDataAccessInterface{

    private final RecipeFactory recipeFactory;
    private final RecipeTagFactory recipeTagFactory;
    private int totalRecipeAmount;
    public SearchRecipeDataAccessObject(RecipeFactory recipeFactory, RecipeTagFactory recipeTagFactory){
        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;
    }


    public ArrayList<Recipe> getByFilters(HashMap filters) {
//        for saving api calling times
//        if (filters.get("query").equals("pizza") && filters.get("cuisine").equals("Italian") && filters.get("maxTime").equals("60")){
//
//        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String requestURL = "https://api.spoonacular.com/recipes/complexSearch?apiKey=2d0253611d2b4126894e03a7be797742";

        //build the request URL depending on inputs for query, cuisine and maxTime

        if (filters.keySet().contains("query")) {
            requestURL = requestURL + "&query=" + filters.get("query");
        }

        if (filters.keySet().contains("cuisine")) {
            requestURL = requestURL + "&cuisine=" + filters.get("cuisine");
        }

        if (filters.keySet().contains("maxTime")) {
            requestURL = requestURL + "&maxReadyTime=" + filters.get("maxTime");
        }

        requestURL = requestURL + "&offset=" + filters.get("offset") + "&number=5";
        Request request = new Request.Builder()
                .url(String.format(requestURL))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray fullResultsArray = new JSONArray(responseBody.getJSONArray("results"));
            this.totalRecipeAmount =  responseBody.getInt("totalResults");


            if (!fullResultsArray.isEmpty()) {
                ArrayList<Recipe> finalRecipeList = new ArrayList<>(5);

                //get recipe from 1 to 5
                for(int i = 0; i < 5; i++){
                    try {
                        JSONObject recipeInfo = fullResultsArray.getJSONObject(i);
                        int id = recipeInfo.getInt("id");
//                        System.out.println(id);
                        String name = recipeInfo.getString("title");
                        String[] image = new String[2];
                        image[0] = recipeInfo.getString("image");
                        image[1] = recipeInfo.getString("imageType");
                        Object[] fullInfo = absorbRecipeInfo(id);   //[recipeTag, instructions, ingredients]
                        Recipe recipe = this.recipeFactory.create(id, name, image,
                                (RecipeTag) fullInfo[0], (String) fullInfo[1],
                                (HashMap<String, ArrayList<Object>>) fullInfo[2]);
                        finalRecipeList.add(recipe);
                    } catch (JSONException e){
                        System.out.println("There are only " + finalRecipeList.size() + " recipes!");
                        // only print in console. Do you want to show this sentence in SearchResultView?
                    }
                }

                return finalRecipeList;

            } else {
                ArrayList<Recipe> emptyRecipeList = new ArrayList<>(0);
                return emptyRecipeList;
            }

            /*String foodName = responseBody.getString("title");
            System.out.println(foodName);*/
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // absorbRecipeInfo creates and adds the RecipeTag, instructions (String) and ingredients (HashMap)
    // to the Recipe object that is passed in.
    //
    // New change: modify absorbRecipeInfo method from void to return Object[], which contains the
    // RecipeTag, instructions, and ingredients.
    private Object[] absorbRecipeInfo(int recipeID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.spoonacular.com/recipes/" + recipeID + "/information?apiKey=2d0253611d2b4126894e03a7be797742&includeNutrition=false"))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.length() != 3) {

                //create and add tags to RecipeTag, and add RecipeTag to Recipe object
                //tags: recipeMinutes, servings, cuisinesList, vegetarian, vegan, intolerances
                int recipeMinutes = responseBody.getInt("readyInMinutes");
                int servings = responseBody.getInt("servings");

                JSONArray cuisinesArray = responseBody.getJSONArray("cuisines");
                ArrayList<String> cuisinesList = new ArrayList<>();
                for (int i = 0; i < cuisinesArray.length(); i++) {
                    cuisinesList.add(cuisinesArray.getString(i));
                }

                boolean vegetarian = responseBody.getBoolean("vegetarian");
                boolean vegan = responseBody.getBoolean("vegan");

                ArrayList<String> intolerances = new ArrayList<>();

                if (responseBody.getBoolean("glutenFree")) intolerances.add("Gluten-Free");
                if (responseBody.getBoolean("dairyFree")) intolerances.add("Dairy-Free");

                RecipeTag recipeTag = this.recipeTagFactory.create(recipeMinutes, servings, cuisinesList,
                        vegetarian, vegan, intolerances);

                //create and add instructions to Recipe object
                String instructions = responseBody.getString("instructions");

                //create IngredientMap, and add to Recipe object

                JSONArray ingredientsJSONArray = responseBody.getJSONArray("extendedIngredients");
                HashMap<String, ArrayList<Object>> ingredientMap = new HashMap<>();
                for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                    String ingredientName = ingredientsJSONArray.getJSONObject(i).getString("name");
                    //make an ArrayList for ingredient amount and unit
                    double ingredientAmount = ingredientsJSONArray.getJSONObject(i).getDouble("amount");
                    String ingredientUnit = ingredientsJSONArray.getJSONObject(i).getString("unit");
                    ArrayList<Object> ingredientInfo = new ArrayList<>();
                    ingredientInfo.add(ingredientAmount);
                    ingredientInfo.add(ingredientUnit);
                    //add ArrayList into IngredientMap
                    ingredientMap.put(ingredientName, ingredientInfo);

                }

                return new Object[]{recipeTag, instructions, ingredientMap};

            } else {
                throw new RuntimeException("fail");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);

        }
    }

    public int getAmountByFilter(){
        System.out.println(this.totalRecipeAmount + " in DAO");
        return this.totalRecipeAmount;
    }
}
