package use_case.save_recipe;

import use_case.search_recipe.SearchInputBoundary;
import use_case.search_recipe.SearchInputData;
import use_case.search_recipe.SearchOutputBoundary;

public class SaveInteractor implements SearchInputBoundary {
    final SaveRecipeDataAccessInterface saveRecipeDAO;
    final SaveOutputBoundary saveDataPresenter;

    public SaveInteractor(SaveRecipeDataAccessInterface saveRecipeDAO, SaveOutputBoundary saveDataPresenter){
        this.saveRecipeDAO = saveRecipeDAO;
        this.saveDataPresenter = saveDataPresenter;
    }
    @Override
    public void execute(SearchInputData searchData) {

    }
}
