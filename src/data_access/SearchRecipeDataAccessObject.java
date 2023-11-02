package data_access;

import entity.Recipe;
import okhttp3.OkHttpClient;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;
import use_case.signup.SignupInputData;

import java.io.IOException;

public class SearchRecipeDataAccessObject {

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

                //get recipe 2
                JSONObject recipe2Info = new JSONObject(fullResultsArray.getJSONObject(1));
                Recipe recipe2 = Recipe.builder()
                        .id(recipe2Info.getInt("id"))
                        .name(recipe2Info.getString("title"))
                        .build();
                recipe2.addImage(recipe2Info.getString("image"), recipe2Info.getString("imageType"));

                //get recipe 3
                JSONObject recipe3Info = new JSONObject(fullResultsArray.getJSONObject(2));
                Recipe recipe3 = Recipe.builder()
                        .id(recipe3Info.getInt("id"))
                        .name(recipe3Info.getString("title"))
                        .build();
                recipe3.addImage(recipe3Info.getString("image"), recipe3Info.getString("imageType"));

                //get recipe 4
                JSONObject recipe4Info = new JSONObject(fullResultsArray.getJSONObject(3));
                Recipe recipe4 = Recipe.builder()
                        .id(recipe4Info.getInt("id"))
                        .name(recipe4Info.getString("title"))
                        .build();
                recipe4.addImage(recipe4Info.getString("image"), recipe4Info.getString("imageType"));

                //get recipe 5
                JSONObject recipe5Info = new JSONObject(fullResultsArray.getJSONObject(4));
                Recipe recipe5 = Recipe.builder()
                        .id(recipe5Info.getInt("id"))
                        .name(recipe5Info.getString("title"))
                        .build();
                recipe5.addImage(recipe5Info.getString("image"), recipe5Info.getString("imageType"));

            } else {
                throw new RuntimeException("fail");
            }

            /*String foodName = responseBody.getString("title");
            System.out.println(foodName);*/
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);

    }
}}
