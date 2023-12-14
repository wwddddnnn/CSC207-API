package entity;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommonRecipeTest {

    private CommonRecipe recipe;
    private CommonRecipeTag recipeTag;

    @BeforeEach
    void init(){
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Mediterranean");
        cuisines.add("French");
        cuisines.add("European");
        ArrayList<String> intolerances = new ArrayList<>();
        String instruction = "Preheat oven to 350 degrees. Butter and flour 2 (8 inch) round cake pans and set aside.\n" +
                "Heat milk on medium heat, add orange zest and saffron, bring to a gentle simmer and take off heat. Let cool.\n" +
                "Sift flour, baking soda, salt and baking powder.\n" +
                "Using a stand mixer cream butter and almond paste for 5 minutes. I cut up the almond paste to smaller pieces and added to mixer.Add confectioners sugar and beat till fluffy. Add in eggs, one at a time. Add in flour and sour cream alternately.\n" +
                "Gently pour in saffron mixture and mix well.\n" +
                "Divide batter between both prepared pans and bake for 30/40 minutes, test for doneness with a cake tester. I baked mined for 40 minutes. Serve with berries or dusted with confectioners sugar.";
        String[] image = {"jpg", "Unknow URL"};
        HashMap<String, ArrayList<Object>> ingredients = new HashMap<>();

        recipeTag = new CommonRecipeTag(45, 12, cuisines, false, false, intolerances);
        recipe = new CommonRecipe(643400, "French Style Almond Saffron Cake", image,recipeTag, instruction, ingredients);
    }

    @org.junit.jupiter.api.Test
    void getId() {
        assertEquals(643400, recipe.getId());
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("French Style Almond Saffron Cake", recipe.getName());
    }

    @org.junit.jupiter.api.Test
    void getImage() {
        assertArrayEquals(new String[]{"jpg", "Unknow URL"}, recipe.getImage());
    }

    @org.junit.jupiter.api.Test
    void getInstructions() {
        String instruction = "Preheat oven to 350 degrees. Butter and flour 2 (8 inch) round cake pans and set aside.\n" +
                "Heat milk on medium heat, add orange zest and saffron, bring to a gentle simmer and take off heat. Let cool.\n" +
                "Sift flour, baking soda, salt and baking powder.\n" +
                "Using a stand mixer cream butter and almond paste for 5 minutes. I cut up the almond paste to smaller pieces and added to mixer.Add confectioners sugar and beat till fluffy. Add in eggs, one at a time. Add in flour and sour cream alternately.\n" +
                "Gently pour in saffron mixture and mix well.\n" +
                "Divide batter between both prepared pans and bake for 30/40 minutes, test for doneness with a cake tester. I baked mined for 40 minutes. Serve with berries or dusted with confectioners sugar.";
        assertEquals(instruction, recipe.getInstructions());
    }

    @org.junit.jupiter.api.Test
    void getRecipeTag() {
        assertEquals(recipeTag, recipe.getRecipeTag());
    }

    @org.junit.jupiter.api.Test
    void getIngredients() {
        HashMap<String, ArrayList<Object>> ingredients = new HashMap<>();
        assertEquals(ingredients, recipe.getIngredients());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        String result = "Name: French Style Almond Saffron Cake\n" +
                "Minutes to make: 45\n" +
                "Servings: 12\n" +
                "Cuisines:Mediterranean, French, European\n" +
                "Vegetarian: Yes\n" +
                "Vegan: No\n" +
                "Intolerances: \n" +
                "Preheat oven to 350 degrees. Butter and flour 2 (8 inch) round cake pans and set aside.\n" +
                "Heat milk on medium heat, add orange zest and saffron, bring to a gentle simmer and take off heat. Let cool.\n" +
                "Sift flour, baking soda, salt and baking powder.\n" +
                "Using a stand mixer cream butter and almond paste for 5 minutes. I cut up the almond paste to smaller pieces and added to mixer.Add confectioners sugar and beat till fluffy. Add in eggs, one at a time. Add in flour and sour cream alternately.\n" +
                "Gently pour in saffron mixture and mix well.\n" +
                "Divide batter between both prepared pans and bake for 30/40 minutes, test for doneness with a cake tester. I baked mined for 40 minutes. Serve with berries or dusted with confectioners sugar.";
        assertEquals(result, result.toString());
    }
}