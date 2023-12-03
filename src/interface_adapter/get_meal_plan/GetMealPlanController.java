package interface_adapter.get_meal_plan;

import use_case.get_meal_plan.GetMealPlanInputBoundary;

public class GetMealPlanController {
    private final GetMealPlanInputBoundary getMealPlanInteractor;
    public GetMealPlanController(GetMealPlanInputBoundary getMealPlanInteractor) {
        this.getMealPlanInteractor = getMealPlanInteractor;
    }

    public void execute() {
        getMealPlanInteractor.execute();
    }
}
