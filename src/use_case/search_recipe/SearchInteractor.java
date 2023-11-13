package use_case.search_recipe;

import entity.Recipe;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    private final SearchRecipeDataAccessInterface searchRecipeDAO;
    final SearchOutputBoundary searchDatapresenter;

    public SearchInteractor(SearchRecipeDataAccessInterface searchRecipeDAO,SearchOutputBoundary  searchDatapresenter) {
        this.searchRecipeDAO = searchRecipeDAO;
        this.searchDatapresenter = searchDatapresenter;
    }

    @Override
    public void execute(SearchInputData searchData) {
        String query = searchData.getQuery();
        List<Recipe> recipes = searchRecipeDAO.findRecipesByQuery(query);
        SearchOutputData searchOutputData = new SearchOutputData(recipes);
        searchDatapresenter.displayRecipes(searchOutputData);
    }



}
