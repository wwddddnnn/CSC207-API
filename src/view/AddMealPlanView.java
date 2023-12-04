package view;

import app.UserInfoRetriever;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.display_saved_recipe.DisplaySavedViewModel;
import interface_adapter.search_recipe.SearchState;
import interface_adapter.search_recipe.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddMealPlanView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Add to Meal Plan";
    private final JComboBox<String> dateComboBox;
    private final JComboBox<String> slotComboBox;
//    private final JTextField positionInputField = new JTextField(15);
    private final JButton addButton;
    private final JButton returnButton;
    private final AddMealPlanController addMealPlanController;
    private final AddMealPlanViewModel addMealPlanViewModel;
    private final DisplaySavedViewModel displaySavedViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddMealPlanView(AddMealPlanController addMealPlanController,
                           AddMealPlanViewModel addMealPlanViewModel,
                           DisplaySavedViewModel displaySavedViewModel,
                           ViewManagerModel viewManagerModel) {
        this.addMealPlanController = addMealPlanController;
        this.addMealPlanViewModel = addMealPlanViewModel;
        this.displaySavedViewModel = displaySavedViewModel;
        this.viewManagerModel = viewManagerModel;
        addMealPlanViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Add to meal plan");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] date = new String[7];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date[0] = dateFormat.format(calendar.getTime());
        for (int i = 1; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            date[i] = dateFormat.format(calendar.getTime());
        }


        dateComboBox = new JComboBox<>(date);
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Date:"));
        datePanel.add(dateComboBox);

        String[] slot = {"Breakfast", "Lunch", "Dinner"};
        slotComboBox = new JComboBox<>(slot);
        JPanel slotPanel = new JPanel();
        slotPanel.add(new JLabel("Slot:"));
        slotPanel.add(slotComboBox);

        addButton = new JButton("Add");
        returnButton = new JButton("Return to saved recipe page");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(datePanel);
        this.add(slotPanel);
        this.add(buttonPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(addButton)) {
                    try {
                        String username = UserInfoRetriever.getUsername();
                        String hash = UserInfoRetriever.getUserHash();
                        String recipeId = addMealPlanViewModel.getRecipeId();
                        addMealPlanController.execute(username, hash, (String) dateComboBox.getSelectedItem(),
                                (String) slotComboBox.getSelectedItem(), recipeId);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView(displaySavedViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof String){
            String message = (String)evt.getNewValue();
            popUpWindow(message);
        }
    }
    private void popUpWindow(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
