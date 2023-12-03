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

import app.UserInfoRetriever;
import okhttp3.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class HalfMealPlanArrayCreator {

    public HalfMealPlanArrayCreator() {
    };

    //TODO: ApiKey used here.
    public ArrayList<ArrayList<ArrayList>> create(ArrayList<ArrayList<ArrayList>> EmptyMealPlanArrayList) {

        String username = UserInfoRetriever.getUsername();
        String userHash = UserInfoRetriever.getUserHash();
        Date date = new Date();
        int startDateEpoch = (int) date.getTime() / 1000;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = null;
        //RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/mealplanner/" + username + "/week/" + date.toString() + "?hash=" + userHash + "&apiKey=e7877dbada2b46aeb588e7418550c78f")
                .method("GET", body)
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray unfilteredResponseArray = responseBody.getJSONArray("days");
            System.out.println(unfilteredResponseArray);

            if (!unfilteredResponseArray.isEmpty()) {

                for (int i = 0; i < unfilteredResponseArray.length(); i++) {

                    JSONObject dayObject = unfilteredResponseArray.getJSONObject(i);

                    int dayEpochTime = (int) dayObject.get("date");
                    System.out.println("dayEpochTime" + dayEpochTime);
                    System.out.println("startDateEpoch" + startDateEpoch);
                    int numDaysAfterStartDate = ((dayEpochTime - startDateEpoch)/86400);    //86,400 is the number of Epoch numbers of 1 day

                    JSONArray dayItemsArray = dayObject.getJSONArray("items");
                    System.out.println(dayItemsArray);

                    for (int numMeals = 0; numMeals < dayItemsArray.length(); numMeals++) {
                        JSONObject mealObject = dayItemsArray.getJSONObject(numMeals);
                        Integer recipeID = mealObject.getInt("id");
                        Integer recipeSlot = mealObject.getInt("slot");    //slot: 1 = breakfast, 2 = lunch, 3 = dinner
                        System.out.println(numDaysAfterStartDate);
                        System.out.println(recipeSlot);
                        EmptyMealPlanArrayList.get(numDaysAfterStartDate).get(recipeSlot+1).add(recipeID);   //numDaysAfterStartDate = index of day; recipeSlot = index of meal

                    }
                    System.out.println(EmptyMealPlanArrayList);
                }
            }
            return EmptyMealPlanArrayList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
