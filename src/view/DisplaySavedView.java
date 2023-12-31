package view;

import app.UserInfoRetriever;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.save_recipe.SaveState;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.search_recipe.SearchedRecipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

public class DisplaySavedView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Display saved recipes";
    private final DisplaySavedViewModel displaySavedViewModel;
    private final AddMealPlanViewModel addMealPlanViewModel;
    private final AddMealPlanController addMealPlanController;
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private SearchedRecipe[] searchedRecipes;

    private JButton previousPage;
    private JButton nextPage;
    private JButton addToMealPlan;
    private JButton confirm;
    private JPanel middlePanel = new JPanel();
    private JTextArea display;
    private JScrollPane scroll;
    private JLabel label;
    public DisplaySavedView(DisplaySavedViewModel displaySavedViewModel,
                            AddMealPlanViewModel addMealPlanViewModel,
                            SearchViewModel searchViewModel,
                            AddMealPlanController addMealPlanController,
                            ViewManagerModel viewManagerModel){
        this.displaySavedViewModel = displaySavedViewModel;
        this.addMealPlanViewModel = addMealPlanViewModel;
        this.searchViewModel = searchViewModel;
        this.addMealPlanController = addMealPlanController;
        this.viewManagerModel = viewManagerModel;

        this.displaySavedViewModel.addPropertyChangeListener(this);
        this.previousPage = new JButton(this.displaySavedViewModel.PREVIOUS_PAGE_BUTTON_LABEL);
        this.nextPage = new JButton(this.displaySavedViewModel.NEXT_PAGE_BUTTON_LABEL);
        this.addToMealPlan = new JButton(this.displaySavedViewModel.ADD_BUTTON_LABEL);
        this.confirm = new JButton(this.displaySavedViewModel.CONFIRM_BUTTON_LABEL);

        this.display = new JTextArea(16, 58);
        this.display.setEditable(false);
        this.display.setLineWrap(true);
        this.scroll = new JScrollPane(display);
        this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.label = new JLabel();

        this.setLayout(new BorderLayout());

        final int[] showingIndex = {0};

        this.previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showingIndex[0] <= 0) popUpWindow("No previous recipe!");
                else{
                    showingIndex[0] -= 1;
                    show(searchedRecipes[showingIndex[0]]);
                }
            }
        });
        this.nextPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    if (showingIndex[0] >= searchedRecipes.length - 1) popUpWindow("No next recipe!");
                    else{
                        showingIndex[0] += 1;
                        show(searchedRecipes[showingIndex[0]]);
                    }

                }
            });
        this.addToMealPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(addToMealPlan)) {
                    String recipeId = searchedRecipes[showingIndex[0]].getId() + "";
                    addMealPlanViewModel.setRecipeId(recipeId);
                    viewManagerModel.setActiveView(addMealPlanViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView(searchViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));
        middlePanel.add(label);
        middlePanel.add(scroll);
        JPanel buttons = new JPanel();
        buttons.add(previousPage);
        buttons.add(nextPage);
        buttons.add(addToMealPlan);
        buttons.add(confirm);
        this.add(middlePanel, BorderLayout.NORTH);
        this.add(buttons, BorderLayout.SOUTH);
    }

    private void show(SearchedRecipe searchedRecipe){
        this.display.setText(searchedRecipe.toString());
        Image image = null;
        try {
            image = ImageIO.read(new URL(searchedRecipe.getImageURL()));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(image);
        this.label.setIcon(imageIcon);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchedRecipe[]){
            this.searchedRecipes = (SearchedRecipe[]) evt.getNewValue();
            System.out.println(searchedRecipes.length);
            if (searchedRecipes.length > 0) show(this.searchedRecipes[0]);
        }
    }

    private void popUpWindow(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
