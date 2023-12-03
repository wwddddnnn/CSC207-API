package use_case.add_to_meal_plan;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddMealPlanInteractor implements AddMealPlanInputBoundary {
    final AddMealPlanDataAccessInterface addMealPlanDAO;
    final AddMealPlanOutputBoundary addMealPlanPresenter;

    public AddMealPlanInteractor(AddMealPlanDataAccessInterface addMealPlanDAO, AddMealPlanOutputBoundary addMealPlanPresenter) {
        this.addMealPlanDAO = addMealPlanDAO;
        this.addMealPlanPresenter = addMealPlanPresenter;
    }

    @Override
    public void execute(AddMealPlanInputData addMealPlanInputData) {
        System.out.println("AddMealPlanInteractor working");
        Date addDate = addMealPlanInputData.getDate();

        // userInfo = {username, hash}, addInfo = {date, slot, position}
        String[] userInfo = {addMealPlanInputData.getUsername(), addMealPlanInputData.getHash()};
        String epochTimeString = addDate.getTime() / 1000 + "";
        String[] addInfo = {epochTimeString, addMealPlanInputData.getSlot()};
        String message = "";
        try {
            message = addMealPlanDAO.addMealPlan(userInfo, addInfo, addMealPlanInputData.getRecipeId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addMealPlanPresenter.prepareSuccessView(message);
    }
}
