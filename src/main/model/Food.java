package model;

public class Food {
    private String name;          //name of the food or drink
    private int calories;         //calories of food consumed


    public Food(String name, int cal) {
        this.name = name;
        calories = cal;
    }


    //MODIFIES: this
    //EFFECTS: changes calories to new calories
    public void changeCalories(int newCalories) {
        calories = newCalories;
    }


    // EFFECTS: returns calories
    public int getCalories() {
        return calories;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }


}
