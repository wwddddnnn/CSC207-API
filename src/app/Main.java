package app;

import data_access.AddMealPlanDataAccessObject;
import data_access.GetMealPlanDataAccessObject;
import interface_adapter.get_meal_plan.MealPlanViewModel;
import data_access.SaveDataAccessObject;

import data_access.SearchRecipeDataAccessObject;
import data_access.ConnectDataAccessObject;
import entity.CommonRecipeFactory;
import entity.CommonRecipeTagFactory;

import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.display_saved_recipe.DisplaySavedController;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveViewModel;
import interface_adapter.search_recipe.SearchController;

import interface_adapter.search_recipe.SearchController;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.connect.ConnectViewModel;

import entity.RecipeFactory;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveViewModel;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchedRecipe;

import interface_adapter.search_recipe_results.SearchResultsViewModel;

import interface_adapter.search_recipe.SearchViewModel;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Recipe Search Program");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchResultsViewModel searchResultsViewModel = new SearchResultsViewModel();
        ConnectViewModel connectViewModel = new ConnectViewModel();

        MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
        GetMealPlanDataAccessObject getMealPlanDataAccessObject = new GetMealPlanDataAccessObject(new CommonRecipeFactory(), new CommonRecipeTagFactory());

        SaveViewModel saveViewModel = new SaveViewModel();
        DisplayRecipeViewModel displayRecipeViewModel = new DisplayRecipeViewModel();
        DisplaySavedViewModel displaySavedViewModel = new DisplaySavedViewModel();
        AddMealPlanViewModel addMealPlanViewModel = new AddMealPlanViewModel();

        SearchRecipeDataAccessObject searchRecipeDataAccessObject = new SearchRecipeDataAccessObject(new CommonRecipeFactory(), new CommonRecipeTagFactory());

        SaveDataAccessObject saveDataAccessObject;
        try{
            saveDataAccessObject = new SaveDataAccessObject("./recipes.csv", new CommonRecipeFactory(),new CommonRecipeTagFactory());
        } catch (IOException e){
            throw new RuntimeException(e);
        }


        AddMealPlanDataAccessObject addMealPlanDAO = new AddMealPlanDataAccessObject();

        SearchRecipeDataAccessObject searchRecipeDataAccessObject = new SearchRecipeDataAccessObject(new CommonRecipeFactory(), new CommonRecipeTagFactory());

        SearchController searchController = SearchRecipeUseCaseFactory.createSearchRecipeUseCase(viewManagerModel, searchViewModel, searchResultsViewModel, searchRecipeDataAccessObject);

        SaveController saveController = SaveUseCaseFactory.createSaveRecipeUseCase(viewManagerModel, saveDataAccessObject, saveViewModel);

        DisplaySavedController displaySavedController = DisplaySavedUseCaseFactory.createDisplaySavedUseCase(displaySavedViewModel, viewManagerModel, saveDataAccessObject);

        AddMealPlanController addMealPlanController = AddToMealPlanUseCaseFactory.createAddToMealPlanSavedUseCase(addMealPlanViewModel, addMealPlanDAO, viewManagerModel);


        SearchView searchView = SearchRecipeUseCaseFactory.create(viewManagerModel, searchViewModel,
                searchResultsViewModel, searchRecipeDataAccessObject,
                mealPlanViewModel, getMealPlanDataAccessObject);
        views.add(searchView, searchView.viewName);

//        DisplayRecipeView displayRecipeView = new DisplayRecipeView(displayRecipeViewModel, viewManagerModel, saveViewModel, saveController);

        SearchResultView searchResultView = new SearchResultView(searchResultsViewModel, searchViewModel ,displayRecipeViewModel, saveViewModel, viewManagerModel, searchController, saveController);
        views.add(searchResultView, searchResultView.viewName);

        DisplaySavedView displaySavedView = new DisplaySavedView(displaySavedViewModel,addMealPlanViewModel,addMealPlanController,viewManagerModel);
        views.add(displaySavedView, displaySavedView.viewName);

        displaySavedController.execute();
        viewManagerModel.setActiveView(displaySavedView.viewName);

        MealPlanView mealPlanView = new MealPlanView(mealPlanViewModel, searchViewModel, viewManagerModel);
        views.add(mealPlanView, mealPlanView.viewName);

        SearchResultView searchResultView = new SearchResultView(searchResultsViewModel, searchViewModel, viewManagerModel, searchController, saveController);
        views.add(searchResultView, searchResultView.viewName);


        ConnectDataAccessObject connectDataAccessObject = new ConnectDataAccessObject();


        viewManagerModel.setActiveView(searchView.viewName);
        ConnectView connectView = ConnectUseCaseFactory.create(viewManagerModel, connectViewModel,connectDataAccessObject);
        views.add(connectView, connectView.viewName);

        viewManagerModel.setActiveView(connectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}