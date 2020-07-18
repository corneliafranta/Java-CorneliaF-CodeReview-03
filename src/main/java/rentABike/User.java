package rentABike;

import java.util.ArrayList;

public class User {

    static int count = 0;
    private int userID;
    private String name;
    private String surname;
    private Bike currentlyRentedBike;
    private Rent currentRentingStatus;
    private ArrayList <Rent> rentingHistory = new ArrayList<>();


    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.userID = count;
        count++;

    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserData() {
        return String.format("User %d \n ============= \n %s %s \n %b \n", this.userID, this.name, this.surname, this.currentlyRentedBike);
    }

    public Rent getRentingStatus() {
        return this.currentRentingStatus;
    }

    public ArrayList<String> getRentingHistory(){
        ArrayList <String> output = new ArrayList<>();
        for (Rent item : rentingHistory){
            String outputItem = String.format("Rented Bike: %d \n------------------\n on the: %s, \n from: %d, until %d \n", item.getBikeID(), item.getDate(), item.getRentStart(), item.getRentEnd());
            output.add(outputItem);
        }
        return output;
    }

    public Bike getCurrentlyRentedBike() {
        return currentlyRentedBike;
    }

    public void setCurrentlyRentedBike(Bike currentlyRentedBike) {
        if (this.currentlyRentedBike == currentlyRentedBike) {
            this.currentlyRentedBike = null;
        }
        this.currentlyRentedBike = currentlyRentedBike;
    }


    public void rentBike(Bike bike, Station station, int start, int end, String date) {
        station.removeBike(bike);
        setCurrentlyRentedBike(bike);
        System.out.printf("\n %s is using Bike Number %d \n", this.name, this.currentlyRentedBike.getBikeID());
        Rent rent =new Rent(bike.getBikeID(), start, end, this.getUserID(), date);
        this.currentRentingStatus = rent;
        this.rentingHistory.add(rent);


    }


    public void returnBike(Bike bike, Station station, int returnTime) {
        station.addBike(bike);
        setCurrentlyRentedBike(bike);
        Boolean overHours = this.currentRentingStatus.checkOverHours(returnTime);
        if (overHours) {
            System.out.println("You have returned the bike to late....");

        }
        this.currentRentingStatus = null;


    }


}
