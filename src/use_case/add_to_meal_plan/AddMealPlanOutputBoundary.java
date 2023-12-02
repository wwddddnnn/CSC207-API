package use_case.add_to_meal_plan;

public interface AddMealPlanOutputBoundary {
//    void prepareSuccessView(AddMealPlanOutputData addMealPlanOutputData);
    void prepareSuccessView(String message);
//    void prepareFailView(AddMealPlanOutputData addMealPlanOutputData);
    void prepareFailView(String error);
}
