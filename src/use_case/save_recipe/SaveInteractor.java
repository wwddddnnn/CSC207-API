package use_case.save_recipe;

import data_access.SaveDataAccessObject;
import entity.RecipeFactory;
import interface_adapter.save_recipe.SavePresenter;


public class SaveInteractor implements SaveInputBoundary {
    final SaveRecipeDataAccessInterface saveRecipeDAO;
    final SaveOutputBoundary saveDataPresenter;

    public SaveInteractor(SaveRecipeDataAccessInterface saveRecipeDAO, SaveOutputBoundary saveDataPresenter){
        this.saveRecipeDAO = saveRecipeDAO;
        this.saveDataPresenter = saveDataPresenter;
    }
    @Override
    public void execute(SaveInputData saveInputData) {
        if (saveRecipeDAO.existsById(saveInputData.getRecipeId())){
            saveDataPresenter.prepareFailView("Recipe already exists!");
        }
        else{
            saveRecipeDAO.save(saveInputData.getSavedRecipe());
            saveDataPresenter.prepareSuccessView();
        }
    }

}
