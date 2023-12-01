package interface_adapter.connect;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConnectViewModel extends ViewModel {

    private ConnectState state = new ConnectState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ConnectViewModel() {
        super("Connect"); // Assuming a title for the view model
    }

    public void setState(ConnectState state) {
        this.state = state;
        firePropertyChanged();
    }

    public ConnectState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Additional methods can be added as required
}
