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

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n" +
//                "    \"date\": " + addInfo[0] + ",\n" +
//                "    \"slot\": " + addInfo[1] + ",\n" +
//                "    \"position\": 0,\n" +
//                "    \"type\": \"RECIPE\",\n" +
//                "    \"value\": {\n" +
//                "        \"id\": " + recipeId + "\n" +
//                "    }\n" +
//                "}");
//        String requestURL = "https://api.spoonacular.com/mealplanner/" + userInfo[0] + "/items?hash=" + userInfo[1] + "apiKey=b1885373924b41d9961c057265b15ed9";
//
//        Request request = new Request.Builder()
//                .url(requestURL)
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"date\": " + addInfo[0] + ",\n    \"slot\": " + addInfo[1] + ",\n    \"position\": 0,\n    \"type\": \"RECIPE\",\n    \"value\": {\n        \"id\": " + recipeId + "\n    }\n}");
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/mealplanner/" + userInfo[0] + "/items?hash="+ userInfo[1] +"&apiKey=76ca5ebc0a734cc6a03fda7bb5af3b3d")
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
