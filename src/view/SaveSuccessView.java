package view;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.save_recipe.SaveSuccessViewModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveSuccessView extends JFrame implements ActionListener {
    private final String viewName = "Save success!";
    private final DisplayRecipeViewModel displayRecipeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SaveSuccessViewModel saveSuccessViewModel;
    private JPanel buttons = new JPanel();
    private JButton finish;
    private JLabel title = new JLabel();

    public SaveSuccessView(DisplayRecipeViewModel displayRecipeViewModel,
                           ViewManagerModel viewManagerModel, SaveSuccessViewModel saveSuccessViewModel){
        this.displayRecipeViewModel = displayRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.saveSuccessViewModel = saveSuccessViewModel;
        this.finish = new JButton(this.saveSuccessViewModel.FINISH_BUTTON_LABEL);
        buttons.add(finish);
        this.add(title);
        this.title.setText("Save Recipe Successful");
        this.add(buttons);
        this.finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSuccessView.this.viewManagerModel.setActiveView(SaveSuccessView.this.displayRecipeViewModel.getViewName());
                SaveSuccessView.this.viewManagerModel.firePropertyChanged();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
