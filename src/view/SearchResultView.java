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

    public final String viewName = "Search results";
    private final SearchResultsViewModel searchResultsViewModel;

    private final SearchViewModel searchViewModel;

    private final ViewManagerModel viewManagerModel;
    private JLabel recipe1 = new JLabel("");
    private JLabel recipe2 = new JLabel("");
    private JLabel recipe3 = new JLabel("");
    private JLabel recipe4 = new JLabel("");
    private JLabel recipe5 = new JLabel("");

    final JButton confirm;

    public SearchResultView(SearchResultsViewModel searchResultsViewModel,
                            SearchViewModel searchViewModel,
                            ViewManagerModel viewManagerModel) {
        this.searchResultsViewModel = searchResultsViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchResultsViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridLayout(6, 1)); // GridLayout to organize labels

        this.add(new JLabel("These are the recipes you want: "));
        this.add(recipe1);
        this.add(recipe2);
        this.add(recipe3);
        this.add(recipe4);
        this.add(recipe5);


        JPanel buttons = new JPanel();
        this.confirm = new JButton(searchResultsViewModel.CONFIRM_BUTTON_LABEL);
        this.confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // return to the searchView after clicking confirm button.
                if (e.getSource().equals(confirm)) {
                    viewManagerModel.setActiveView(searchViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        buttons.add(confirm);

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState currentState = (SearchState) evt.getNewValue();
        ArrayList<String> recipes = currentState.getRecipe();

        if (recipes.size() != 0) {

            for (int i = 0; i < recipes.size(); i++) {
                if (i == 0) {
                    recipe1.setText(recipes.get(0));
                } else if (i == 1) {
                    recipe2.setText(recipes.get(1));
                } else if (i == 2) {
                    recipe3.setText(recipes.get(2));
                } else if (i == 3) {
                    recipe4.setText(recipes.get(3));
                } else if (i == 4) {
                    recipe5.setText(recipes.get(4));
                }
            }
        } else {
            recipe1.setText("No recipes found!");
        }


    }
}