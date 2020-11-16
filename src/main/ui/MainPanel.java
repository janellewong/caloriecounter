package ui;

import model.Day;
import model.Food;
import model.Meal;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {

    int calBreakfast;
    int calLunch;
    int calDinner;
    int calorieGoal;

    JLabel calBreakfastLabel;
    JLabel calLunchLabel;
    JLabel calDinnerLabel;
    JLabel calorieGoalLabel;

    JLabel myDay;
    JLabel goal;
    JLabel breakfastLabel;
    JLabel lunchLabel;
    JLabel dinnerLabel;

    GridBagConstraints gc = new GridBagConstraints();

    JList<Food> tempListB = new JList<>();
    JList<Food> tempListL = new JList<>();
    JList<Food> tempListD = new JList<>();

    DefaultListModel<Food> tempModelB = new DefaultListModel<>();
    DefaultListModel<Food> tempModelL = new DefaultListModel<>();
    DefaultListModel<Food> tempModelD = new DefaultListModel<>();


    JList<String> listOfBreakfast = new JList<>();
    JList<String> listOfLunch = new JList<>();
    JList<String> listOfDinner = new JList<>();

    DefaultListModel<String> modelB = new DefaultListModel<>();
    DefaultListModel<String> modelL = new DefaultListModel<>();
    DefaultListModel<String> modelD = new DefaultListModel<>();


    protected static Day day = SidePanel.day;
    protected static Meal breakfast1 = SidePanel.breakfast1;
    protected static Meal lunch1 = SidePanel.lunch1;
    protected static Meal dinner1 = SidePanel.dinner1;
    private static final String DAY_FILE = "./data/day.json";
    protected static JsonReader jsonReader = new JsonReader(DAY_FILE);



    public MainPanel() {
        setPanelDimensions();
        loadDay();

        day.addMeal(breakfast1);
        day.addMeal(lunch1);
        day.addMeal(dinner1);

        initializeComp();
        updateNumbersMain();
        setLayout(new GridBagLayout());

        titleLabels();
        positionLabels();
        positionNumbers();

        gc.weightx = 1;
        gc.weighty = 1;

        initializeBreakfastList();
        initializeLunchList();
        initializeDinnerList();

    }

    // EFFECTS: initializes the breakfast list
    public void initializeBreakfastList() {
        Meal breakfast = day.getMeals().get(0);

        tempListB.setModel(tempModelB);
        listOfBreakfast.setModel(modelB);

        for (Food f: breakfast.getFood()) {
            tempModelB.addElement(f);
        }

        for (int k = 0; k < tempModelB.size(); k++) {
            modelB.addElement(tempModelB.get(k).getName());
        }
        gc.gridx = 0;
        gc.gridy = 3;

        add(listOfBreakfast,gc);
        listOfBreakfast.setVisible(true);
    }

    // EFFECTS: initializes the lunch list
    public void initializeLunchList() {
        Meal lunch = day.getMeals().get(1);

        tempListL.setModel(tempModelL);
        listOfLunch.setModel(modelL);

        for (Food f: lunch.getFood()) {
            tempModelL.addElement(f);
        }

        for (int k = 0; k < tempModelL.size(); k++) {
            modelL.addElement(tempModelL.get(k).getName());
        }
        gc.gridx = 0;
        gc.gridy = 5;

        add(listOfLunch,gc);
        listOfLunch.setVisible(true);
    }

    // EFFECTS: initializes the dinner list
    public void initializeDinnerList() {
        Meal dinner = day.getMeals().get(2);

        tempListD.setModel(tempModelD);
        listOfDinner.setModel(modelD);

        for (Food f: dinner.getFood()) {
            tempModelD.addElement(f);
        }

        for (int k = 0; k < tempModelD.size(); k++) {
            modelD.addElement(tempModelD.get(k).getName());
        }
        gc.gridx = 0;
        gc.gridy = 7;

        add(listOfDinner,gc);
        listOfDinner.setVisible(true);
    }

    // EFFECTS: initializes labels
    public void initializeComp() {

        myDay = new JLabel("My Day");
        goal = new JLabel("My Goal Today:");
        breakfastLabel = new JLabel("Breakfast");
        lunchLabel = new JLabel("Lunch");
        dinnerLabel = new JLabel("Dinner");

    }
    // EFFECTS: sets position of labels
    public void titleLabels() {
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.gridy = 0;
        add(myDay,gc);
        myDay.setFont(new Font("Bold",100,50));
        myDay.setVisible(true);

        gc.gridx = 0;
        gc.gridy = 1;
        add(goal,gc);
        goal.setFont(new Font("Bold",100,30));
        goal.setVisible(true);
    }

    // EFFECTS: sets position of labels
    public void positionLabels() {

        gc.gridx = 0;
        gc.gridy = 2;
        add(breakfastLabel,gc);
        breakfastLabel.setFont(new Font("Bold",100,30));
        breakfastLabel.setVisible(true);

        gc.gridx = 0;
        gc.gridy = 4;
        add(lunchLabel,gc);
        lunchLabel.setFont(new Font("Bold",100,30));
        lunchLabel.setVisible(true);

        gc.gridx = 0;
        gc.gridy = 6;
        add(dinnerLabel,gc);
        dinnerLabel.setFont(new Font("Bold",100,30));
        dinnerLabel.setVisible(true);
    }

    // EFFECTS: sets position of numbers
    public void positionNumbers() {

        gc.gridx = 2;
        gc.gridy = 1;
        add(calorieGoalLabel,gc);
        calorieGoalLabel.setFont(new Font("Bold",100,30));
        calorieGoalLabel.setVisible(true);

        gc.gridx = 2;
        gc.gridy = 2;
        add(calBreakfastLabel,gc);
        calBreakfastLabel.setFont(new Font("Bold",100,30));
        calBreakfastLabel.setVisible(true);

        gc.gridx = 2;
        gc.gridy = 4;
        add(calLunchLabel,gc);
        calLunchLabel.setFont(new Font("Bold",100,30));
        calLunchLabel.setVisible(true);

        gc.gridx = 2;
        gc.gridy = 6;
        add(calDinnerLabel,gc);
        calDinnerLabel.setFont(new Font("Bold",100,30));
        calDinnerLabel.setVisible(true);



    }

    // EFFECTS: sets panel dimensions
    public void setPanelDimensions() {
        Dimension size = getPreferredSize();
        size.width = 600;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // MODIFIES: this
    // EFFECTS: loads day from file, if that file exists;
    // otherwise initializes accounts with default values
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
    }

    // EFFECTS: updates the stats of the day
    protected void updateNumbersMain() {
        Meal mealBreak = day.getMeals().get(0);
        Meal mealLunch = day.getMeals().get(1);
        Meal mealDin = day.getMeals().get(2);
        calBreakfast = mealBreak.addCalories();
        calLunch = mealLunch.addCalories();
        calDinner = mealDin.addCalories();
        calorieGoal = day.getGoal();
        calBreakfastLabel = new JLabel(Integer.toString(calBreakfast) + " calories");
        calLunchLabel = new JLabel(Integer.toString(calLunch) + " calories");
        calDinnerLabel = new JLabel(Integer.toString(calDinner) + " calories");
        calorieGoalLabel = new JLabel(Integer.toString(calorieGoal) + " calories");

    }



}
