package model;

import persistance.Writable;
import org.json.JSONObject;


//EFFECTS: a food with the name and calories of food consumed
public class Food implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        return json;
    }


}
