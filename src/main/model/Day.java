package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//EFFECTS: A list of all meals in a day
public class Day implements Writable {
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

    //MODIFIES: this
    //EFFECTS: changes your goal
    public void changeGoal(int newGoal) {
        goal = newGoal;
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
    public void removeMeal(String mealType) {
        Meal temp = null;
        for (Meal m: day) {
            if (m.getMealType().equals(mealType)) {
                temp = m;
            }
        }
        day.remove(temp);
    }

    //EFFECTS: returns true if name of meal is in day; false otherwise
    public boolean containsMeal(String name) {
        for (Meal m : day) {
            if (m.getMealType().equals(name)) {
                return true;
            }
        }
        return false;
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

    // EFFECTS: returns an unmodifiable list of meals in this day
    public List<Meal> getMeals() {
        return Collections.unmodifiableList(day);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goal", goal);
        json.put("day", dayToJson());
        return json;
    }

    // EFFECTS: returns things in this day as a JSON array
    private JSONArray dayToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : day) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }


}
