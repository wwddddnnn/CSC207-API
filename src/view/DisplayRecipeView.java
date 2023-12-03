package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_meal_plan.MealPlanViewModel;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveState;
import interface_adapter.save_recipe.SaveViewModel;
import interface_adapter.search_recipe.SearchController;
import interface_adapter.search_recipe.SearchedRecipe;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;

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

public class DisplayRecipeView extends JFrame implements PropertyChangeListener {

    private final String viewName = "Display recipe";

    //TODO: Make this recipe attribute 'final' once you incorporate it into the second DisplayRecipeView constructor.
    private SearchedRecipe recipe;
    private MealPlanViewModel mealPlanViewModel = null;
    private final DisplayRecipeViewModel displayRecipeViewModel;
    private SearchResultsViewModel searchResultsViewModel;
    private SaveViewModel saveViewModel;
    private final ViewManagerModel viewManagerModel;
    private SaveController saveController;

    private JPanel middlePanel = new JPanel();
    private JButton save;
    private JButton finish;

    public DisplayRecipeView(SearchedRecipe recipe, DisplayRecipeViewModel displayRecipeViewModel,
                             SearchResultsViewModel searchResultsViewModel,
                             SaveViewModel saveViewModel,
                             ViewManagerModel viewManagerModel,
                             SaveController saveController) {
        this.recipe = recipe;
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.searchResultsViewModel = searchResultsViewModel;
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.saveController = saveController;

        this.saveViewModel.addPropertyChangeListener(this);

//        this.finish = new JButton(this.displayRecipeViewModel.FINISH_BUTTON_LABEL);
        this.save = new JButton(this.displayRecipeViewModel.SAVE_BUTTON_LABEL);
        // return to the searchResultView after clicking finish button.
//        this.finish.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DisplayRecipeView.this.viewManagerModel.setActiveView(DisplayRecipeView.this.searchResultsViewModel.getViewName());
//                DisplayRecipeView.this.viewManagerModel.firePropertyChanged();
//            }
//        });

        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveState currentState = saveViewModel.getState();
                currentState.setRecipe(recipe);
                currentState.setRecipeError(null);
                saveController.execute(recipe);
            }
        });

        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

        // create the middle panel components

        JTextArea display = new JTextArea(16, 58);
        display.setText(recipe.toString());
        display.setEditable(false); // set textArea non-editable
        display.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Image image = null;
        try {
            image = ImageIO.read(new URL(recipe.getImageURL()));

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        this.middlePanel.add(label, BorderLayout.CENTER);

        //Add Textarea in to middle panel
        middlePanel.add(scroll);

        JFrame frame = new JFrame();
        frame.add(middlePanel);
        middlePanel.add(save);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DisplayRecipeView(String recipeString, DisplayRecipeViewModel displayRecipeViewModel,
                             MealPlanViewModel mealPlanViewModel,
                             ViewManagerModel viewManagerModel) {
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
        this.viewManagerModel = viewManagerModel;
        this.finish = new JButton(this.displayRecipeViewModel.FINISH_BUTTON_LABEL);
        // return to the searchResultView after clicking finish button.
        this.finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayRecipeView.this.viewManagerModel.setActiveView(DisplayRecipeView.this.mealPlanViewModel.getViewName());
                DisplayRecipeView.this.viewManagerModel.firePropertyChanged();
            }
        });

        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

        // create the middle panel components

        JTextArea display = new JTextArea(16, 58);
        display.setText(recipeString);
        display.setEditable(false); // set textArea non-editable
        display.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Image image = null;
        //TODO: Add image if possible; need to include image information in Output date
        //try {
        //    image = ImageIO.read(new URL(recipe.getImage()[0]));

        //} catch (Exception exp) {
        //    exp.printStackTrace();
        //}
        //JLabel label = new JLabel(new ImageIcon(image));
        //this.middlePanel.add(label, BorderLayout.CENTER);

        //Add Textarea in to middle panel
        middlePanel.add(scroll);

        // My code
        JFrame frame = new JFrame();
        frame.add(middlePanel);
//        frame.add(finish);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SaveState){
            SaveState state = (SaveState) evt.getNewValue();
            if (state.getRecipe().getId() != this.recipe.getId()) return;
            if (state.getRecipeError() !=  null){
                JOptionPane.showMessageDialog(this, state.getRecipeError());
            } else JOptionPane.showMessageDialog(this, "Save successfully!");
        }
    }


}