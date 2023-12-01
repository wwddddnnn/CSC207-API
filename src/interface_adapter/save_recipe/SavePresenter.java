package interface_adapter.save_recipe;

import interface_adapter.ViewManagerModel;
import use_case.save_recipe.SaveOutputBoundary;
import use_case.save_recipe.SaveOutputData;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManagerModel viewManagerModel;
    public SavePresenter(ViewManagerModel viewManagerModel, SaveViewModel saveViewModel){
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        SaveState saveState = saveViewModel.getState();
        this.saveViewModel.setState(saveState);
        this.saveViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        SaveState saveState = saveViewModel.getState();
        saveState.setRecipeError(error);
        this.saveViewModel.firePropertyChanged();
    }
}
