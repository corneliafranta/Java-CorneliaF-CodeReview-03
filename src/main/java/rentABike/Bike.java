package rentABike;

import java.util.ArrayList;
import java.util.HashMap;

public class Bike {
    static int bikeCount = 0;
    private int bikeID;
    private String color;
    private HashMap<String, Boolean> state = new HashMap<String, Boolean>();

    public Bike(String color) {
        this.bikeID = bikeCount;
        this.color = color;
        this.state.put("CanBeRented", true);
        this.state.put("CanNotBeRented", false);
        this.state.put("InService", false);
        this.state.put("Discarded", false);
        bikeCount++;
    }


    public int getBikeID() {
        return bikeID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getState() {
        ArrayList<String> keys = new ArrayList<String>();
        String returnValue = "";
        for (String key : state.keySet()) {
            Boolean value = state.get(key);
            if (value) {
                returnValue = key;

            }


        }
        return returnValue;
    }


    public void setStateTrue(String key) {

        for (String itemkey: state.keySet()){
            state.replace(itemkey, false);
        }
        this.state.replace(key, true);
    }


    public HashMap<String, Boolean> getStatusMap(){
        return state;
    }
}
