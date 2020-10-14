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

    //EFFECTS: remove food from meal
    public void removeFood(Food food) {
        meal.remove(food);
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
