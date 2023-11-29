package interface_adapter.get_meal_plan;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;
import use_case.get_meal_plan.GetMealPlanOutputBoundary;
import use_case.get_meal_plan.GetMealPlanOutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMealPlanPresenter implements GetMealPlanOutputBoundary {
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    private MealPlanViewModel mealPlanViewModel;

    public GetMealPlanPresenter(SearchViewModel searchViewModel,
                                ViewManagerModel viewManagerModel,
                                MealPlanViewModel mealPlanViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mealPlanViewModel = mealPlanViewModel;
    }

    public void prepareSuccessView(GetMealPlanOutputData mealPlanOD) {
        MealPlanState mealPlanState = this.mealPlanViewModel.getState();

        ArrayList<String> day1SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day1DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day2SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day2DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day3SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day3DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day4SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day4DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day5SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day5DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day6SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day6DetailedArray = new ArrayList<String>(3);

        ArrayList<String> day7SimpleArray = new ArrayList<String>(3);
        ArrayList<String> day7DetailedArray = new ArrayList<String>(3);

        for (int numMeal = 1; numMeal < 4; numMeal++) {

            //day 1
            if (!mealPlanOD.getDay1Meals().get(numMeal).isEmpty()) {

                HashMap day1HashMap = (HashMap) mealPlanOD.getDay1Meals().get(numMeal).get(0);

                String day1Simple = (String) day1HashMap.get("simpleString");
                String day1Detailed = (String) day1HashMap.get("detailedString");
                day1SimpleArray.add(day1Simple);
                day1DetailedArray.add(day1Detailed);

            } else {
                day1SimpleArray.add("");
                day1DetailedArray.add("");
            }

            //day 2
            if (!mealPlanOD.getDay2Meals().get(numMeal).isEmpty()) {

                HashMap day2HashMap = (HashMap) mealPlanOD.getDay2Meals().get(numMeal).get(0);

                String day2Simple = (String) day2HashMap.get("simpleString");
                String day2Detailed = (String) day2HashMap.get("detailedString");
                day2SimpleArray.add(day2Simple);
                day2DetailedArray.add(day2Detailed);

            } else {
                day2SimpleArray.add("");
                day2DetailedArray.add("");
            }

            //day 3
            if (!mealPlanOD.getDay3Meals().get(numMeal).isEmpty()) {

                HashMap day3HashMap = (HashMap) mealPlanOD.getDay3Meals().get(numMeal).get(0);

                String day3Simple = (String) day3HashMap.get("simpleString");
                String day3Detailed = (String) day3HashMap.get("detailedString");
                day3SimpleArray.add(day3Simple);
                day3DetailedArray.add(day3Detailed);

            } else {
                day3SimpleArray.add("");
                day3DetailedArray.add("");
            }

            //day 4
            if (!mealPlanOD.getDay4Meals().get(numMeal).isEmpty()) {

                HashMap day4HashMap = (HashMap) mealPlanOD.getDay4Meals().get(numMeal).get(0);

                String day4Simple = (String) day4HashMap.get("simpleString");
                String day4Detailed = (String) day4HashMap.get("detailedString");
                day4SimpleArray.add(day4Simple);
                day4DetailedArray.add(day4Detailed);

            } else {
                day4SimpleArray.add("");
                day4DetailedArray.add("");
            }

            //day 5
            if (!mealPlanOD.getDay5Meals().get(numMeal).isEmpty()) {

                HashMap day5HashMap = (HashMap) mealPlanOD.getDay5Meals().get(numMeal).get(0);

                String day5Simple = (String) day5HashMap.get("simpleString");
                String day5Detailed = (String) day5HashMap.get("detailedString");
                day5SimpleArray.add(day5Simple);
                day5DetailedArray.add(day5Detailed);

            } else {
                day5SimpleArray.add("");
                day5DetailedArray.add("");
            }

            //day 6
            if (!mealPlanOD.getDay6Meals().get(numMeal).isEmpty()) {

                HashMap day6HashMap = (HashMap) mealPlanOD.getDay6Meals().get(numMeal).get(0);

                String day6Simple = (String) day6HashMap.get("simpleString");
                String day6Detailed = (String) day6HashMap.get("detailedString");
                day6SimpleArray.add(day6Simple);
                day6DetailedArray.add(day6Detailed);

            } else {
                day6SimpleArray.add("");
                day6DetailedArray.add("");
            }

            //day 7
            if (!mealPlanOD.getDay7Meals().get(numMeal).isEmpty()) {

                HashMap day7HashMap = (HashMap) mealPlanOD.getDay7Meals().get(numMeal).get(0);

                String day7Simple = (String) day7HashMap.get("simpleString");
                String day7Detailed = (String) day7HashMap.get("detailedString");
                day7SimpleArray.add(day7Simple);
                day7DetailedArray.add(day7Detailed);

            } else {
                day7SimpleArray.add("");
                day7DetailedArray.add("");
            }
        }

        //update the MealPlanState
        mealPlanState.setDay1SimpleStrings(day1SimpleArray);
        mealPlanState.setDay1DetailedStrings(day1DetailedArray);

        mealPlanState.setDay2SimpleStrings(day2SimpleArray);
        mealPlanState.setDay2DetailedStrings(day2DetailedArray);

        mealPlanState.setDay3SimpleStrings(day3SimpleArray);
        mealPlanState.setDay3DetailedStrings(day3DetailedArray);

        mealPlanState.setDay4SimpleStrings(day4SimpleArray);
        mealPlanState.setDay4DetailedStrings(day4DetailedArray);

        mealPlanState.setDay5SimpleStrings(day5SimpleArray);
        mealPlanState.setDay5DetailedStrings(day5DetailedArray);

        mealPlanState.setDay6SimpleStrings(day6SimpleArray);
        mealPlanState.setDay6DetailedStrings(day6DetailedArray);

        mealPlanState.setDay7SimpleStrings(day7SimpleArray);
        mealPlanState.setDay7DetailedStrings(day7DetailedArray);


        this.mealPlanViewModel.setState(mealPlanState);
        this.mealPlanViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mealPlanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
