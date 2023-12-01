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
    public HashMap<String, Object> getResult() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/users/connect?apiKey=41d15208fa06404c963c39772afe6584")
                .method("POST", body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            return convertJsonToHashMap(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            // Return an empty HashMap in case of IO issues
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


