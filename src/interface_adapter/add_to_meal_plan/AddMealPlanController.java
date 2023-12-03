package interface_adapter.add_to_meal_plan;

import use_case.add_to_meal_plan.AddMealPlanInputBoundary;
import use_case.add_to_meal_plan.AddMealPlanInputData;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMealPlanController {
    final AddMealPlanInputBoundary addMealPlanInteractor;
    public AddMealPlanController(AddMealPlanInputBoundary addMealPlanInteractor) {
        this.addMealPlanInteractor = addMealPlanInteractor;
    }
    public void execute(String username, String hash, String dateString, String slotString, String recipeId) throws IOException {
        System.out.println("addController working");
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (slotString.equals("Breakfast")) slotString = "1";
        if (slotString.equals("Lunch")) slotString = "2";
        if (slotString.equals("Dinner")) slotString = "3";
        AddMealPlanInputData addMealPlanInputData = new AddMealPlanInputData(username, hash, date, slotString, recipeId);
        this.addMealPlanInteractor.execute(addMealPlanInputData);
    }
}
