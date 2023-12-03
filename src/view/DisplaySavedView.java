package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.save_recipe.SaveState;
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
    private final ViewManagerModel viewManagerModel;
    private SearchedRecipe[] searchedRecipes;

    private JButton previousPage;
    private JButton nextPage;
    private JButton addToMealPlan;
    private JPanel middlePanel = new JPanel();
    private JTextArea display;
    private JScrollPane scroll;
    private JLabel label;
    public DisplaySavedView(DisplaySavedViewModel displaySavedViewModel, AddMealPlanViewModel addMealPlanViewModel, AddMealPlanController addMealPlanController, ViewManagerModel viewManagerModel){
        this.displaySavedViewModel = displaySavedViewModel;
        this.addMealPlanViewModel = addMealPlanViewModel;
        this.addMealPlanController = addMealPlanController;
        this.viewManagerModel = viewManagerModel;

        this.displaySavedViewModel.addPropertyChangeListener(this);
        this.previousPage = new JButton(this.displaySavedViewModel.PREVIOUS_PAGE_BUTTON_LABEL);
        this.nextPage = new JButton(this.displaySavedViewModel.NEXT_PAGE_BUTTON_LABEL);
        this.addToMealPlan = new JButton(this.displaySavedViewModel.ADD_BUTTON_LABEL);

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
                    System.out.println(showingIndex[0] + "," + searchedRecipes.length);
                    if (showingIndex[0] >= searchedRecipes.length) popUpWindow("No next recipe!");
                    else{
                        showingIndex[0] += 1;
                        show(searchedRecipes[showingIndex[0]]);
                    }

                }
            });
        this.addToMealPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));
        middlePanel.add(label);
        middlePanel.add(scroll);
        this.add(middlePanel, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        middlePanel.add(previousPage);
        middlePanel.add(nextPage);
        middlePanel.add(addToMealPlan);
        this.add(buttons, BorderLayout.PAGE_END);
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
            show(this.searchedRecipes[0]);
        }
    }

    private void popUpWindow(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
