package interface_adapter.display_saved_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import use_case.display_saved_recipe.DisplaySavedOutputBoundary;
import use_case.display_saved_recipe.DisplaySavedOutputData;

public class DisplaySavedPresenter implements DisplaySavedOutputBoundary {
    private final DisplaySavedViewModel displaySavedViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplaySavedPresenter(DisplaySavedViewModel displaySavedViewModel, ViewManagerModel viewManagerModel) {
        this.displaySavedViewModel = displaySavedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DisplaySavedOutputData displaySavedOutputData) {
        this.displaySavedViewModel.setSearchedRecipes(displaySavedOutputData.getSearchedRecipes());
        this.displaySavedViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(this.displaySavedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
