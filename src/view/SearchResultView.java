package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;

public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Search results";
    private final SearchResultsViewModel searchResultsViewModel;

    private final SearchViewModel searchViewModel;

    private final ViewManagerModel viewManagerModel;


    private JLabel title = new JLabel();
//    private JLabel[] recipes = new JLabel[5];
    private HashMap<String, String> foundRecipes;
    private JButton[] recipesTitle = new JButton[5];

    final JButton confirm;
    final JButton nextPage;

    public SearchResultView(SearchResultsViewModel searchResultsViewModel,
                            SearchViewModel searchViewModel,
                            ViewManagerModel viewManagerModel) {
        for (int i = 0; i < 5; i++) recipesTitle[i] = new JButton("");
        this.searchResultsViewModel = searchResultsViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchResultsViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridLayout(6, 6)); // GridLayout to organize labels


        this.add(title);
        JPanel buttons = new JPanel();
        this.confirm = new JButton(searchResultsViewModel.CONFIRM_BUTTON_LABEL);
        // return to the searchView after clicking confirm button.
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    viewManagerModel.setActiveView(searchViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
            }
        });
        this.nextPage = new JButton(searchResultsViewModel.NEXT_PAGE_BUTTON_LABEL);
        this.nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // search next 5 recipes after clicking nextPage button.
            }
        });

        buttons.add(nextPage);
        buttons.add(confirm);

        for (JButton rt: recipesTitle) this.add(rt);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState currentState = (SearchState) evt.getNewValue();
        HashMap<String, String> recipes = currentState.getRecipe();
        this.foundRecipes = recipes;
        if (!foundRecipes.isEmpty()) {
            String[] rtList = foundRecipes.keySet().toArray(new String[0]);;
            for (int i = 0; i < rtList.length; i++) {
                recipesTitle[i].setText(rtList[i]);
                System.out.println(rtList[i]);
                recipesTitle[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DisplayRecipeViewModel displayRecipeViewModel= new DisplayRecipeViewModel();
                        DisplayRecipeView displayRecipeView = new DisplayRecipeView("maybe something in there.",
                                displayRecipeViewModel, searchResultsViewModel, viewManagerModel);
                        viewManagerModel.setActiveView(displayRecipeViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                });
            }
            int totalRecipeAmount = this.searchResultsViewModel.getState().getTotalRecipeAmount();
            System.out.println(totalRecipeAmount + " in ResultView");
            this.title.setText("These are the " + rtList.length + "/" + totalRecipeAmount + " recipes you want: ");
        } else {
//            recipes[0].setText("No recipes found!");
            this.title.setText("No recipes found!");
        }


    }
}