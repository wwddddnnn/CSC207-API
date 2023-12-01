package view;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;
import interface_adapter.search_recipe_results.SearchResultsViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class DisplayRecipeView extends JFrame implements ActionListener {

    private final String viewName = "Display recipe";
    private final DisplayRecipeViewModel displayRecipeViewModel;
    private final SearchResultsViewModel searchResultsViewModel;
    private final ViewManagerModel viewManagerModel;

    private JPanel middlePanel = new JPanel();
    private JButton finish;
    private JButton save;

    public DisplayRecipeView(Recipe recipe, DisplayRecipeViewModel displayRecipeViewModel,
                             SearchResultsViewModel searchResultsViewModel,
                             ViewManagerModel viewManagerModel) {
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.searchResultsViewModel = searchResultsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.finish = new JButton(this.displayRecipeViewModel.FINISH_BUTTON_LABEL);
        this.save = new JButton("save this recipe");
        // return to the searchResultView after clicking finish button.
        this.finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayRecipeView.this.viewManagerModel.setActiveView(DisplayRecipeView.this.searchResultsViewModel.getViewName());
                DisplayRecipeView.this.viewManagerModel.firePropertyChanged();
            }
        });

        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            image = ImageIO.read(new URL(recipe.getImage()[0]));

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        this.middlePanel.add(label, BorderLayout.CENTER);

        //Add Textarea in to middle panel
        middlePanel.add(scroll);

        JFrame frame = new JFrame();
        frame.add(middlePanel);
        middlePanel.add(finish);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}