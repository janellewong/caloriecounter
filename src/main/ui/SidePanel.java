package ui;

import model.Day;
import model.Food;
import model.Meal;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SidePanel extends JPanel {

    GridBagConstraints gc;

    protected static Meal selected;
    Food food;
    int totalCalories;
    int caloriesRemaining;

    JLabel totalCaloriesNumber;
    JLabel caloriesRemainingNumber;

    JButton addFood;
    JButton removeFood;
    JButton setGoal;

    JLabel totalCals;
    JLabel totalCalsRemaining;

    JLabel mealAdd;
    JButton breakfast;
    JButton lunch;
    JButton dinner;

    JLabel foodNameDescription;
    JTextField foodName;
    JButton foodNameSubmit;

    JLabel caloriesAddDescription;
    JTextField caloriesAdd;
    JButton caloriesAddSubmit;

    JLabel newGoalDescription;
    JTextField caloriesGoal;
    JButton caloriesGoalSubmit;

    MainFrame mainPanel;

    protected static Meal breakfast1 = new Meal("breakfast");
    protected static Meal lunch1 = new Meal("lunch");
    protected static Meal dinner1 = new Meal("dinner");

    protected static Day day = new Day(1800);

    private static final String DAY_FILE = "./data/day.json";
    protected static JsonWriter jsonWriter = new JsonWriter(DAY_FILE);
    protected static JsonReader jsonReader = new JsonReader(DAY_FILE);




    public SidePanel(MainFrame mainPanel) {
        this.mainPanel = mainPanel;
        setPanelDimensions();
        setBackground(Color.GRAY);
        loadDay();

        updateNum();
        totalCaloriesNumber = new JLabel(Integer.toString(totalCalories));
        caloriesRemainingNumber = new JLabel(Integer.toString(caloriesRemaining));

        initializeComp();

        mainScreen();

    }

    public void setPanelDimensions() {
        Dimension size = getPreferredSize();
        size.width = 300;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initializeComp() {
        //main screen
        addFood = new JButton("Add Food");
        removeFood = new JButton("Remove Food");
        setGoal = new JButton("Set Goal");

        totalCals = new JLabel("Total Calories:");
        totalCalsRemaining = new JLabel("Calories Remaining to Goal:");

        //Add & remove
        mealAdd = new JLabel("Which meal would you like to add or remove food to?");
        breakfast = new JButton("Breakfast");
        lunch = new JButton("Lunch");
        dinner = new JButton("Dinner");

        foodNameDescription = new JLabel("Enter name of food:");
        foodName = new JTextField(20);
        foodNameSubmit = new JButton("Submit");

        caloriesAddDescription = new JLabel("How many calories is your food?");
        caloriesAdd = new JTextField(10);
        caloriesAddSubmit = new JButton("Submit");

        //set goal
        newGoalDescription = new JLabel("Set your new goal:");
        caloriesGoal = new JTextField(10);
        caloriesGoalSubmit = new JButton("Submit");

    }

    public void mainScreen() {
        updateNum();

        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        mainButtons();
        totalCals.setVisible(true);
        totalCalsRemaining.setVisible(true);
        totalCaloriesNumber.setVisible(true);
        caloriesRemainingNumber.setVisible(true);
        mainStats();

        //Initialize Everything
        initializeQuestions();
        initializeAnswers();
        initializeSubmit();
        initializeMealOptions();
        initializeMealQuestion();

        mainOptions();
    }

    public void mainOptions() {

        // if addFood button is pressed
        addFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireMealEventAdd();
            }
        });

        // if removeFood button is pressed
        removeFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireMealEventRemove();
            }
        });

        // if setGoal button is pressed
        setGoal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireSetGoal();
            }
        });

    }

    public void mainButtons() {
        addFood.setVisible(true);
        removeFood.setVisible(true);
        setGoal.setVisible(true);

        gc.gridx = 0;
        gc.gridy = 0;
        add(addFood,gc);
        addFood.setPreferredSize(new Dimension(200, 80));

        gc.gridx = 0;
        gc.gridy = 1;
        add(removeFood,gc);
        removeFood.setPreferredSize(new Dimension(200, 80));


        gc.gridx = 0;
        gc.gridy = 2;
        add(setGoal,gc);
        setGoal.setPreferredSize(new Dimension(200, 80));


    }

    public void mainStats() {
        gc.gridx = 0;
        gc.gridy = 3;
        add(totalCals,gc);
        totalCals.setFont(new Font("Bold",100,20));
        totalCals.setForeground(Color.white);

        gc.gridx = 0;
        gc.gridy = 4;
        add(totalCaloriesNumber,gc);
        totalCaloriesNumber.setFont(new Font("Bold",100,30));
        totalCaloriesNumber.setForeground(Color.white);

        gc.gridx = 0;
        gc.gridy = 5;
        add(totalCalsRemaining,gc);
        totalCalsRemaining.setFont(new Font("Bold",100,20));
        totalCalsRemaining.setForeground(Color.white);

        gc.gridx = 0;
        gc.gridy = 6;
        add(caloriesRemainingNumber,gc);
        caloriesRemainingNumber.setFont(new Font("Bold",100,30));
        caloriesRemainingNumber.setForeground(Color.white);

    }


    public void initializeQuestions() {
        gc.gridx = 0;
        gc.gridy = 0;

        add(newGoalDescription,gc);
        newGoalDescription.setVisible(false);
        add(foodNameDescription,gc);
        foodNameDescription.setVisible(false);
        add(caloriesAddDescription,gc);
        caloriesAddDescription.setVisible(false);

    }

    public void initializeAnswers() {
        gc.gridx = 0;
        gc.gridy = 1;

        add(caloriesGoal,gc);
        caloriesGoal.setVisible(false);
        add(caloriesAdd,gc);
        caloriesAdd.setVisible(false);
        add(foodName,gc);
        foodName.setVisible(false);

    }

    public void initializeSubmit() {
        gc.gridx = 0;
        gc.gridy = 2;

        add(foodNameSubmit,gc);
        foodNameSubmit.setVisible(false);
        add(caloriesAddSubmit,gc);
        caloriesAddSubmit.setVisible(false);
        add(caloriesGoalSubmit,gc);
        caloriesGoalSubmit.setVisible(false);

    }

    public void initializeMealOptions() {
        gc.gridx = 0;
        gc.gridy = 7;

        add(breakfast,gc);
        breakfast.setVisible(false);

        gc.gridx = 0;
        gc.gridy = 9;
        add(lunch,gc);
        lunch.setVisible(false);

        gc.gridx = 0;
        gc.gridy = 11;
        add(dinner,gc);
        dinner.setVisible(false);

    }

    public void initializeMealQuestion() {
        gc.gridx = 0;
        gc.gridy = 3;

        add(mealAdd,gc);
        mealAdd.setVisible(false);

    }

    public void fireMealEventAdd() {
        mealVisibility();

        // if breakfast is pressed
        breakfast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(0);
                fireNameEventAdd();

            }
        });

        // if lunch is pressed
        lunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(1);
                fireNameEventAdd();

            }
        });

        // if dinner is pressed
        dinner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(2);
                fireNameEventAdd();

            }
        });

    }

    public void fireMealEventRemove() {
        mealVisibility();

        // if breakfast is pressed
        breakfast.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(0);
                fireNameEventRemove();

            }
        });

        // if lunch is pressed
        lunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(1);
                fireNameEventRemove();

            }
        });

        // if dinner is pressed
        dinner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = day.getMeals().get(2);
                fireNameEventRemove();

            }
        });

    }

    public void mealVisibility() {
        addFood.setVisible(false);
        totalCals.setVisible(false);
        totalCaloriesNumber.setVisible(false);
        caloriesRemainingNumber.setVisible(false);
        totalCalsRemaining.setVisible(false);
        removeFood.setVisible(false);
        setGoal.setVisible(false);
        mealAdd.setVisible(true);
        breakfast.setVisible(true);
        lunch.setVisible(true);
        dinner.setVisible(true);
    }

    public void fireNameEventAdd() {
        mealAdd.setVisible(false);
        breakfast.setVisible(false);
        lunch.setVisible(false);
        dinner.setVisible(false);
        foodNameDescription.setVisible(true);
        foodName.setVisible(true);
        foodNameSubmit.setVisible(true);

        // if submit button is pressed
        foodNameSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireFoodCaloriesEvent();

            }
        });

    }

    public void fireNameEventRemove() {
        mealAdd.setVisible(false);
        breakfast.setVisible(false);
        lunch.setVisible(false);
        dinner.setVisible(false);
        foodNameDescription.setVisible(true);
        foodName.setVisible(true);
        foodNameSubmit.setVisible(true);

        // if submit button is pressed
        foodNameSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireRemoveProcess();
                mainScreen();

            }
        });


    }

    public void fireFoodCaloriesEvent() {
        foodNameDescription.setVisible(false);
        foodName.setVisible(false);
        foodNameSubmit.setVisible(false);
        caloriesAdd.setVisible(true);
        caloriesAddDescription.setVisible(true);
        caloriesAddSubmit.setVisible(true);

        // if submit button is pressed
        caloriesAddSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireAddProcess();

            }
        });

    }

    public void fireAddProcess() {
        food = new Food(foodName.getText(), Integer.parseInt(caloriesAdd.getText()));
        selected.addFood(food);
        saveDay();
        mainPanel.refresh();
        updateNum();
        mainScreen();

    }

    public void fireRemoveProcess() {
        selected.removeFood(foodName.getText());
        saveDay();
        mainPanel.refresh();
        updateNum();
        mainScreen();
    }


    public void fireSetGoal() {
        addFood.setVisible(false);
        totalCaloriesNumber.setVisible(false);
        caloriesRemainingNumber.setVisible(false);
        totalCals.setVisible(false);
        totalCalsRemaining.setVisible(false);
        removeFood.setVisible(false);
        setGoal.setVisible(false);
        caloriesGoal.setVisible(true);
        newGoalDescription.setVisible(true);
        caloriesGoalSubmit.setVisible(true);

        // if submit button is pressed
        caloriesGoalSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                day.changeGoal(Integer.parseInt(caloriesGoal.getText()));
                saveDay();
                mainPanel.refresh();
                updateNum();
                mainScreen();

            }
        });

    }











    // EFFECTS: saves the day to file
    private void saveDay() {
        try {
            jsonWriter.open();
            jsonWriter.write(day);
            jsonWriter.close();
            System.out.println("Saved to " + DAY_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + DAY_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads day from file
    private void loadDay() {
        try {
            day = jsonReader.read();
        } catch (IOException e) {
            init();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Day
    private void init() {
        day = new Day(1800);
        day.addMeal(breakfast1);
        day.addMeal(lunch1);
        day.addMeal(dinner1);
    }

    public void updateNum() {
        totalCalories = day.getMeals().get(0).addCalories() + day.getMeals().get(1).addCalories()
                + day.getMeals().get(2).addCalories();
        caloriesRemaining = day.getGoal() - totalCalories;
        totalCaloriesNumber = new JLabel(Integer.toString(totalCalories));
        caloriesRemainingNumber = new JLabel(Integer.toString(caloriesRemaining));


    }


}
