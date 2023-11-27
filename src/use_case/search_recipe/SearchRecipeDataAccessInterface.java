package use_case.search_recipe;

import entity.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SearchRecipeDataAccessInterface {

    ArrayList<Recipe> getByFilters(HashMap filters);

    int getAmountByFilter();

}
