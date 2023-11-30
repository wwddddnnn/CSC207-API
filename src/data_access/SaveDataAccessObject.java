package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveDataAccessObject implements SaveRecipeDataAccessInterface {
    private final File csvFile;
    private final File csvFileId;
    private final File csvFileName;
    private final File csvFileImage;
    private final File csvFileInstructions;
    private final File csvFileTags;
    private final File csvFileIngredients;
    private JSONArray fullResultsArray;

    private final Map<Integer, Recipe> savedRecipes = new HashMap<>();
    private RecipeFactory recipeFactory;
    private RecipeTagFactory recipeTagFactory;
    public SaveDataAccessObject(String csvpath, String csvpath0, String csvpath1,
                                String csvpath2, String csvpath3, String csvpath4, String csvpath5,
                                RecipeFactory recipeFactory, RecipeTagFactory recipeTagFactory) throws IOException {
        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;

        csvFileId = new File(csvpath0);
        csvFileName = new File(csvpath1);
        csvFileImage = new File(csvpath2);
        csvFileInstructions = new File(csvpath3);
        csvFileTags = new File(csvpath4);
        csvFileIngredients = new File(csvpath5);

        csvFile = new File(csvpath);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int id = Integer.parseInt(col[0]);

                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("text/plain");
                    RequestBody body = RequestBody.create(mediaType, "");
                    Request request = new Request.Builder()
                            .url("https://api.spoonacular.com/recipes/"+id+"/information?apiKey=b8f6333f2a07457b925cce0d5ddda576")
                            .method("GET", body)
                            .addHeader("Accept", "application/json")
                            .build();

                    Response response = client.newCall(request).execute();
                    JSONObject responseBody = new JSONObject(response.body().string());
                    int id1 = responseBody.getInt("id");
                    String name = responseBody.getString("title");
                    String[] image = new String[2];
                    image[0] = responseBody.getString("image");
                    image[1] = responseBody.getString("imageType");
                    Object[] fullInfo = this.absorbRecipeInfo(id);
                    Recipe recipe = this.recipeFactory.create(id1, name, image,
                            (RecipeTag) fullInfo[0], (String) fullInfo[1],
                            (HashMap<String, ArrayList<Object>>) fullInfo[2]);
                    savedRecipes.put(id, recipe);
                }
            }
        }
    }

    private Object[] absorbRecipeInfo(int recipeID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.spoonacular.com/recipes/" + recipeID + "/information?apiKey=b8f6333f2a07457b925cce0d5ddda576&includeNutrition=false"))
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
    @Override
    public void save(Recipe recipe) {
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

            writer = new BufferedWriter(new FileWriter(csvFileId));
            writer.write("id");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getId());
                writer.write(line);
                writer.newLine();
            }

            writer.close();



            writer = new BufferedWriter(new FileWriter(csvFileName));
            writer.write("name");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getName());
                writer.write(line);
                writer.newLine();
            }

            writer.close();



            writer = new BufferedWriter(new FileWriter(csvFileImage));
            writer.write("image");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getImage().toString());
                writer.write(line);
                writer.newLine();
            }

            writer.close();


            writer = new BufferedWriter(new FileWriter(csvFileInstructions));
            writer.write("instructions");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getInstructions());
                writer.write(line);
                writer.newLine();
            }

            writer.close();



            writer = new BufferedWriter(new FileWriter(csvFileTags));
            writer.write("tags");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getRecipeTag());
                writer.write(line);
                writer.newLine();
            }

            writer.close();


            writer = new BufferedWriter(new FileWriter(csvFileIngredients));
            writer.write("ingredients");
            writer.newLine();

            for (Recipe recipe : savedRecipes.values()) {
                String line = String.format("%s", recipe.getIngredients());
                writer.write(line);
                writer.newLine();
            }

            writer.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

