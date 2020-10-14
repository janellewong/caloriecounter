package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MealTest {
    Meal meal;
    Food food1;
    Food food2;
    Food food3;

    @BeforeEach
    void runBefore() {
        meal = new Meal("Breakfast");
        food1 = new Food("ham", 100, 145);
        food2 = new Food("bread", 300, 430);
        food3 = new Food("mayo", 100, 200);
    }

    @Test
    void testAddFood() {
        meal.addFood(food1);
        assertEquals(145, meal.addCalories());

    }

    @Test
    void testRemoveFood() {
        meal.addFood(food1);
        meal.removeFood(food1);
        assertEquals(0, meal.addCalories());

    }

    @Test
    void testAddCalories() {
        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);
        assertEquals(775, meal.addCalories());

    }

    @Test
    void testViewFoodListForMeal() {
        meal.addFood(food1);
        meal.addFood(food2);
        assertEquals("ham\nbread\n", meal.viewFoodListForMeal());
    }


}