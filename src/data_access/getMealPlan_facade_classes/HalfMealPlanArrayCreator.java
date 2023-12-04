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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.time.temporal.ChronoUnit;

public class HalfMealPlanArrayCreator {

    public HalfMealPlanArrayCreator() {
    };

    //TODO: ApiKey used here.
    public ArrayList<ArrayList<ArrayList>> create(ArrayList<ArrayList<ArrayList>> EmptyMealPlanArrayList) {

        String username = UserInfoRetriever.getUsername();
        String userHash = UserInfoRetriever.getUserHash();
        Date date = new Date();
        long startDateEpoch = date.getTime() / 1000;
        String dateAsAString = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = null;
        //RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/mealplanner/" + username + "/week/" + dateAsAString + "?hash=" + userHash + "&apiKey=76ca5ebc0a734cc6a03fda7bb5af3b3d")
                .method("GET", body)
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            JSONArray unfilteredResponseArray = responseBody.getJSONArray("days");

            //TODO: delete this print line.
            System.out.println("THIS IS UNFILTERED: " + unfilteredResponseArray);

            if (!unfilteredResponseArray.isEmpty()) {

                for (int i = 0; i < unfilteredResponseArray.length(); i++) {

                    JSONObject dayObject = unfilteredResponseArray.getJSONObject(i);

                    //TODO: delete this print line
                    System.out.println("THIS IS DAY" + i + ": " + dayObject);

                    int dayEpochTimeOld = (int) dayObject.get("date");
                    long dayEpochTime = (long) dayEpochTimeOld;

                    //TODO: delete this print line
                    System.out.println("DAY EPOCH TIME FOR" + i + ": " + dayEpochTime);
                    System.out.println("START DATE EPOCH TIME FOR" + i + ": " + startDateEpoch);
//
//                    String date1 = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date (dayEpochTime));
//                    LocalDate date1Local = LocalDate.parse(date1);
//                    //TODO: delete this print line
//                    System.out.println(date1Local);
//
//                    String date2 = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date (startDateEpoch));
//                    LocalDate date2Local = LocalDate.parse(date2);
//                    //TODO: delete this print line
//                    System.out.println(date2Local);

                    //int numDaysAfterStartDate = (int) ChronoUnit.DAYS.between(date2Local, date1Local);


                    long numDaysAfterStartDate = ((dayEpochTime - startDateEpoch)/864000000);    //86,400 is the number of Epoch numbers of 1 day

                    JSONArray dayItemsArray = dayObject.getJSONArray("items");

                    for (int numMeals = 0; numMeals < dayItemsArray.length() && numMeals < 3; numMeals++) {
                        JSONObject mealObject = dayItemsArray.getJSONObject(numMeals);

                        JSONObject recipeJSONInfo = mealObject.getJSONObject("value");
                        Integer recipeID = recipeJSONInfo.getInt("id");

                        Integer recipeSlot = mealObject.getInt("slot");    //slot: 1 = breakfast, 2 = lunch, 3 = dinner

                        //TODO: delete this print line
                        System.out.println(numDaysAfterStartDate);
                        //TODO: delete this print line
                        System.out.println(recipeSlot);
                        EmptyMealPlanArrayList.get((int) numDaysAfterStartDate).get(recipeSlot).add(recipeID);   //numDaysAfterStartDate = index of day; recipeSlot = index of meal

                    }

                    //TODO: delete this print line.
                    System.out.println(EmptyMealPlanArrayList);
                }
            }
            return EmptyMealPlanArrayList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
