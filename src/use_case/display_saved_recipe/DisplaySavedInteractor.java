package use_case.display_saved_recipe;

import interface_adapter.search_recipe.SearchedRecipe;

public class DisplaySavedInteractor implements DisplaySavedInputBoundary {
    final DisplaySavedDataAccessInterface displaySavedDAO;
    final DisplaySavedOutputBoundary displaySavedPresenter;

    public DisplaySavedInteractor(DisplaySavedDataAccessInterface displaySavedDAO, DisplaySavedOutputBoundary displaySavedPresenter) {
        this.displaySavedDAO = displaySavedDAO;
        this.displaySavedPresenter = displaySavedPresenter;
    }

    @Override
    public void execute() {
        SearchedRecipe[] searchedRecipes = this.displaySavedDAO.getSavedRecipes();
        DisplaySavedOutputData displaySavedOutputData = new DisplaySavedOutputData(searchedRecipes);
        this.displaySavedPresenter.prepareSuccessView(displaySavedOutputData);
    }
}
