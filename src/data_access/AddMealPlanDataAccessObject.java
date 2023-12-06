package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_to_meal_plan.AddMealPlanDataAccessInterface;

import java.io.IOException;

public class AddMealPlanDataAccessObject implements AddMealPlanDataAccessInterface {
    @Override
    public String addMealPlan(String[] userInfo, String[] addInfo, String recipeId) throws IOException {
        // userInfo = {username, hash}, addInfo = {date, slot}

        System.out.println(userInfo[0] + "," + userInfo[1]);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"date\": " + addInfo[0] + ",\n    \"slot\": " + addInfo[1] + ",\n    \"position\": 0,\n    \"type\": \"RECIPE\",\n    \"value\": {\n        \"id\": " + recipeId + "\n    }\n}");
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/mealplanner/" + userInfo[0] + "/items?hash=" + userInfo[1] + "&apiKey=416a4d1c29a446b1b80bae4df5ca3083")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getString("status").equals("failure")) {
                return "Failed to add to meal plan! \n" + responseBody.getString("message");
            }
            return "Add to meal plan successfully!";
        } catch ( IOException e) {
            return "Failed to add to meal plan! \n Some exceptions occur.";
        }
    }
}
