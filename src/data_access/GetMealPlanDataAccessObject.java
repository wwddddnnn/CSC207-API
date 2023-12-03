package data_access;

import app.UserInfoRetriever;
import data_access.getMealPlan_facade_classes.EmptyMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.FullMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.HalfMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.SavedRecipeToStringConverter;
import entity.RecipeFactory;
import entity.RecipeTagFactory;
import use_case.get_meal_plan.GetMealPlanDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class GetMealPlanDataAccessObject implements GetMealPlanDataAccessInterface {

    private RecipeFactory recipeFactory;
    private RecipeTagFactory recipeTagFactory;
    private SavedRecipeToStringConverter savedRecipeToStringConverter;
    private EmptyMealPlanArrayCreator emptyMealPlanArrayCreator;
    private HalfMealPlanArrayCreator halfMealPlanArrayCreator;
    private FullMealPlanArrayCreator fullMealPlanArrayCreator;
    private SearchRecipeDataAccessObject searchRecipeDataAccessObject;

    //or a File as an attribute?

    public GetMealPlanDataAccessObject(RecipeFactory recipeFactory,
                                       RecipeTagFactory recipeTagFactory) {

        //this.startDate = UserInfoRetriever.getStartDate();
        //System.out.println(startDate);
        //this.startDateEpoch = UserInfoRetriever.getStartDateEpoch();
        //this.username = UserInfoRetriever.getUsername();
        //this.userHash = UserInfoRetriever.getUserHash();


        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;

        this.savedRecipeToStringConverter = new SavedRecipeToStringConverter(recipeFactory, recipeTagFactory);
        this.emptyMealPlanArrayCreator = new EmptyMealPlanArrayCreator();
        this.searchRecipeDataAccessObject = new SearchRecipeDataAccessObject(recipeFactory, recipeTagFactory);
        this.halfMealPlanArrayCreator = new HalfMealPlanArrayCreator();
        this.fullMealPlanArrayCreator = new FullMealPlanArrayCreator(savedRecipeToStringConverter, searchRecipeDataAccessObject);
    }

    public ArrayList<ArrayList<ArrayList>> getMealPlan() {
        Date startDate = new Date();
        ArrayList<ArrayList<ArrayList>> emptyMealPlanArray = emptyMealPlanArrayCreator.create(startDate);
        ArrayList<ArrayList<ArrayList>> halfMealPlanArray = halfMealPlanArrayCreator.create(emptyMealPlanArray);
        ArrayList<ArrayList<ArrayList>> fullMealPlanArray = null;
        try {
            fullMealPlanArray = fullMealPlanArrayCreator.create(halfMealPlanArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fullMealPlanArray;
    }

}
