package interface_adapter.save_recipe;

import entity.Recipe;

public class SaveState {
    private Recipe recipes;
    public SaveState(SaveState copy){
        this.recipes = copy.recipes;
    }
    public SaveState(){

    }
    public Recipe getRecipes(){
        return this.recipes;
    }

    public void setRecipes(Recipe recipe){
        this.recipes = recipe;
    }


}
