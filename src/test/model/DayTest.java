package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DayTest {

    Day day;
    Meal meal1;
    Meal meal2;
    Meal meal3;
    Meal meal4;
    Food food1;
    Food food2;
    Food food3;
    Food food4;

    @BeforeEach
    void runBefore() {
        day = new Day(1800);
        meal1 = new Meal("breakfast");
        meal2 = new Meal("lunch");
        meal3 = new Meal("dinner");
        meal4 = new Meal("breakfast");

        food1 = new Food("ham", 145);
        food2 = new Food("bread", 430);
        food3 = new Food("mayo", 200);
        food4 = new Food("ice cream", 2000);
    }

    @Test
    void testAddMealGood() {
        day.addMeal(meal2);
        assertEquals(1, day.getNumberMeals());
    }

    @Test
    void testChangeGoal() {
        day.changeGoal(2000);
        assertEquals(2000, day.getGoal());
    }

    @Test
    void testGetGoal() {
        assertEquals(1800, day.getGoal());
    }

    @Test
    void testAddMealFail() {
        day.addMeal(meal1);
        day.addMeal(meal4);
        assertEquals(1, day.getNumberMeals());
    }

    @Test
    void testRemoveMeal() {
        day.addMeal(meal1);
        day.removeMeal("breakfast");
        assertEquals(0, day.getNumberMeals());
    }

    @Test
    void testContainsMealTrue() {
        day.addMeal(meal1);
        assertTrue(day.containsMeal("breakfast"));
    }

    @Test
    void testContainsMealFalse() {
        day.addMeal(meal1);
        assertFalse(day.containsMeal("lunch"));
    }

    @Test
    void testViewFoodListForDay() {
        meal1.addFood(food1);
        meal2.addFood(food2);
        meal3.addFood(food3);
        day.addMeal(meal1);
        day.addMeal(meal2);
        day.addMeal(meal3);
        assertEquals("ham\nbread\nmayo\n", day.viewFoodListForDay());
    }


    @Test
    void testTotalCaloriesToday() {
        meal1.addFood(food1);
        meal2.addFood(food2);
        meal3.addFood(food3);
        meal3.addFood(food4);
        day.addMeal(meal1);
        day.addMeal(meal2);
        day.addMeal(meal3);
        assertEquals(2775, day.totalCaloriesToday());
    }

    @Test
    void testGoalSuccessFalse() {
        meal1.addFood(food1);
        meal3.addFood(food4);
        day.addMeal(meal1);
        day.addMeal(meal3);
        assertFalse(day.goalSuccess());
    }

    @Test
    void testGoalSuccessTrue() {
        meal1.addFood(food1);
        meal2.addFood(food2);
        day.addMeal(meal1);
        day.addMeal(meal2);
        assertTrue(day.goalSuccess());
    }

    @Test
    void testRemainingCal() {
        meal1.addFood(food1);
        meal2.addFood(food2);
        day.addMeal(meal1);
        day.addMeal(meal2);
        assertEquals(1225, day.remainingCal());
    }

}







