package interface_adapter.search_recipe;
import use_case.search_recipe.SearchInputBoundary;
import use_case.search_recipe.SearchInputData;


public class SearchController {
    private final SearchInputBoundary recipesSearchUseCaseInteractor;

    public SearchController(SearchInputBoundary recipesSearchUseCaseInteractor) {
        this.recipesSearchUseCaseInteractor = recipesSearchUseCaseInteractor;
    }

    public void execute(String query, String cuisine) {
        SearchInputData searchData = new SearchInputData(query, cuisine);
        recipesSearchUseCaseInteractor.execute(searchData);
    }
}