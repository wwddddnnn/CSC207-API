package use_case.add_to_meal_plan;

import java.io.IOException;

public interface AddMealPlanDataAccessInterface {
    // return "success" if add successfully, or error information otherwise.
    String addMealPlan(String[] userInfo, String[] addInfo, String recipeId) throws IOException;
    // userInfo = {username, hash}, addInfo = {date, slot, position}
}
