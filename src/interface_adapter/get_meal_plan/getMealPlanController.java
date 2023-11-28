package interface_adapter.get_meal_plan;

public class getMealPlanController {
    private final GetMealPlanInputBoundary getMealPlanInteractor;
    public getMealPlanController(GetMealPlanInputBoundary getMealPlanInteractor) {
        this.getMealPlanInteractor = getMealPlanInteractor;
    }

    public void execute() {
        getMealPlanInteractor.execute();
    }
}
