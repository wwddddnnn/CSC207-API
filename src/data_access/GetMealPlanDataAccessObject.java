package data_access;

import data_access.getMealPlan_facade_classes.EmptyMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.FullMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.HalfMealPlanArrayCreator;
import data_access.getMealPlan_facade_classes.SavedRecipeToStringConverter;
import entity.RecipeFactory;
import entity.RecipeTagFactory;
import use_case.get_meal_plan.GetMealPlanDataAccessInterface;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class GetMealPlanDataAccessObject implements GetMealPlanDataAccessInterface {

    private LocalDate startDate;
    private Long startDateEpoch;
    private String username;
    private String userHash;
    private RecipeFactory recipeFactory;
    private RecipeTagFactory recipeTagFactory;
    private SavedRecipeToStringConverter savedRecipeToStringConverter;
    private EmptyMealPlanArrayCreator emptyMealPlanArrayCreator;
    private HalfMealPlanArrayCreator halfMealPlanArrayCreator;
    private FullMealPlanArrayCreator fullMealPlanArrayCreator;

    //or a File as an attribute?

    public GetMealPlanDataAccessObject(RecipeFactory recipeFactory,
                                       RecipeTagFactory recipeTagFactory,
                                       ConnectUserFileDataAccessObject connectUserFileDataAccessObject) {

        this.startDate = connectUserFileDataAccessObject.getStartDate();
        this.startDateEpoch = connectUserFileDataAccessObject.getStartDateEpoch();
        this.username = connectUserFileDataAccessObject.getUsername();
        this.userHash = connectUserFileDataAccessObject.getUserHash();

        this.recipeFactory = recipeFactory;
        this.recipeTagFactory = recipeTagFactory;

        this.savedRecipeToStringConverter = new SavedRecipeToStringConverter(recipeFactory, recipeTagFactory);
        this.emptyMealPlanArrayCreator = new EmptyMealPlanArrayCreator();
        this.halfMealPlanArrayCreator = new HalfMealPlanArrayCreator(username, userHash, startDate, startDateEpoch);
        this.fullMealPlanArrayCreator = new FullMealPlanArrayCreator(startDate, savedRecipeToStringConverter);
    }

    public ArrayList<ArrayList<ArrayList>> getMealPlan() {
        ArrayList<ArrayList<ArrayList>> emptyMealPlanArray = emptyMealPlanArrayCreator.create(startDate);
        ArrayList<ArrayList<ArrayList>> halfMealPlanArray = halfMealPlanArrayCreator.create(emptyMealPlanArray);
        ArrayList<ArrayList<ArrayList>> fullMealPlanArray = fullMealPlanArrayCreator.create(halfMealPlanArray);
        return fullMealPlanArray;
    }

}
