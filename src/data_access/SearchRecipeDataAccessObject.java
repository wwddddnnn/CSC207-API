package data_access;

import entity.Recipe;
import entity.RecipeTag;
import entity.RecipeFactory;
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

    private RecipeFactory recipeFactory;

    public SearchRecipeDataAccessObject(RecipeFactory recipeFactory){
        this.recipeFactory = recipeFactory;
    }

    public ArrayList<Recipe> getByFilters(HashMap filters) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String requestURL = "https://api.spoonacular.com/recipes/complexSearch?apiKey=c8eb3493121949b592c890859e2a5240";

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

        requestURL = requestURL + "&number=5";

        Request request = new Request.Builder()
                .url(String.format(requestURL))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray fullResultsArray = new JSONArray(responseBody.getJSONArray("results"));

            if (!fullResultsArray.isEmpty()) {

                //get recipe 1
                JSONObject recipe1Info = fullResultsArray.getJSONObject(0);

                Recipe recipe1 = this.recipeFactory.create(recipe1Info.getInt("id"),
                        recipe1Info.getString("title"));
                recipe1.addImage(recipe1Info.getString("image"), recipe1Info.getString("imageType"));
                absorbRecipeInfo(recipe1);

                //get recipe 2
                JSONObject recipe2Info = fullResultsArray.getJSONObject(1);
                Recipe recipe2 = this.recipeFactory.create(recipe2Info.getInt("id"),
                        recipe2Info.getString("title"));
                recipe2.addImage(recipe2Info.getString("image"), recipe2Info.getString("imageType"));
                absorbRecipeInfo(recipe2);

                //get recipe 3
                JSONObject recipe3Info = fullResultsArray.getJSONObject(2);
                Recipe recipe3 = this.recipeFactory.create(recipe3Info.getInt("id"),
                        recipe3Info.getString("title"));
                recipe3.addImage(recipe3Info.getString("image"), recipe3Info.getString("imageType"));
                absorbRecipeInfo(recipe3);

                //get recipe 4
                JSONObject recipe4Info = fullResultsArray.getJSONObject(3);
                Recipe recipe4 = this.recipeFactory.create(recipe4Info.getInt("id"),
                        recipe4Info.getString("title"));
                recipe4.addImage(recipe4Info.getString("image"), recipe4Info.getString("imageType"));
                absorbRecipeInfo(recipe4);

                //get recipe 5
                JSONObject recipe5Info = fullResultsArray.getJSONObject(4);
                Recipe recipe5 = this.recipeFactory.create(recipe5Info.getInt("id"),
                        recipe5Info.getString("title"));
                recipe5.addImage(recipe5Info.getString("image"), recipe5Info.getString("imageType"));
                absorbRecipeInfo(recipe5);

                ArrayList<Recipe> finalRecipeList = new ArrayList<>(5);
                finalRecipeList.add(recipe1);
                finalRecipeList.add(recipe2);
                finalRecipeList.add(recipe3);
                finalRecipeList.add(recipe4);
                finalRecipeList.add(recipe5);

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
    private void absorbRecipeInfo(Recipe recipe) {
        int recipeID = recipe.getId();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.spoonacular.com/recipes/" + recipeID + "/information?apiKey=c8eb3493121949b592c890859e2a5240&includeNutrition=false"))
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

                ArrayList<String> intolerances = new ArrayList<String>();
                if (responseBody.getBoolean("glutenFree")) {
                    intolerances.add("Gluten-Free");
                }
                if (responseBody.getBoolean("dairyFree")) {
                    intolerances.add("Dairy-Free");
                }

                RecipeTag recipeTag = new RecipeTag(recipeMinutes, servings, cuisinesList,
                        vegetarian, vegan, intolerances);

                recipe.addRecipeTag(recipeTag);

                //create and add instructions to Recipe object
                String instructions = responseBody.getString("instructions");
                recipe.addInstructions(instructions);

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
                recipe.addIngredients(ingredientMap);

            } else {
                throw new RuntimeException("fail");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);

        }
    }
}
