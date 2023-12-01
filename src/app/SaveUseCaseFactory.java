package app;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SavePresenter;
import interface_adapter.save_recipe.SaveViewModel;
import use_case.save_recipe.SaveInputBoundary;
import use_case.save_recipe.SaveInteractor;
import use_case.save_recipe.SaveOutputBoundary;
import use_case.save_recipe.SaveRecipeDataAccessInterface;
import view.SaveView;

public class SaveUseCaseFactory {
    private SaveUseCaseFactory(){

    }
    public static SaveView create(ViewManagerModel viewManagerModel, SaveRecipeDataAccessInterface saveRecipeDataAccessInterface,
                                  SaveViewModel saveViewModel, Recipe recipe){
        SaveController saveController = createSaveRecipeUseCase(viewManagerModel, saveRecipeDataAccessInterface, saveViewModel);
        return new SaveView(recipe, saveController, saveViewModel, viewManagerModel);
    }

    private static SaveController createSaveRecipeUseCase(ViewManagerModel viewManagerModel, SaveRecipeDataAccessInterface saveRecipeDataAccessInterface,
                                                          SaveViewModel saveViewModel) {
        SaveOutputBoundary savePresenter = new SavePresenter(viewManagerModel, saveViewModel);
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        SaveInputBoundary saveInteractor = new SaveInteractor(saveRecipeDataAccessInterface, savePresenter, recipeFactory);
        return new SaveController(saveInteractor);

    }
}
