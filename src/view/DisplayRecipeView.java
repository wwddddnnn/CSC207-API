package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveState;
import interface_adapter.save_recipe.SaveViewModel;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchedRecipe;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import interface_adapter.search_recipe_results.DisplayState;
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

public class DisplayRecipeView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "Display recipe";
    private final DisplayRecipeViewModel displayRecipeViewModel;
//    private final SearchResultsViewModel searchResultsViewModel;
    private final SaveViewModel saveViewModel;
    private final ViewManagerModel viewManagerModel;
    private SaveController saveController;
    private SearchedRecipe recipe;

    private JPanel middlePanel = new JPanel();
    private JButton finish;
    private JButton save;
    private JLabel label = new JLabel();
    private JTextArea display = new JTextArea(16, 58);;



    public DisplayRecipeView(SearchedRecipe recipe, DisplayRecipeViewModel displayRecipeViewModel,
                             ViewManagerModel viewManagerModel, SaveViewModel saveViewModel,
                             SaveController saveController) {
        this.displayRecipeViewModel = displayRecipeViewModel;
//        this.searchResultsViewModel = searchResultsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.saveViewModel = saveViewModel;
        this.saveController = saveController;
        this.recipe = recipe;
        System.out.println("this is " + recipe.getName() + " window.");
        displayRecipeViewModel.addPropertyChangeListener(this);
        saveViewModel.addPropertyChangeListener(this);

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
                if(e.getSource().equals(save)) {
                    //popUpWindow("Saved recipe successful.");
                    SaveState currentState = saveViewModel.getState();
                    currentState.setRecipe(recipe);

                    System.out.println("saveController is working with " + currentState.getRecipe().getName());
                    saveController.execute(currentState.getRecipe());
                }
            }
            });

        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

        // create the middle panel components
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
        label = new JLabel(new ImageIcon(image));
        this.middlePanel.add(label, BorderLayout.CENTER);

        //Add Textarea in to middle panel
        middlePanel.add(scroll);

        JFrame frame = new JFrame();
        frame.add(middlePanel);
//        middlePanel.add(finish);
        middlePanel.add(save);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private void popUpWindow(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SaveState) {
            SaveState state = (SaveState) evt.getNewValue();
            if (state.getRecipe().getId() == recipe.getId()){
                if (state.getRecipeError() != null) {
                    JOptionPane.showMessageDialog(this, state.getRecipeError());
                } else JOptionPane.showMessageDialog(this, "Save success!");
            }
        }
//        else {
//            DisplayState state = (DisplayState) evt.getNewValue();
//            if (state.getSearchedRecipe()  != null) {
//                this.recipe = state.getSearchedRecipe();
//                Image image = null;
//                try {
//                    image = ImageIO.read(new URL(recipe.getImageURL()));
//                } catch (Exception exp) {
//                    exp.printStackTrace();
//                }
//                label = new JLabel(new ImageIcon(image));
//                display.setText(recipe.toString());
//            }
//        }
    }
}
