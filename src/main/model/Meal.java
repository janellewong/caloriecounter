package model;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<Food> meal;
    private String mealType;

    //REQUIRES: mealType can be one of "Breakfast", "Lunch", or "Dinner"
    //EFFECTS: creates a meal with no refreshments
    public Meal(String mealType) {
        meal = new ArrayList<>();
        this.mealType = mealType;
    }

    // EFFECTS: returns mealType
    public String getMealType() {
        return mealType;
    }

    //EFFECTS: adds food to meal
    public void addFood(Food food) {
        meal.add(food);
    }

    //EFFECTS: removes the first food with name from meal
    public void removeFood(String name) {
        Food temp = null;
        for (Food f : meal) {
            if (f.getName().equals(name)) {
                temp = f;
            }
        }
        meal.remove(temp);
    }

    public boolean containsFood(String name) {
        for (Food f : meal) {
            if (f.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: adds all calories in meal list
    public int addCalories() {
        int calories = 0;
        for (Food f : meal) {
            calories = calories + f.getCalories();
        }
        return calories;

    }

    // EFFECTS: Returns the foods that are currently in the meal
    public String viewFoodListForMeal() {
        String temp = "";
        for (Food f : meal) {
            temp = temp + f.getName() + "\n";
        }
        return temp;
    }
}
