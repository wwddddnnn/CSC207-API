package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_meal_plan.MealPlanState;
import interface_adapter.get_meal_plan.MealPlanViewModel;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MealPlanView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Meal Plan";
    private final MealPlanViewModel mealPlanViewModel;
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private JLabel title = new JLabel();
    private ArrayList<ArrayList<ArrayList>> mealPlanArray;
    final JButton returnToSearch;

    public MealPlanView(MealPlanViewModel mealPlanViewModel,
                        SearchViewModel searchViewModel,
                        ViewManagerModel viewManagerModel) {
        this.mealPlanViewModel = mealPlanViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mealPlanViewModel.addPropertyChangeListener(this);

        this.add(title);
        JPanel buttons = new JPanel();
        this.returnToSearch = new JButton(mealPlanViewModel.RETURNTOSEARCH_BUTTON_LABEL);
        //return to the searchView after clicking returnToSearch button.

        this.returnToSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView(searchViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        buttons.add(returnToSearch);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MealPlanState currentState = (MealPlanState) evt.getNewValue();
        ArrayList<ArrayList<ArrayList>> result = currentState.getMealPlanResult();
        this.mealPlanArray = result;

        for (int numDay = 0; numDay < 7; numDay++) {
            JPanel dayPanel = new JPanel();
            String dayString = (String) mealPlanArray.get(numDay).get(0).get(0);
            JLabel dayLabel = new JLabel(dayString);
            dayPanel.add(dayLabel);

            JButton[] mealTitle = new JButton[3];

            HashMap<String, String> meal1HashMap = (HashMap<String, String>) result.get(numDay).get(1).get(0);
            String mt1String = meal1HashMap.get("simpleString");
            String mInfo1String = meal1HashMap.get("detailedString");

            HashMap<String, String> meal2HashMap = (HashMap<String, String>) result.get(numDay).get(2).get(0);
            String mt2String = meal2HashMap.get("simpleString");
            String mInfo2String = meal2HashMap.get("detailedString");

            HashMap<String, String> meal3HashMap = (HashMap<String, String>) result.get(numDay).get(3).get(0);
            String mt3String = meal3HashMap.get("simpleString");
            String mInfo3String = meal3HashMap.get("detailedString");

            String[] mtList = new String[]{mt1String, mt2String, mt3String};
            String[] mInfoList = new String []{mInfo1String, mInfo2String, mInfo3String};

            for (int numMeal = 0; numMeal < 3; numMeal++) {
                mealTitle[numMeal].setText(mtList[numMeal]);

                if (!mtList[numMeal].isEmpty()) {
                    int finalI = numMeal;
                    mealTitle[numMeal].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DisplayRecipeViewModel displayRecipeViewModel = new DisplayRecipeViewModel();
                            DisplayRecipeView displayRecipeView = new DisplayRecipeView(mInfoList[finalI], displayRecipeViewModel, mealPlanViewModel, viewManagerModel);
                            viewManagerModel.setActiveView(displayRecipeViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    });
                }
            }
            for (JButton mt : mealTitle) dayPanel.add(mt);
            this.add(dayPanel);
        }
    }

}
