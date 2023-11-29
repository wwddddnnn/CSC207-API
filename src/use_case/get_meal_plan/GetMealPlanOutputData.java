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
    private ArrayList<ArrayList> day1Meals;
    private ArrayList<ArrayList> day2Meals;
    private ArrayList<ArrayList> day3Meals;
    private ArrayList<ArrayList> day4Meals;
    private ArrayList<ArrayList> day5Meals;
    private ArrayList<ArrayList> day6Meals;
    private ArrayList<ArrayList> day7Meals;

    public GetMealPlanOutputData(ArrayList<ArrayList<ArrayList>> fullMealPlanArray) {
        day1Meals = fullMealPlanArray.get(0);
        day2Meals = fullMealPlanArray.get(1);
        day3Meals = fullMealPlanArray.get(2);
        day4Meals = fullMealPlanArray.get(3);
        day5Meals = fullMealPlanArray.get(4);
        day6Meals = fullMealPlanArray.get(5);
        day7Meals = fullMealPlanArray.get(6);
    }

    public ArrayList<ArrayList> getDay1Meals() {
        return day1Meals;
    }
    public ArrayList<ArrayList> getDay2Meals() {
        return day2Meals;
    }
    public ArrayList<ArrayList> getDay3Meals() {
        return day3Meals;
    }
    public ArrayList<ArrayList> getDay4Meals() {
        return day4Meals;
    }
    public ArrayList<ArrayList> getDay5Meals() {
        return day5Meals;
    }
    public ArrayList<ArrayList> getDay6Meals() {
        return day6Meals;
    }
    public ArrayList<ArrayList> getDay7Meals() {
        return day7Meals;
    }
}
