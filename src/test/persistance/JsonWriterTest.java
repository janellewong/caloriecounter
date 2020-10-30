package persistance;

import model.Day;
import model.Food;
import model.Meal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Day day = new Day(2000);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDay() {
        try {
            Day day = new Day(2000);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            day = reader.read();
            assertEquals(2000, day.getGoal());
            assertEquals(0, day.getNumberMeals());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDay() {
        try {
            Day day = new Day(2000);
            Meal meal1 = new Meal("breakfast");
            Meal meal2 = new Meal("lunch");
            Food food1 = new Food("pizza", 500);
            Food food2 = new Food("chips", 200);
            Food food3 = new Food("coffee", 50);

            day.addMeal(meal1);
            day.addMeal(meal2);
            meal1.addFood(food1);
            meal1.addFood(food3);
            meal2.addFood(food2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDay.json");
            day = reader.read();
            assertEquals(2000, day.getGoal());
            assertEquals(2, day.getNumberMeals());
            assertEquals("pizza\ncoffee\n", meal1.viewFoodListForMeal());

            checkMeal("breakfast", meal1);
            checkMeal("lunch", meal2);
            checkFood("pizza",500, food1);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
