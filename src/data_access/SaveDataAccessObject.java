package data_access;

import entity.*;
import interface_adapter.search_recipe.SearchedRecipe;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.display_saved_recipe.DisplaySavedDataAccessInterface;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveDataAccessObject implements SaveRecipeDataAccessInterface, DisplaySavedDataAccessInterface {
    private final File csvFile;
    private final Map<Integer, SearchedRecipe> savedRecipes = new HashMap<>();
    private final RecipeFactory recipeFactory;
    private final RecipeTagFactory recipeTagFactory;
    public SaveDataAccessObject(String csvpath, RecipeFactory recipeFactory, RecipeTagFactory recipeTagFactory) throws IOException {
        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;
        csvFile = new File(csvpath);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    System.out.println(row);
                    int id = Integer.parseInt(row);
                    SearchedRecipe recipe = absorbRecipeInfo(id);
                    savedRecipes.put(id, recipe);
                }
            }
        }
    }

    private SearchedRecipe absorbRecipeInfo(int recipeID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.spoonacular.com/recipes/" + recipeID + "/information?apiKey=e7877dbada2b46aeb588e7418550c78f&includeNutrition=false"))
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
                String title = responseBody.getString("title");

                String[] image = new String[2];
                image[0] = responseBody.getString("image");
                image[1] = responseBody.getString("imageType");

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
                SearchedRecipe searchedRecipe = new SearchedRecipe(this.recipeFactory.create(recipeID, title,image,recipeTag, instructions, ingredientMap));
                return searchedRecipe;
            } else {
                throw new RuntimeException("fail");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(SearchedRecipe recipe) {

        savedRecipes.put(recipe.getId(), recipe);
        this.save();

    }
    @Override
    public boolean existsById(Integer identifier) {
        return savedRecipes.containsKey(identifier);
    }

    public void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            for (SearchedRecipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getId());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SearchedRecipe[] getSavedRecipes() {
        return this.savedRecipes.values().toArray(new SearchedRecipe[0]);
    }
}