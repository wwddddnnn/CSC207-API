package app;

import data_access.SearchRecipeDataAccessObject;
import data_access.ConnectDataAccessObject;
import entity.CommonRecipeFactory;
import entity.CommonRecipeTagFactory;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.ViewManagerModel;
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

        SearchRecipeDataAccessObject searchRecipeDataAccessObject = new SearchRecipeDataAccessObject(new CommonRecipeFactory(), new CommonRecipeTagFactory());

        ConnectDataAccessObject connectDataAccessObject = new ConnectDataAccessObject();


        SearchController searchController = SearchRecipeUseCaseFactory.createSearchRecipeUseCase(viewManagerModel, searchViewModel, searchResultsViewModel, searchRecipeDataAccessObject);

        SearchView searchView = SearchRecipeUseCaseFactory.create(searchViewModel, searchController);
        views.add(searchView, searchView.viewName);

        SearchResultView searchResultView = new SearchResultView(searchResultsViewModel, searchViewModel, viewManagerModel, searchController);
        views.add(searchResultView, searchResultView.viewName);

        viewManagerModel.setActiveView(searchView.viewName);
        ConnectView connectView = ConnectUseCaseFactory.create(viewManagerModel, connectViewModel,connectDataAccessObject);
        views.add(connectView, connectView.viewName);

        viewManagerModel.setActiveView(connectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}