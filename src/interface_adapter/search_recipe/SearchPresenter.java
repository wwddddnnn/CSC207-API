package interface_adapter.search_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupState;
import use_case.search_recipe.SearchOutputBoundary;
import use_case.search_recipe.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchResultsViewModel searchResultsViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SearchResultsViewModel searchResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.searchResultsViewModel = searchResultsViewModel;
    }

    public void prepareSuccessView(SearchOutputData result){

        SearchState searchState = this.searchViewModel.getState();
        searchState.setQuery(result.getQuery());
        searchState.setCuisine(result.getCuisine());
        searchState.setMaxTime(result.getMaxTime());
        searchState.setRecipe(result.getRecipes());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchResultsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchByFilterError(error);
        searchViewModel.firePropertyChanged();
    }


}
