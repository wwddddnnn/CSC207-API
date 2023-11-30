package interface_adapter.add_to_meal_plan;

import use_case.add_to_meal_plan.AddMealPlanInputBoundary;
import use_case.add_to_meal_plan.AddMealPlanInputData;

import java.io.IOException;

public class AddMealPlanController {
    final AddMealPlanInputBoundary addMealPlanInteractor;
    public AddMealPlanController(AddMealPlanInputBoundary addMealPlanInteractor) {
        this.addMealPlanInteractor = addMealPlanInteractor;
    }

    public void execute() throws IOException {
//        AddMealPlanInputData addMealPlanInputData = new AddMealPlanInputData();
//        this.addMealPlanInteractor.execute(addMealPlanInputData);
    }
}
