package persistance;

import model.Day;
import model.Food;
import model.Meal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Day day = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDay.json");
        try {
            Day day = reader.read();
            assertEquals(2000, day.getGoal());
            assertEquals(0, day.getNumberMeals());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDay() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDay.json");
        try {
            Day day = reader.read();
            assertEquals(2000, day.getGoal());
            assertEquals(2, day.getNumberMeals());
            List<Meal> meals = day.getMeals();
            assertEquals(2, meals.size());

            checkMeal("breakfast", meals.get(0));
            checkMeal("lunch", meals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
