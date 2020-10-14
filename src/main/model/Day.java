package model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<Meal> day;
    private int goal;

    //EFFECTS: creates a day with no meals yet
    public Day(int goal) {
        day = new ArrayList<>();
        this.goal = goal;
    }

    // EFFECTS: returns goal
    public int getGoal() {
        return goal;
    }

    //REQUIRES: number of meals in a day cannot exceed 3 (Breakfast, Lunch, Dinner)
    // EFFECTS: returns number of meals in day
    public int getNumberMeals() {
        return day.size();
    }


    //EFFECTS: adds meal to list only if there is not already
    // a meal of the same mealType in the list. If there is, nothing changes
    public void addMeal(Meal meal) {
        for (Meal m : day) {
            if (m.getMealType() == meal.getMealType()) {
                return;
            }
        }
        day.add(meal);
    }

    //EFFECTS: remove meal from list
    public void removeMeal(Meal meal) {
        day.remove(meal);
    }


    // EFFECTS: Returns the foods that are currently in the meal
    public String viewFoodListForDay() {
        String list = "";
        for (Meal m : day) {
            list = list + (m.viewFoodListForMeal());
        }
        return list;
    }


    //EFFECTS: adds all calories in the day
    public int totalCaloriesToday() {
        int cal = 0;
        for (Meal m : day) {
            cal = cal + (m.addCalories());
        }
        return cal;
    }

    //EFFECTS: finds out how many remaining calories you have
    // today until you reach your goal limit
    public int remainingCal() {
        return goal - totalCaloriesToday();
    }

    //EFFECTS: returns true if you are under or equal to your goal limit
    // and false if you are over
    public boolean goalSuccess() {
        if (totalCaloriesToday() <= goal) {
            return true;
        } else {
            return false;
        }
    }





}
