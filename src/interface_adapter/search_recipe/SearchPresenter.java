package interface_adapter.search_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchState;
import use_case.search_recipe.SearchOutputBoundary;
import use_case.search_recipe.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchResultsViewModel searchResultsViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,
                           SearchViewModel searchViewModel,
                           SearchResultsViewModel searchResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.searchResultsViewModel = searchResultsViewModel;
    }

    public void prepareSuccessView(SearchOutputData result){
        // on success, switch to the search result view.
        SearchState searchState = this.searchViewModel.getState();
        searchState.setRecipe(result.getRecipes());
        this.searchResultsViewModel.setState(searchState);
        this.searchResultsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchResultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchByFilterError(error);
        searchViewModel.firePropertyChanged();
    }


}
