package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginState;
import interface_adapter.search_recipe.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;
import view.SearchView;

public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Search Results";
    private final SearchResultsViewModel searchResultsViewModel;
    
    private final SearchViewModel searchViewModel;

    private final ViewManagerModel viewManagerModel;
    private JLabel[] recipeLabels = new JLabel[0]; // Array to hold labels for up to 5 recipes
    // Change in 2023/11/20: the amount of recipe may less than 5.

    final JButton confirm;

    public SearchResultView(SearchResultsViewModel searchResultsViewModel,
                            SearchViewModel searchViewModel,
                            ViewManagerModel viewManagerModel) {
        this.searchResultsViewModel = searchResultsViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchResultsViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridLayout(6, 1)); // GridLayout to organize labels

        updateResultView(); // Initial update

        this.add(new JLabel("These are the recipes you want: "));
        for (int i = 0; i < recipeLabels.length; i++) {
            recipeLabels[i] = new JLabel();
            this.add(recipeLabels[i]);
        }

        JPanel buttons = new JPanel();
        this.confirm = new JButton(searchResultsViewModel.CONFIRM_BUTTON_LABEL);
        this.confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // return to the searchView after clicking confirm button.
                if (e.getSource().equals(confirm)) {
                    searchViewModel.firePropertyChanged();

                    viewManagerModel.setActiveView(searchViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        buttons.add(confirm);

        this.add(buttons);
    }

    private void updateResultView() {
        SearchState currentState = searchResultsViewModel.getState();
        ArrayList<String> recipes = currentState.getRecipe();
        this.recipeLabels = new JLabel[recipes.size()];
//        clearRecipeLabels();
        // recipeLabels is already empty.

        for (int i = 0; i < recipeLabels.length; i++) {
            recipeLabels[i].setText(recipes.get(i));
        }
    }

    private void clearRecipeLabels() {
        for (JLabel label : recipeLabels) {
            label.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
