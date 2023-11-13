package data_access;

import entity.Recipe;
import entity.RecipeTag;
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
import java.util.List;

public class SearchRecipeDataAccessObject implements SearchRecipeDataAccessInterface{

    public void getByCuisine(String cuisine) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.spoonacular.com/recipes/complexSearch?apiKey=c8eb3493121949b592c890859e2a5240&cuisine=" + cuisine + "&maxFat=25&number=5"))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray fullResultsArray = new JSONArray(responseBody.getJSONArray("results"));

            if (responseBody.getInt("status_code") == 200) {

                //get recipe 1
                JSONObject recipe1Info = new JSONObject(fullResultsArray.getJSONObject(0));
                Recipe recipe1 = Recipe.builder()
                        .id(recipe1Info.getInt("id"))
                        .name(recipe1Info.getString("title"))
                        .build();
                recipe1.addImage(recipe1Info.getString("image"), recipe1Info.getString("imageType"));
                absorbRecipeInfo(recipe1);

                //get recipe 2
                JSONObject recipe2Info = new JSONObject(fullResultsArray.getJSONObject(1));
                Recipe recipe2 = Recipe.builder()
                        .id(recipe2Info.getInt("id"))
                        .name(recipe2Info.getString("title"))
                        .build();
                recipe2.addImage(recipe2Info.getString("image"), recipe2Info.getString("imageType"));
                absorbRecipeInfo(recipe2);

                //get recipe 3
                JSONObject recipe3Info = new JSONObject(fullResultsArray.getJSONObject(2));
                Recipe recipe3 = Recipe.builder()
                        .id(recipe3Info.getInt("id"))
                        .name(recipe3Info.getString("title"))
                        .build();
                recipe3.addImage(recipe3Info.getString("image"), recipe3Info.getString("imageType"));
                absorbRecipeInfo(recipe3);

                //get recipe 4
                JSONObject recipe4Info = new JSONObject(fullResultsArray.getJSONObject(3));
                Recipe recipe4 = Recipe.builder()
                        .id(recipe4Info.getInt("id"))
                        .name(recipe4Info.getString("title"))
                        .build();
                recipe4.addImage(recipe4Info.getString("image"), recipe4Info.getString("imageType"));
                absorbRecipeInfo(recipe4);

                //get recipe 5
                JSONObject recipe5Info = new JSONObject(fullResultsArray.getJSONObject(4));
                Recipe recipe5 = Recipe.builder()
                        .id(recipe5Info.getInt("id"))
                        .name(recipe5Info.getString("title"))
                        .build();
                recipe5.addImage(recipe5Info.getString("image"), recipe5Info.getString("imageType"));
                absorbRecipeInfo(recipe5);

            } else {
                throw new RuntimeException("fail");
            }

            /*String foodName = responseBody.getString("title");
            System.out.println(foodName);*/
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Recipe> findRecipesByQuery(String query) {
        return null;
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

            if (responseBody.getInt("status_code") == 200) {

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
