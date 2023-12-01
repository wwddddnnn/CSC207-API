package use_case.add_to_meal_plan;

import java.util.Date;

public class AddMealPlanInputData {
    private final String username;
    private final String hash;
    private final Date date;
    private final String slot;
    private final String recipeId;

    public AddMealPlanInputData(String username, String hash, Date date,
                                String slot, String recipeId) {
        this.username = username;
        this.hash = hash;
        this.date = date;
        this.slot = slot;
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

    public Date getDate() {
        return date;
    }

    public String getHash() {
        return hash;
    }
}
