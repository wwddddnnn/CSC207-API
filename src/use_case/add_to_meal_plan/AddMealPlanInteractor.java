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
        Date addDate = addMealPlanInputData.getData();
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 7);
        Date upToWeek = calendar.getTime();
        if (addDate.before(now) && addDate.after(upToWeek)) {
            addMealPlanPresenter.prepareFailView("You could pick any days starting from today up to a week! ");
            return;
        }

        // userInfo = {username, hash}, addInfo = {date, slot, position}
        String[] userInfo = {addMealPlanInputData.getUsername(), addMealPlanInputData.getHash()};
        String epochTimeString = addDate.getTime() / 1000 + "";
        String[] addInfo = {epochTimeString, addMealPlanInputData.getSlot(), addMealPlanInputData.getPosition()};
        String message = "";
        try {
            message = addMealPlanDAO.addMealPlan(userInfo, addInfo, addMealPlanInputData.getRecipeId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addMealPlanPresenter.prepareSuccessView(message);
    }
}
