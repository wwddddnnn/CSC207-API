package interface_adapter.save_recipe;

import entity.Recipe;
import use_case.save_recipe.SaveInputBoundary;
import use_case.save_recipe.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveUseCaseInteractor;

    public SaveController(SaveInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }


    public void execute(Recipe savedRecipe){
        SaveInputData saveInputData = new SaveInputData(savedRecipe);

        saveUseCaseInteractor.execute(saveInputData);

    }
}
