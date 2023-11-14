package interface_adapter.search_recipe;

import interface_adapter.ViewManagerModel;
import use_case.search_recipe.SearchOutputBoundary;
import use_case.search_recipe.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    public void prepareSuccessView(SearchOutputData result){

        SearchState searchState = this.searchViewModel.getState();
        searchState.setQuery(result.getQuery());
        searchState.setCuisine(result.getCuisine());
        searchState.setRecipe(result.getRecipes());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();
    }


}
