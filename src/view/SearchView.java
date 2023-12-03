package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import interface_adapter.display_saved_recipe.DisplaySavedController;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.get_meal_plan.GetMealPlanController;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.ViewManagerModel;


public class SearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "Search recipes";
    private final JTextField queryInputField = new JTextField(15);
    private final JComboBox<String> cuisineComboBox;
    private final JTextField weightInputField = new JTextField(15);
    private final JButton searchButton;
    private final JButton getMealPlanButton;
    private final JButton savedRecipe;
    private final SearchController searchController;
    private final GetMealPlanController getMealPlanController;
    private final SearchViewModel searchViewModel;
    private final DisplaySavedController displaySavedController;
    private ViewManagerModel viewManagerModel;


    public SearchView(SearchController controller, SearchViewModel searchViewModel,
                      GetMealPlanController getMealPlanController,
                      DisplaySavedController displaySavedController,
                      ViewManagerModel viewManagerModel) {

        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.getMealPlanController = getMealPlanController;
        this.displaySavedController = displaySavedController;
        this.viewManagerModel = viewManagerModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Search");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel queryPanel = new JPanel();
        queryPanel.add(new JLabel("Query:"));
        queryPanel.add(queryInputField);

        String[] cuisines = {"Italian", "Chinese", "Indian", "Mexican", "Japanese", "French", "Other"};
        cuisineComboBox = new JComboBox<>(cuisines);
        JPanel cuisinePanel = new JPanel();
        cuisinePanel.add(new JLabel("Cuisine:"));
        cuisinePanel.add(cuisineComboBox);

        JPanel weightPanel = new JPanel();
        weightPanel.add(new JLabel("Max Time:"));
        weightPanel.add(weightInputField);

        searchButton = new JButton("Search");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);


        //added a getMealPlanButton
        getMealPlanButton = new JButton("My Meal Plan");
        JPanel getMealPlanButtonPanel = new JPanel();
        getMealPlanButtonPanel.add(getMealPlanButton);

        //Added a savedRecipe Button
        savedRecipe = new JButton("Saved Recipes");
        getMealPlanButtonPanel.add(savedRecipe);

        // KeyListeners for fields
        queryInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                String text = queryInputField.getText() + e.getKeyChar();
                currentState.setQuery(text);
                searchViewModel.setState(currentState);
            }
        });

        weightInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                String text = weightInputField.getText() + e.getKeyChar();
                currentState.setMaxTime(text);
                searchViewModel.setState(currentState);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(searchButton)) {
                    SearchState currentState = searchViewModel.getState();
                    if (currentState.getMaxTime().equals("0")){
                        popUpWindow("MaxTime cannot be 0!");
                    } else {
                        searchController.execute(currentState.getQuery(),
                                (String) cuisineComboBox.getSelectedItem(),
                                currentState.getMaxTime(),
                                currentState.getOffset());
                    }
                }
            }
        });


        getMealPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getMealPlanButton)) {
                    getMealPlanController.execute();
                }
            }
        });

        savedRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(savedRecipe)) {
                    displaySavedController.execute();
//                    viewManagerModel.setActiveView(displaySavedViewModel.getViewName());
//                    viewManagerModel.firePropertyChanged();

                }
            }
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(queryPanel);
        this.add(cuisinePanel);
        this.add(weightPanel);
        this.add(buttonPanel);

        this.add(getMealPlanButtonPanel);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        SearchState state = (SearchState) evt.getNewValue();
//        JOptionPane.showMessageDialog(this, state.getRecipe());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void popUpWindow(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}


