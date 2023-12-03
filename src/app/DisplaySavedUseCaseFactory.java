package app;

import data_access.SaveDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_saved_recipe.DisplaySavedController;
import interface_adapter.display_saved_recipe.DisplaySavedPresenter;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import use_case.display_saved_recipe.DisplaySavedInputBoundary;
import use_case.display_saved_recipe.DisplaySavedInteractor;
import use_case.display_saved_recipe.DisplaySavedOutputBoundary;

public class DisplaySavedUseCaseFactory {
    private DisplaySavedUseCaseFactory(){}

    public static DisplaySavedController createDisplaySavedUseCase(DisplaySavedViewModel displaySavedViewModel,
                                                                   ViewManagerModel viewManagerModel,
                                                                   SaveDataAccessObject saveDAO){
        DisplaySavedOutputBoundary displaySavedPresenter = new DisplaySavedPresenter(displaySavedViewModel, viewManagerModel);
        DisplaySavedInputBoundary displaySavedInteractor = new DisplaySavedInteractor(saveDAO, displaySavedPresenter);
        return new DisplaySavedController(displaySavedInteractor);
    }
}
