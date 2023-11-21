package data_access;

import entity.CommonRecipeFactory;
import entity.CommonRecipeTagFactory;
import entity.RecipeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeDataAccessObjectTest {
    private SearchRecipeDataAccessObject searchRecipeDAO;
    @BeforeEach
    void init(){
        this.searchRecipeDAO = new SearchRecipeDataAccessObject(
                new CommonRecipeFactory(), new CommonRecipeTagFactory());
    }
    @Test
    void getByFilters() {

    }
}