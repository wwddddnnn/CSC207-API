package use_case.get_meal_plan;

import java.util.ArrayList;

/* GetMealPlanOutputData's attributes are each ArrayLists that represent the meals of each day.

They look like this:

day1Meals = [
            ["2023-11-28, Tuesday"],
            [{“simpleString”: “Banana Oatmeal”, “detailedString”: “Name: Banana Oatmeal, etc.”}],
            [{“simpleString”: “Shrimp Salad”, “detailedString”: “Name: Shrimp Salad, etc.”}],
            []
            ]

day2Meals = [
            ["2023-11-29, Wednesday"],
            [],
            [],
            []
            ]

 */

public class GetMealPlanOutputData {
    private ArrayList<ArrayList<ArrayList>> mealPlanResult;

    public GetMealPlanOutputData(ArrayList<ArrayList<ArrayList>> fullMealPlanArray) {
        mealPlanResult = fullMealPlanArray;
    }

    public ArrayList<ArrayList<ArrayList>> getMealPlanResult() {
        return mealPlanResult;
    }

}
