package use_case.get_meal_plan;

import java.util.ArrayList;

public class GetMealPlanInteractor implements GetMealPlanInputBoundary{
    final GetMealPlanDataAccessInterface getMealPlanDAO;
    final GetMealPlanOutputBoundary getMealPlanPresenter;
    public GetMealPlanInteractor(GetMealPlanDataAccessInterface getMealPlanDAO, GetMealPlanOutputBoundary getMealPlanPresenter) {
        this.getMealPlanDAO = getMealPlanDAO;
        this.getMealPlanPresenter = getMealPlanPresenter;
    }
    public void execute() {
        ArrayList<ArrayList<ArrayList>> fullMealPlanArray = getMealPlanDAO.getMealPlan();
        GetMealPlanOutputData getMealPlanOutputData = new GetMealPlanOutputData(fullMealPlanArray);
        getMealPlanPresenter.prepareSuccessView(getMealPlanOutputData);
    }
}
