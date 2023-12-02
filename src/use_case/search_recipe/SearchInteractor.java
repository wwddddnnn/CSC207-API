package use_case.search_recipe;

import entity.Recipe;
import interface_adapter.search_recipe.SearchedRecipe;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchInteractor implements SearchInputBoundary{
    final SearchRecipeDataAccessInterface searchRecipeDAO;
    final SearchOutputBoundary searchDataPresenter;

    public SearchInteractor(SearchRecipeDataAccessInterface searchRecipeDAO, SearchOutputBoundary searchDataPresenter) {
        this.searchRecipeDAO = searchRecipeDAO;
        this.searchDataPresenter = searchDataPresenter;
    }

    @Override
    public void execute(SearchInputData searchData) {
        String query = searchData.getQuery();
        String cuisine = searchData.getCuisine();
        String maxTime = searchData.getMaxTime();
        String offsetString = searchData.getOffsetString();

        HashMap<String, String> filtersMap = new HashMap<>();
        if (!query.isEmpty()) {
            filtersMap.put("query", query);
        }
        if (!cuisine.isEmpty()) {
            filtersMap.put("cuisine", cuisine);
        }
        if (!maxTime.isEmpty()) {
            filtersMap.put("maxTime", maxTime);
        }
        filtersMap.put("offset", offsetString);

        //if no search keywords are entered, prepareFailView
        if (query.isEmpty() && cuisine.isEmpty() && maxTime.isEmpty()) {
            searchDataPresenter.prepareFailView("No search keywords entered.");
        }

        ArrayList<Recipe> recipesEntity = searchRecipeDAO.getByFilters(filtersMap);
        ArrayList<SearchedRecipe> recipes = new ArrayList<>();
        for (Recipe i: recipesEntity) recipes.add(new SearchedRecipe(i));

        int totalAmount = searchRecipeDAO.getAmountByFilter();
//        System.out.println(totalAmount + " in Interactor");

        //if no recipes are found, prepareFailView; else, prepareSuccessView
        if (recipesEntity.isEmpty()) {
            searchDataPresenter.prepareFailView("No recipes found.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(recipes, cuisine, query, maxTime, totalAmount);
            searchDataPresenter.prepareSuccessView(searchOutputData);
        }
    }

}
