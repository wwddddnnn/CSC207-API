package use_case.search_recipe;
import entity.Recipe;
import data_access.SearchRecipeDataAccessObject;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    private final SearchRecipeDataAccessObject searchRecipeDAO;
    final  SearchOutputBoundary  searchDatapresenter;

    public SearchInteractor(SearchRecipeDataAccessObject searchRecipeDAO,SearchOutputBoundary  searchDatapresenter) {
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
