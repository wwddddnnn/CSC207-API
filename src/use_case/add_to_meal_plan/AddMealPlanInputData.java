package use_case.add_to_meal_plan;

import java.util.Date;

public class AddMealPlanInputData {
    private final String username;
    private final String hash;
    private final Date data;
    private final String slot;
    private final String position;
    private final String recipeId;

    public AddMealPlanInputData(String username, String hash, Date data,
                                String slot, String position, String recipeId) {
        this.username = username;
        this.hash = hash;
        this.data = data;
        this.slot = slot;
        this.position = position;
        this.recipeId = recipeId;
    }

    public String getUsername() {
        return username;
    }

    public String getSlot() {
        return slot;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public Date getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }

    public String getPosition() {
        return position;
    }
}
