package view;

import interface_adapter.save_recipe.SaveController;
import interface_adapter.save_recipe.SaveViewModel;
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

public class SaveView extends JFrame implements ActionListener{
    public SaveView(SaveController saveController, SaveViewModel saveViewModel) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
