package app;

import data_access.SearchRecipeDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchPresenter;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.search_recipe.SearchInputBoundary;
import use_case.search_recipe.SearchInteractor;
import use_case.search_recipe.SearchOutputBoundary;
import use_case.search_recipe.SearchRecipeDataAccessInterface;
import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.RecipeView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SearchRecipeUseCaseFactory {
    private SearchRecipeUseCaseFactory() {}

    public static RecipeView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {
        SearchController searchController = createSearchRecipeUseCase(viewManagerModel, searchViewModel, searchRecipeDataAccessObject);
        return new RecipeView(searchController, searchViewModel);
    }

    private static SearchController createSearchRecipeUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel);
        //SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        RecipeFactory recipeFactory = new CommonRecipeFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                searchRecipeDataAccessObject, searchOutputBoundary, recipeFactory);

        return new SearchController(searchInteractor);
    }
}
