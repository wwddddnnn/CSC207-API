package app;

import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.get_meal_plan.GetMealPlanController;
import interface_adapter.get_meal_plan.GetMealPlanPresenter;
import interface_adapter.get_meal_plan.MealPlanViewModel;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchPresenter;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;

import use_case.get_meal_plan.GetMealPlanDataAccessInterface;
import use_case.get_meal_plan.GetMealPlanInputBoundary;
import use_case.get_meal_plan.GetMealPlanInteractor;
import use_case.get_meal_plan.GetMealPlanOutputBoundary;

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



    public static SearchView create(ViewManagerModel viewManagerModel,
                                    SearchViewModel searchViewModel,
                                    SearchResultsViewModel searchResultsViewModel,
                                    SearchRecipeDataAccessInterface searchRecipeDataAccessObject,
                                    MealPlanViewModel mealPlanViewModel,
                                    DisplaySavedViewModel displaySavedViewModel,
                                    GetMealPlanDataAccessInterface getMealPlanDataAccessObject) {
        SearchController searchController = createSearchRecipeUseCase(viewManagerModel, searchViewModel, searchResultsViewModel, searchRecipeDataAccessObject);
        GetMealPlanController getMealPlanController = createGetMealPlanUseCase(viewManagerModel, searchViewModel, mealPlanViewModel, getMealPlanDataAccessObject);
        return new SearchView(searchController, searchViewModel, getMealPlanController, displaySavedViewModel, viewManagerModel);

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

    private static GetMealPlanController createGetMealPlanUseCase(ViewManagerModel viewManagerModel,
                                                                  SearchViewModel searchViewModel,
                                                                  MealPlanViewModel mealPlanViewModel,
                                                                  GetMealPlanDataAccessInterface getMealPlanDataAccessObject){
        GetMealPlanOutputBoundary getMealPlanOutputBoundary = new GetMealPlanPresenter(searchViewModel, viewManagerModel,mealPlanViewModel);

        GetMealPlanInputBoundary getMealPlanInteractor = new GetMealPlanInteractor(getMealPlanDataAccessObject, getMealPlanOutputBoundary);

        return new GetMealPlanController(getMealPlanInteractor);
    }
}
