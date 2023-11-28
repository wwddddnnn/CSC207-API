package interface_adapter.save_recipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveSuccessViewModel extends ViewModel {
    private SaveState state = new SaveState();
    public static final String FINISH_BUTTON_LABEL = "OK";
    public SaveSuccessViewModel() {
        super("save");
    }
    public void setState(SaveState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public SaveState getState() {
        return state;
    }
}
