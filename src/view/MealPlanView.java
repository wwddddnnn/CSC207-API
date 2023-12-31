package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_meal_plan.MealPlanState;
import interface_adapter.get_meal_plan.MealPlanViewModel;
import interface_adapter.search_recipe.SearchViewModel;
import interface_adapter.search_recipe.SearchedRecipe;
import interface_adapter.search_recipe_results.DisplayRecipeViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
        //return to the searchView after clicking returnToSearch button.

        this.setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.returnToSearch = new JButton(mealPlanViewModel.RETURN_TO_SEARCH_BUTTON_LABEL);

        this.returnToSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView(searchViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        buttons.add(returnToSearch);
        this.add(buttons,BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MealPlanState currentState = (MealPlanState) evt.getNewValue();
        ArrayList<ArrayList<ArrayList>> result = currentState.getMealPlanResult();
        this.mealPlanArray = result;

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        for (int numDay = 0; numDay < 7; numDay++) {

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));

            String dayString = (String) mealPlanArray.get(numDay).get(0).get(0);
            String dayStringHalf1 = dayString.substring(0, 11);
            String dayStringHalf2 = dayString.substring(11);
            JLabel dayLabel1 = new JLabel(dayStringHalf1);
            JLabel dayLabel2 = new JLabel(dayStringHalf2);

            //Border blackline = BorderFactory.createLineBorder(Color.black);
            //LayoutManager layout = new FlowLayout();
            //dayPanel.setLayout(layout);

            //dayLabel.setBorder(blackline);

            dayPanel.add(dayLabel1);
            dayPanel.add(dayLabel2);

            JButton[] mealTitle = new JButton[3];
            for (int i = 0; i < 3; i++) mealTitle[i] = new JButton("");

            HashMap<String, Object> meal1HashMap = (HashMap<String, Object>) result.get(numDay).get(1).get(0);
            Object mt1String = meal1HashMap.get("simpleString"); //this is a string
            Object mInfo1Recipe = meal1HashMap.get("recipeObject"); //this is a SearchedRecipe object

            HashMap<String, Object> meal2HashMap = (HashMap<String, Object>) result.get(numDay).get(2).get(0);
            Object mt2String = meal2HashMap.get("simpleString");
            Object mInfo2Recipe = meal2HashMap.get("recipeObject");

            HashMap<String, Object> meal3HashMap = (HashMap<String, Object>) result.get(numDay).get(3).get(0);
            Object mt3String = meal3HashMap.get("simpleString");
            Object mInfo3Recipe = meal3HashMap.get("recipeObject");

            String[] mtList = new String[]{(String) mt1String, (String) mt2String, (String) mt3String};
            Object[] mInfoList = new Object[]{mInfo1Recipe, mInfo2Recipe, mInfo3Recipe};


            for (int numMeal = 0; numMeal < 3; numMeal++) {
                mealTitle[numMeal].setText(mtList[numMeal]);

                //layout for the buttons within a day.
                //mealTitle[numMeal].setLayout(new GridBagLayout());
                //GridBagConstraints gbcButton = new GridBagConstraints();
                //gbcButton.gridy = numMeal + 1;

                if (!Objects.equals(mtList[numMeal], null)) {
                    int finalI = numMeal;
                    mealTitle[numMeal].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DisplayRecipeViewModel displayRecipeViewModel = new DisplayRecipeViewModel();
                            DisplayRecipeView displayRecipeView = new DisplayRecipeView((SearchedRecipe) mInfoList[finalI], displayRecipeViewModel, mealPlanViewModel, viewManagerModel);
                            viewManagerModel.setActiveView(displayRecipeViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    });
                }

                //dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
                //dayPanel.setSize(100, 500);
                //dayPanel.setVisible(true);

                dayPanel.add(mealTitle[numMeal]);
            }

            container.add(dayPanel);

            }

        this.add(container);
        }


}
