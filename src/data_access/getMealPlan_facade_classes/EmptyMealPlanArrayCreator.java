package data_access.getMealPlan_facade_classes;

/* This class creates an empty Meal Plan ArrayList, given a starting date.

A Meal Plan ArrayList has this general shape:
    [
    [["2023-11-28, Tuesday"], [], [], []],
    [["2023-11-29, Wednesday"], [], [], []],
    [["2023-11-30, Thursday"], [], [], []],
    [["2023-12-01, Friday"], [], [], []],
    [["2023-12-02, Saturday"], [], [], []],
    [["2023-12-03, Sunday"], [], [], []],
    [["2023-12-04, Monday"], [], [], []],
    ]

Each ArrayList in the first layer of the larger Arraylist represents the 7 days of the week.
Within that, the first ArrayList holds a string with the date and day. The following 3 ArrayLists
are empty ArrayLists that will each represent either the breakfast, lunch or dinner meal of the day.

 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmptyMealPlanArrayCreator {
    public EmptyMealPlanArrayCreator() {}
    public ArrayList<ArrayList<ArrayList>> create(Date startDate) {

        ArrayList<ArrayList<ArrayList>> finalArrayList = new ArrayList<>();

        for (int numDays = 0; numDays < 7; numDays++) {

            //create dateArray, which is an ArrayList for the date e.g. ["2023-11-28, Tuesday"]
            ArrayList<String> dateArray = new ArrayList<>(1);
            Calendar cal = Calendar.getInstance();

            cal.setTime(startDate);
            cal.add(Calendar.DATE, numDays); //minus number would decrement the days
            String date = cal.getTime().toString();

            String dateString = date;
            dateArray.add(dateString);

            //create dayArrayList, which is 1 day's ArrayList e.g. [["2023-11-28, Tuesday"], [], [], []]
            ArrayList<ArrayList> dayArrayList = new ArrayList();
            dayArrayList.add(dateArray);
            dayArrayList.add(new ArrayList<>());
            dayArrayList.add(new ArrayList<>());
            dayArrayList.add(new ArrayList<>());

            finalArrayList.add(dayArrayList);
        }
        return finalArrayList;
    }
}
