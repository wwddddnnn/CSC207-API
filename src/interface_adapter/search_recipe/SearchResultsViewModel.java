package interface_adapter.search_recipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchResultsViewModel extends ViewModel {

    private SearchState state = new SearchState();
    public SearchResultsViewModel(){super("Search results");}
    public void setState(SearchState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public SearchState getState() {
        return state;
    }
}
