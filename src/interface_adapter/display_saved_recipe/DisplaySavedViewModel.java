package interface_adapter.display_saved_recipe;

import interface_adapter.ViewModel;
import interface_adapter.search_recipe.SearchedRecipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplaySavedViewModel extends ViewModel {
    private  SearchedRecipe[] searchedRecipes;
    public static final String PREVIOUS_PAGE_BUTTON_LABEL = "Previous Page";
    public static final String NEXT_PAGE_BUTTON_LABEL = "Next Page";
    public static final String ADD_BUTTON_LABEL = "Add to meal plan";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DisplaySavedViewModel(){
        super("Display saved recipes");
    }
    public void setSearchedRecipes(SearchedRecipe[] searchedRecipes){
        System.out.println(searchedRecipes.length);
        this.searchedRecipes = searchedRecipes;
    }

    public SearchedRecipe[] getSearchedRecipes() {
        return searchedRecipes;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("searchedRecipes", null, this.searchedRecipes);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
