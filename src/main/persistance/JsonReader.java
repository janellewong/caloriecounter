package persistance;

import model.Day;
import model.Food;
import model.Meal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code modified from JsonSerializationDemo

// Represents a reader that reads day info from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads day from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        Integer goal = jsonObject.getInt("goal");
        Day day = new Day(goal);
        addMeals(day, jsonObject);
        return day;
    }

    // MODIFIES: day
    // EFFECTS: parses meals from JSON object and adds them to day
    private void addMeals(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("day");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(day, nextMeal);
        }
    }

    /// MODIFIES: day
    // EFFECTS: parses meal from JSON object and adds it to day
    private void addMeal(Day day, JSONObject jsonObject) {
        String mealType = jsonObject.getString("meal type");
        Meal meal = new Meal(mealType);
        day.addMeal(meal);
        addFoods(meal, jsonObject);
    }

    // MODIFIES: meal
    // EFFECTS: parses foods from JSON object and adds it to meal
    private void addFoods(Meal meal, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meal");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(meal, nextFood);
        }
    }

    // MODIFIES: meal
    // EFFECTS: parses food from JSON object and adds it to meal
    private void addFood(Meal meal, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer calories = jsonObject.getInt("calories");
        Food food = new Food(name, calories);
        meal.addFood(food);
    }
}
