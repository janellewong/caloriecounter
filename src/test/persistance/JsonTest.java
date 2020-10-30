package persistance;

import model.Food;
import model.Meal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMeal(String mealType, Meal meal) {
        assertEquals(mealType, meal.getMealType());
    }

    protected void checkFood(String name, Integer calories, Food food) {
        assertEquals(name, food.getName());
        assertEquals(calories, food.getCalories());
    }
}
