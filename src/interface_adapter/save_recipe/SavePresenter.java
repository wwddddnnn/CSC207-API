package interface_adapter.save_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import use_case.save_recipe.SaveOutputBoundary;
import use_case.save_recipe.SaveOutputData;
import view.DisplayRecipeView;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManagerModel viewManagerModel;
    public SavePresenter(ViewManagerModel viewManagerModel, SaveViewModel saveViewModel){
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        System.out.println("prepareSuccessView");
        this.saveViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("prepareFailView");
        SaveState saveState = saveViewModel.getState();
        saveState.setRecipeError(error);
        this.saveViewModel.firePropertyChanged();
    }
}
