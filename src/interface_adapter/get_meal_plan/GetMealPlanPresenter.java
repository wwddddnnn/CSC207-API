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

        //update the MealPlanState
        mealPlanState.setMealPlanResult(mealPlanOD.getMealPlanResult());

        this.mealPlanViewModel.setState(mealPlanState);
        this.mealPlanViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mealPlanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
