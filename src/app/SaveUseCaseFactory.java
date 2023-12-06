package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SavePresenter;
import interface_adapter.save_recipe.SaveViewModel;

import use_case.save_recipe.SaveInputBoundary;
import use_case.save_recipe.SaveInteractor;
import use_case.save_recipe.SaveOutputBoundary;
import use_case.save_recipe.SaveRecipeDataAccessInterface;



public class SaveUseCaseFactory {
    private SaveUseCaseFactory(){}

    public static SaveController createSaveRecipeUseCase(ViewManagerModel viewManagerModel, SaveRecipeDataAccessInterface saveRecipeDataAccessInterface,

                                                          SaveViewModel saveViewModel) {
        SaveOutputBoundary savePresenter = new SavePresenter(viewManagerModel, saveViewModel);
        SaveInputBoundary saveInteractor = new SaveInteractor(saveRecipeDataAccessInterface, savePresenter);
        return new SaveController(saveInteractor);

    }
}
