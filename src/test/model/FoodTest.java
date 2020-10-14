package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {
    Food food;

    @BeforeEach
    void runBefore() {

        food = new Food("ham", 100, 145);
    }

    @Test
    void testChangeAmount() {
        food.changeAmount(500);
        assertEquals(500, food.getAmount());

    }

    @Test
    void testChangeCalories() {
        food.changeCalories(650);
        assertEquals(650, food.getCalories());
    }
}
