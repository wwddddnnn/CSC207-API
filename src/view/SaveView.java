package view;

import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveViewModel;
import entity.Recipe;
import interface_adapter.ViewManagerModel;
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

public class SaveView extends JFrame implements ActionListener, PropertyChangeListener {
    private final String viewName = "Saved Recipes";
    private final SaveController saveController;
    private final ViewManagerModel viewManagerModel;
    private final SaveViewModel saveViewModel;
    private JPanel middlePanel = new JPanel();
    private JButton next;
    public SaveView(SaveController saveController, SaveViewModel saveViewModel, ViewManagerModel viewManagerModel) {
        this.saveController = saveController;
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
        this.next = new JButton(SaveViewModel.NEXT_BUTTON_LABEL);
        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

        SearchedRecipe recipe = new SearchedRecipe();


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
        middlePanel.add(next);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
