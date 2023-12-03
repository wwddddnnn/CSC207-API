package app;

import data_access.SaveDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanPresenter;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.display_saved_recipe.DisplaySavedController;
import interface_adapter.display_saved_recipe.DisplaySavedPresenter;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import use_case.add_to_meal_plan.AddMealPlanDataAccessInterface;
import use_case.add_to_meal_plan.AddMealPlanInputBoundary;
import use_case.add_to_meal_plan.AddMealPlanInteractor;
import use_case.add_to_meal_plan.AddMealPlanOutputBoundary;
import use_case.display_saved_recipe.DisplaySavedInputBoundary;
import use_case.display_saved_recipe.DisplaySavedInteractor;
import use_case.display_saved_recipe.DisplaySavedOutputBoundary;

public class AddToMealPlanUseCaseFactory {
    private AddToMealPlanUseCaseFactory(){}

    public static AddMealPlanController createAddToMealPlanSavedUseCase(AddMealPlanViewModel addMealPlanViewModel,
                                                                        AddMealPlanDataAccessInterface addMealPlanDAO,
                                                                        ViewManagerModel viewManagerModel){
        AddMealPlanOutputBoundary addMealPlanPresenter = new AddMealPlanPresenter(addMealPlanViewModel, viewManagerModel);
        AddMealPlanInputBoundary addMealPlanInteractor = new AddMealPlanInteractor(addMealPlanDAO, addMealPlanPresenter);
        return new AddMealPlanController(addMealPlanInteractor);
    }
}
