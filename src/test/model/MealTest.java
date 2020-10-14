package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MealTest {
    Meal meal;
    Food food1;
    Food food2;
    Food food3;

    @BeforeEach
    void runBefore() {
        meal = new Meal("Breakfast");
        food1 = new Food("ham", 100);
        food2 = new Food("bread", 300);
        food3 = new Food("mayo", 100);
    }

    @Test
    void testAddFood() {
        meal.addFood(food1);
        assertEquals(100, meal.addCalories());

    }

    @Test
    void testRemoveFoodTrue() {
        meal.addFood(food1);
        meal.removeFood("ham");
        assertEquals(0, meal.addCalories());

    }

    @Test
    void testRemoveFoodFalse() {
        meal.addFood(food1);
        meal.removeFood("bread");
        assertEquals(100, meal.addCalories());

    }

    @Test
    void testContainsFoodTrue() {
        meal.addFood(food1);
        assertTrue(meal.containsFood("ham"));
    }

    @Test
    void testContainsFoodFalse() {
        meal.addFood(food1);
        assertFalse(meal.containsFood("bread"));
    }

    @Test
    void testAddCalories() {
        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);
        assertEquals(500, meal.addCalories());

    }

    @Test
    void testViewFoodListForMeal() {
        meal.addFood(food1);
        meal.addFood(food2);
        assertEquals("ham\nbread\n", meal.viewFoodListForMeal());
    }


}