package interface_adapter.search_recipe;

import interface_adapter.ViewModel;
import interface_adapter.search_recipe.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public static final String SEARCH_BUTTON_LABEL = "Search";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String TITLE_LABEL = "Search recipes";

    public static final String QUERY_LABEL = "Enter query";

    public static final String CUISINE_LABEL = "Enter cuisine";

    private SearchState state = new SearchState();

    public SearchViewModel(){super("search recipes");};

    public void setState(SearchState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {

        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
