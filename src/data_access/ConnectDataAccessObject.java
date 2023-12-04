package data_access;

import entity.Recipe;
import entity.RecipeTag;
import entity.RecipeFactory;
import entity.RecipeTagFactory;
import okhttp3.*;
import use_case.search_recipe.SearchRecipeDataAccessInterface;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import use_case.connect.ConnectDataAccessInterface;

public class ConnectDataAccessObject implements ConnectDataAccessInterface {
    public HashMap<String, Object> getResult(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Creating JSON String from the username
        String jsonBody = "{\r\n \"username\": \"" + username + "\"\r\n}";

        // Setting MediaType to JSON
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/users/connect?apiKey=759b1b1c8a9e47ff88aed913c15f50ac")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            return convertJsonToHashMap(jsonObject);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            // Return an empty HashMap in case of IO issues or JSON parsing errors
            return new HashMap<>();
        }
    }
    private HashMap<String, Object> convertJsonToHashMap(JSONObject jsonObject) {
        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("username", jsonObject.optString("username"));
        resultMap.put("spoonacularPassword", jsonObject.optString("spoonacularPassword"));
        resultMap.put("hash", jsonObject.optString("hash"));

        return resultMap;
    }


}


