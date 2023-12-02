package interface_adapter.search_recipe_results;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayRecipeViewModel extends ViewModel {
    private DisplayState state = new DisplayState();
    public static final String SAVE_BUTTON_LABEL = "Save this recipe";

    public DisplayRecipeViewModel(){super("Display recipe");}

//    public void setState(SearchState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DisplayState getState() {
        return state;
    }
}
