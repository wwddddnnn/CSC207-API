package use_case.save_recipe;

import data_access.SaveDataAccessObject;
import entity.RecipeFactory;
import interface_adapter.save_recipe.SavePresenter;


public class SaveInteractor implements SaveInputBoundary {
    final SaveRecipeDataAccessInterface saveRecipeDAO;
    final SaveOutputBoundary saveDataPresenter;
    final RecipeFactory recipeFactory;

    public SaveInteractor(SaveRecipeDataAccessInterface saveRecipeDAO, SaveOutputBoundary saveDataPresenter,
                          RecipeFactory recipeFactory){
        this.saveRecipeDAO = saveRecipeDAO;
        this.saveDataPresenter = saveDataPresenter;
        this.recipeFactory = recipeFactory;
    }
    @Override
    public void execute(SaveInputData saveInputData) {
        if (saveRecipeDAO.existsByName(saveInputData.getRecipeName())){
            saveDataPresenter.prepareFailView("Recipe already exists!");
        }
        else{
            saveRecipeDAO.save(saveInputData.getSavedRecipe());
            saveDataPresenter.prepareSuccessView();
        }
    }

}
