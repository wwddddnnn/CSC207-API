package interface_adapter.get_meal_plan;

import interface_adapter.ViewModel;
import interface_adapter.search_recipe.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel extends ViewModel {
    private MealPlanState state = new MealPlanState();

    //TODO: implement this.
    public static final String RETURN_TO_SEARCH_BUTTON_LABEL = "Return to search";

    public MealPlanViewModel(){super("Meal Plan");}

    public void setState(MealPlanState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public MealPlanState getState() {
        return state;
    }
}
