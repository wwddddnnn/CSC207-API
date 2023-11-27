package interface_adapter.search_recipe_results;

import interface_adapter.ViewModel;
import interface_adapter.search_recipe.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchResultsViewModel extends ViewModel {

    private SearchState state = new SearchState();

    public static final String CONFIRM_BUTTON_LABEL = "Confirm";
    public static final String NEXT_PAGE_BUTTON_LABEL = "Next Page";

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
