package app;

import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchPresenter;
import interface_adapter.search_recipe_results.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchViewModel;
import use_case.search_recipe.SearchInputBoundary;
import use_case.search_recipe.SearchInteractor;
import use_case.search_recipe.SearchOutputBoundary;
import use_case.search_recipe.SearchRecipeDataAccessInterface;
import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import interface_adapter.*;
import view.SearchView;

public class SearchRecipeUseCaseFactory {
    private SearchRecipeUseCaseFactory() {}

    public static SearchView create(SearchViewModel searchViewModel,
                                    SearchController searchController) {
        return new SearchView(searchController, searchViewModel);
    }



    public static SearchController createSearchRecipeUseCase(ViewManagerModel viewManagerModel,
                                                              SearchViewModel searchViewModel,
                                                              SearchResultsViewModel searchResultsViewModel,
                                                              SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, searchResultsViewModel);
        //SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        RecipeFactory recipeFactory = new CommonRecipeFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                searchRecipeDataAccessObject, searchOutputBoundary);
        //SearchInputBoundary searchInteractor = new SearchInteractor(
        //                searchRecipeDataAccessObject, searchOutputBoundary, recipeFactory);

        return new SearchController(searchInteractor);
    }
}
