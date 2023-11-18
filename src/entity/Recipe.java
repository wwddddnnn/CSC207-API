package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private int id;
    private String name;
    private String[] image;
    private RecipeTag recipeTag;
    private String instructions;
    private HashMap<String, ArrayList<Object>> ingredients;

    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder {
        private int id;
        private String name;

        RecipeBuilder() {
        }

        public RecipeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder name(String name) {
            this.name = name;
            return this;
        }


        public Recipe build() {
            return new Recipe(id, name);
        }
    }

//    @Override
//    public String toString() {
//        return "Grade{" +
//                "utorid='" + utorid + '\'' +
//                ", course='" + course + '\'' +
//                ", grade=" + grade +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addImage(String imageURL, String imageType) {
        this.image = new String[]{imageURL, imageType};
    }

    public String[] getImage() { return image; }

    public void addRecipeTag(RecipeTag recipeTag) {
        this.recipeTag = recipeTag;
    }

    public void addInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void addIngredients(HashMap<String, ArrayList<Object>> ingredients) {
        this.ingredients = ingredients;
    }

}
