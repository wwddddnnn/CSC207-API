package data_access.getMealPlan_facade_classes;

/* This class takes in an EmptyMealPlan (which has already been created with a start date), and uses
Spoonacular's getMealPlanWeek call to retrieve information on the planned meals, to fill in
the ArrayLists that represent each meal with the meal recipe's ID number. It should return an
ArrayList that looks like this:

    [
    [["2023-11-28, Tuesday"], [243], [], []],
    [["2023-11-29, Wednesday"], [], [1523], []],
    [["2023-11-30, Thursday"], [], [], []],
    [["2023-12-01, Friday"], [], [], []],
    [["2023-12-02, Saturday"], [75], [], []],
    [["2023-12-03, Sunday"], [], [], []],
    [["2023-12-04, Monday"], [], [], []],
    ]

 */

import okhttp3.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HalfMealPlanArrayCreator {

    private final String username;
    private final String userHash;
    private final LocalDate startDate;
    private final int startDateEpoch;

    public HalfMealPlanArrayCreator(String username, String userHash, LocalDate startDate, int startDateEpoch) {
        this.username = username;
        this.userHash = userHash;
        this.startDate = startDate;
        this.startDateEpoch = startDateEpoch;
    };

    //TODO: ApiKey used here.
    public ArrayList<ArrayList<ArrayList>> create(ArrayList<ArrayList<ArrayList>> EmptyMealPlanArrayList) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = null;
        //RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/mealplanner/" + username + "/week/" + startDate.toString() + "?hash=" + userHash + "&apiKey=63a546e1f4084beda46d6cf3ce87b550")
                .method("GET", body)
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray unfilteredResponseArray = responseBody.getJSONArray("days");

            if (!unfilteredResponseArray.isEmpty()) {

                for (int i = 0; i < unfilteredResponseArray.length(); i++) {

                    JSONObject dayObject = unfilteredResponseArray.getJSONObject(i);

                    int dayEpochTime = (int) dayObject.get("date");
                    int numDaysAfterStartDate = ((dayEpochTime - startDateEpoch)/86400);    //86,400 is the number of Epoch numbers of 1 day

                    JSONArray dayItemsArray = dayObject.getJSONArray("items");

                    for (int numMeals = 0; numMeals < dayItemsArray.length(); numMeals++) {
                        JSONObject mealObject = dayItemsArray.getJSONObject(numMeals);
                        Integer recipeID = mealObject.getInt("id");
                        Integer recipeSlot = mealObject.getInt("slot");    //slot: 1 = breakfast, 2 = lunch, 3 = dinner

                        EmptyMealPlanArrayList.get(numDaysAfterStartDate).get(recipeSlot+1).add(recipeID);   //numDaysAfterStartDate = index of day; recipeSlot = index of meal

                    }
                }
            }
            return EmptyMealPlanArrayList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
