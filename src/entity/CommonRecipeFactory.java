package entity;

public class CommonRecipeFactory implements RecipeFactory{
    @Override
    public Recipe create(int id, String name) {
        return new CommonRecipe(id, name);
    }
}
