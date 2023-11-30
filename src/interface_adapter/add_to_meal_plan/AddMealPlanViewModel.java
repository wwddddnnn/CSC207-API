package interface_adapter.add_to_meal_plan;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddMealPlanViewModel extends ViewModel {

    private String message = "";

    public AddMealPlanViewModel() { super("Add to Meal Plan"); }

    public void setMessage(String message){
        this.message = message;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("message", "", this.message);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getMessage() {
        return message;
    }
}
