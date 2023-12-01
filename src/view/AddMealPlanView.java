package view;

import interface_adapter.add_to_meal_plan.AddMealPlanController;
import interface_adapter.add_to_meal_plan.AddMealPlanViewModel;
import interface_adapter.search_recipe.SearchState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddMealPlanView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Add to Meal Plan";
    private final JComboBox<String> dateComboBox;
    private final JComboBox<String> slotComboBox;
//    private final JTextField positionInputField = new JTextField(15);
    private final JButton addButton;
    private final AddMealPlanController addMealPlanController;
    private final AddMealPlanViewModel addMealPlanViewModel;

    public AddMealPlanView(AddMealPlanController addMealPlanController,
                           AddMealPlanViewModel addMealPlanViewModel,
                           String recipeId) {
        this.addMealPlanController = addMealPlanController;
        this.addMealPlanViewModel = addMealPlanViewModel;
        addMealPlanViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Add to meal plan");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] date = new String[7];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        date[0] = calendar.getTime().toString();
        for (int i = 1; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            String sd = calendar.getTime().getYear() + "-" +
                    calendar.getTime().getMonth() + "-" +
                    calendar.getTime().getDate();
            date[i] = sd;
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(addButton)) {
                    try {
                        addMealPlanController.execute((String) dateComboBox.getSelectedItem(),
                                (String) slotComboBox.getSelectedItem(), recipeId);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
