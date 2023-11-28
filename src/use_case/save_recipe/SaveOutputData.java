package use_case.save_recipe;

import java.util.List;

public class SaveOutputData {
    private final List<String> savedRecipes;
    private String saveMessage;

    public SaveOutputData(List<String> savedRecipes, String saveMessage){
        this.savedRecipes = savedRecipes;
        this.saveMessage = saveMessage;
    }

    public List<String> getSavedRecipes(){
        return this.savedRecipes;
    }

    public String getSaveMessage(){
        return this.saveMessage;
    }
}
