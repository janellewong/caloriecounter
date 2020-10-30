package ui;

import model.Day;
import model.Food;
import model.Meal;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Calorie Counter application
public class CalorieCounter {

    private static final String JSON_STORE = "./data/day.json";

    private Day day;
    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the Calorie Counter application
    public CalorieCounter() throws FileNotFoundException {
        runCounter();
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        day = new Day(1800);
        breakfast = new Meal("breakfast");
        lunch = new Meal("lunch");
        dinner = new Meal("dinner");
        input = new Scanner(System.in);
        day.addMeal(breakfast);
        day.addMeal(lunch);
        day.addMeal(dinner);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCounter() {
        boolean keepGoing = true;
        String command = null;

        init();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            modifyMeal();
            viewStats();
        } else if (command.equals("d")) {
            setGoal();
        } else if (command.equals("v")) {
            viewStats();
        } else if (command.equals("s")) {
            saveDay();
        } else if (command.equals("l")) {
            loadDay();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add food or remove food to meal");
        System.out.println("\td -> set daily goal");
        System.out.println("\tv -> view stats");
        System.out.println("\ts -> save to day");
        System.out.println("\tl -> load day");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: modifies the selected meal by
    // either removing or adding food
    private void modifyMeal() {
        Meal selected = selectMeal();
        String selection = "";

        while (!(selection.equals("a") || selection.equals("r"))) {
            System.out.println("a for Add food");
            System.out.println("r for Remove food");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("a")) {
            System.out.println("Enter name of food");
            String name = input.next();
            System.out.println("Enter calories of food");
            int calories = input.nextInt();
            Food food = new Food(name, calories);
            selected.addFood(food);

        } else if (selection.equals("r")) {
            System.out.println("Enter name of food you want to delete");
            String name = input.next();
            if (selected.containsFood(name)) {
                selected.removeFood(name);
            }
            System.out.println("There is no food with this name in this meal");
        }
    }


    // EFFECTS: shows stats for your day
    private void viewStats() {
        System.out.println("Foods you have eaten today are: ");
        System.out.println(day.viewFoodListForDay());
        Meal mealBreak = day.getMeals().get(0);
        Meal mealLunch = day.getMeals().get(1);
        Meal mealDin = day.getMeals().get(2);
        System.out.println("Your total calories today for breakfast are: " + mealBreak.addCalories());
        System.out.println("Your total calories today for lunch are " + mealLunch.addCalories());
        System.out.println("Your total calories today for dinner are " + mealDin.addCalories());
        //Integer totalCal = breakfast.addCalories() + lunch.addCalories() + dinner.addCalories();
        //System.out.println("Your total calories today are " + totalCal);
        System.out.println("Your total calories today are " + day.totalCaloriesToday());

        System.out.println("Your calorie goal for today is " + day.getGoal());

        //Integer calRemaining = day.getGoal() - totalCal;
        //System.out.println("Remaining calories until goal limit is reached is " + calRemaining);
        System.out.println("Remaining calories until goal limit is reached is " + day.remainingCal());

    }

    // MODIFIES: this
    // EFFECTS: sets goal for your day
    private void setGoal() {
        System.out.println("Enter your goal in calories for today");
        int goal = input.nextInt();
        day.changeGoal(goal);

        viewStats();
        saveDay();

    }

    // EFFECTS: prompts user to select which meal and returns it
    private Meal selectMeal() {
        String selection = "";

        while (!(selection.equals("b") || selection.equals("l") || selection.equals("d"))) {
            System.out.println("b for breakfast");
            System.out.println("l for lunch");
            System.out.println("d for dinner");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("b")) {
            return breakfast;
        } else if (selection.equals("l")) {
            return lunch;
        } else {
            return dinner;
        }
    }

    // EFFECTS: saves the day to file
    private void saveDay() {
        try {
            jsonWriter.open();
            jsonWriter.write(day);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads day from file
    private void loadDay() {
        try {
            day = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }




}














