package interface_adapter.save_recipe;

import interface_adapter.ViewManagerModel;
import use_case.save_recipe.SaveOutputBoundary;
import use_case.save_recipe.SaveOutputData;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManagerModel viewManagerModel;
    private SaveSuccessViewModel saveSuccessViewModel;
    public SavePresenter(ViewManagerModel viewManagerModel, SaveViewModel saveViewModel,
                         SaveSuccessViewModel saveSuccessViewModel){
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.saveSuccessViewModel = saveSuccessViewModel;
    }


    @Override
    public void prepareSuccessView() {
        SaveState saveState = saveViewModel.getState();
        this.saveSuccessViewModel.setState(saveState);
        this.saveSuccessViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

    }
}
