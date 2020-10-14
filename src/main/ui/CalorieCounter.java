package ui;

import model.Day;
import model.Food;
import model.Meal;

import java.util.Scanner;

//Calorie Counter application
public class CalorieCounter {
    private Day day;
    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;
    private Scanner input;

    //EFFECTS: runs the Calorie Counter application
    public CalorieCounter() {
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
        } else if (command.equals("s")) {
            setGoal();
        } else if (command.equals("v")) {
            viewStats();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add food or remove food to meal");
        System.out.println("\ts -> set daily goal");
        System.out.println("\tv -> view stats");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: modifies the selected meal by
    // either removing or adding food
    private void modifyMeal() {
        System.out.println("Which meal would you like to change?");
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
        System.out.println("Your total calories today for breakfast are: " + breakfast.addCalories());
        System.out.println("Your total calories today for lunch are " + lunch.addCalories());
        System.out.println("Your total calories today for dinner are " + dinner.addCalories());
        System.out.println("Your total calories today are " + day.totalCaloriesToday());
        System.out.println("Your calorie goal for today is " + day.getGoal());
        System.out.println("Remaining calories until goal limit is reached is " + day.remainingCal());

    }

    // MODIFIES: this
    // EFFECTS: shows stats for your day
    private void setGoal() {
        System.out.println("Enter your goal in calories for today");
        int goal = input.nextInt();
        day.changeGoal(goal);

        viewStats();

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


}














