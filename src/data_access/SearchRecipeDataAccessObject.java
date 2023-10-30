package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.signup.SignupInputData;

public class SearchRecipeDataAccessObject {

    public void getByCuisine(String cuisine) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/complexSearch?apiKey=c8eb3493121949b592c890859e2a5240&query=pasta&maxFat=25&number=2")
                .get()
                .build();

        Response response = client.newCall(request).execute();

    }
}
