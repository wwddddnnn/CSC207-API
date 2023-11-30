package interface_adapter.get_meal_plan;

import interface_adapter.search_recipe.SearchState;

import java.util.ArrayList;

public class MealPlanState {
    private ArrayList<ArrayList<ArrayList>> mealPlanResult = null;

    public MealPlanState(MealPlanState copy){
        mealPlanResult = copy.mealPlanResult;
    }
    public MealPlanState(){}

    //set methods
    public void setMealPlanResult(ArrayList<ArrayList<ArrayList>> mpResult){mealPlanResult = mpResult;}

    //get methods
    public ArrayList<ArrayList<ArrayList>> getMealPlanResult(){return mealPlanResult;}

}
