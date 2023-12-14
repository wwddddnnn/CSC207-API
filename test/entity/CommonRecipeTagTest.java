package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommonRecipeTagTest {

    private CommonRecipeTag recipeTag;

    @BeforeEach
    void init(){
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Mediterranean");
        cuisines.add("French");
        cuisines.add("European");
        ArrayList<String> intolerances = new ArrayList<>();
        recipeTag = new CommonRecipeTag(45, 12, cuisines, false, false, intolerances);
    }

    @Test
    void getRecipeMinutes() {
        assertEquals(45, recipeTag.getRecipeMinutes());
    }

    @Test
    void getServings() {
        assertEquals(12, recipeTag.getServings());
    }

    @Test
    void getCuisines() {
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Mediterranean");
        cuisines.add("French");
        cuisines.add("European");

        assertEquals(cuisines, recipeTag.getCuisines());
    }

    @Test
    void getVegetarianBool() {
        assertEquals(false, recipeTag.getVegetarianBool());
    }

    @Test
    void getVeganBool() {
        assertEquals(false, recipeTag.getVeganBool());
    }

    @Test
    void getIntolerances() {
        ArrayList<String> intolerances = new ArrayList<>();
        assertEquals(intolerances, recipeTag.getIntolerances());
    }
}