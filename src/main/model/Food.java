package model;

public class Food {
    private String name;          //name of the food or drink
    private int amount;           //grams of food or mL of drink consumed
    private int calories;         //calories of food consumed


    public Food(String name, int amt, int cal) {
        this.name = name;
        amount = amt;
        calories = cal;
    }

    //MODIFIES: this
    //EFFECTS: changes amount to new amount
    public void changeAmount(int newAmount) {
        amount = newAmount;
    }

    //MODIFIES: this
    //EFFECTS: changes calories to new calories
    public void changeCalories(int newCalories) {
        calories = newCalories;
    }

    // EFFECTS: returns amount
    public int getAmount() {
        return amount;
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
