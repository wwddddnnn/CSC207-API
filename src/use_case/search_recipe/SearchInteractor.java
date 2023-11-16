package use_case.search_recipe;

import entity.Recipe;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    private final SearchRecipeDataAccessInterface searchRecipeDAO;
    final SearchOutputBoundary searchDataPresenter;

    public SearchInteractor(SearchRecipeDataAccessInterface searchRecipeDAO,SearchOutputBoundary searchDataPresenter) {
        this.searchRecipeDAO = searchRecipeDAO;
        this.searchDataPresenter = searchDataPresenter;
    }

    @Override
    public void execute(SearchInputData searchData) {
        String query = searchData.getQuery();
        String cuisine = searchData.getCuisine();
        List<Recipe> recipes = searchRecipeDAO.findRecipesByQuery(query);

        if (recipes.isEmpty()) {
            searchDataPresenter.prepareFailView("No recipes found.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(recipes, cuisine, query);
            searchDataPresenter.prepareSuccessView(searchOutputData);
        }
    }

}
