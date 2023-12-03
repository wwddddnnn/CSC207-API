package interface_adapter.display_saved_recipe;

import interface_adapter.search_recipe.SearchedRecipe;
import use_case.display_saved_recipe.DisplaySavedInputBoundary;
import use_case.save_recipe.SaveInputBoundary;
import use_case.save_recipe.SaveInputData;

public class DisplaySavedController {
    final DisplaySavedInputBoundary displaySavedInteractor;

    public DisplaySavedController(DisplaySavedInputBoundary displaySavedInteractor) {
        this.displaySavedInteractor = displaySavedInteractor;
    }

    public void execute(){
        displaySavedInteractor.execute();
    }
}
