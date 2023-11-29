package interface_adapter.get_meal_plan;

import interface_adapter.search_recipe.SearchState;

import java.util.ArrayList;

public class MealPlanState {
    private ArrayList<String> day1SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day1DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day2SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day2DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day3SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day3DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day4SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day4DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day5SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day5DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day6SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day6DetailedStrings = new ArrayList<String>(3);

    private ArrayList<String> day7SimpleStrings = new ArrayList<String>(3);
    private ArrayList<String> day7DetailedStrings = new ArrayList<String>(3);

    public MealPlanState(MealPlanState copy){
        day1SimpleStrings = copy.day1SimpleStrings;
        day1DetailedStrings = copy.day1DetailedStrings;

        day2SimpleStrings = copy.day2SimpleStrings;
        day2DetailedStrings = copy.day2DetailedStrings;

        day3SimpleStrings = copy.day3SimpleStrings;
        day3DetailedStrings = copy.day3DetailedStrings;

        day4SimpleStrings = copy.day4SimpleStrings;
        day4DetailedStrings = copy.day4DetailedStrings;

        day5SimpleStrings = copy.day5SimpleStrings;
        day5DetailedStrings = copy.day5DetailedStrings;

        day6SimpleStrings = copy.day6SimpleStrings;
        day6DetailedStrings = copy.day6DetailedStrings;

        day7SimpleStrings = copy.day7SimpleStrings;
        day7DetailedStrings = copy.day7DetailedStrings;
    }
    public MealPlanState(){}

    //set methods
    public void setDay1SimpleStrings(ArrayList<String> d1Simple){day1SimpleStrings = d1Simple;}
    public void setDay1DetailedStrings(ArrayList<String> d1Detailed){day1DetailedStrings = d1Detailed;}

    public void setDay2SimpleStrings(ArrayList<String> d2Simple){day2SimpleStrings = d2Simple;}
    public void setDay2DetailedStrings(ArrayList<String> d2Detailed){day2DetailedStrings = d2Detailed;}

    public void setDay3SimpleStrings(ArrayList<String> d3Simple){day3SimpleStrings = d3Simple;}
    public void setDay3DetailedStrings(ArrayList<String> d3Detailed){day3DetailedStrings = d3Detailed;}

    public void setDay4SimpleStrings(ArrayList<String> d4Simple){day4SimpleStrings = d4Simple;}
    public void setDay4DetailedStrings(ArrayList<String> d4Detailed){day4DetailedStrings = d4Detailed;}

    public void setDay5SimpleStrings(ArrayList<String> d5Simple){day5SimpleStrings = d5Simple;}
    public void setDay5DetailedStrings(ArrayList<String> d5Detailed){day5DetailedStrings = d5Detailed;}

    public void setDay6SimpleStrings(ArrayList<String> d6Simple){day6SimpleStrings = d6Simple;}
    public void setDay6DetailedStrings(ArrayList<String> d6Detailed){day6DetailedStrings = d6Detailed;}

    public void setDay7SimpleStrings(ArrayList<String> d7Simple){day7SimpleStrings = d7Simple;}
    public void setDay7DetailedStrings(ArrayList<String> d7Detailed){day7DetailedStrings = d7Detailed;}

    //get methods
    public ArrayList<String> getDay1SimpleStrings(){return day1SimpleStrings;}
    public ArrayList<String> getDay1DetailedStrings(){return day1DetailedStrings;}

    public ArrayList<String> getDay2SimpleStrings(){return day2SimpleStrings;}
    public ArrayList<String> getDay2DetailedStrings(){return day2DetailedStrings;}

    public ArrayList<String> getDay3SimpleStrings(){return day3SimpleStrings;}
    public ArrayList<String> getDay3DetailedStrings(){return day3DetailedStrings;}

    public ArrayList<String> getDay4SimpleStrings(){return day4SimpleStrings;}
    public ArrayList<String> getDay4DetailedStrings(){return day4DetailedStrings;}

    public ArrayList<String> getDay5SimpleStrings(){return day5SimpleStrings;}
    public ArrayList<String> getDay5DetailedStrings(){return day5DetailedStrings;}

    public ArrayList<String> getDay6SimpleStrings(){return day6SimpleStrings;}
    public ArrayList<String> getDay6DetailedStrings(){return day6DetailedStrings;}

    public ArrayList<String> getDay7SimpleStrings(){return day7SimpleStrings;}
    public ArrayList<String> getDay7DetailedStrings(){return day7DetailedStrings;}

}
