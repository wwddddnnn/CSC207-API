package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import interface_adapter.search_recipe.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchState;

public class SearchResultView extends JPanel {
    private final SearchResultsViewModel searchResultViewModel;
    private final JLabel[] recipeLabels = new JLabel[5]; // Array to hold labels for up to 5 recipes

    public SearchResultView(SearchResultsViewModel viewModel) {
        this.searchResultViewModel = viewModel;
        searchResultViewModel.addPropertyChangeListener(evt -> updateResultView());

        this.setLayout(new GridLayout(6, 1)); // GridLayout to organize labels
        this.add(new JLabel("Search Results"));

        for (int i = 0; i < recipeLabels.length; i++) {
            recipeLabels[i] = new JLabel();
            this.add(recipeLabels[i]);
        }

        updateResultView(); // Initial update
    }

    private void updateResultView() {
        SearchState currentState = searchResultViewModel.getState();
        ArrayList<String> recipes = currentState.getRecipe();
        clearRecipeLabels();

        for (int i = 0; i < recipes.size() && i < recipeLabels.length; i++) {
            recipeLabels[i].setText(recipes.get(i));
        }
    }

    private void clearRecipeLabels() {
        for (JLabel label : recipeLabels) {
            label.setText("");
        }
    }
}
