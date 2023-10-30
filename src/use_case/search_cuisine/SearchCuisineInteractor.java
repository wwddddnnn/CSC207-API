package use_case.search_cuisine;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.signup.SignupInputData;

public class SearchCuisineInteractor {

    public void execute(SignupInputData searchCuisineInputData) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/complexSearch/diet/inputda")
                .get()
                .build();

        Response response = client.newCall(request).execute();
    }

}
