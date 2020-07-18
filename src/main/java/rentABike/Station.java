package rentABike;

import java.util.ArrayList;

public class Station {

    static Integer count = 0;
    private Integer stationID;
    private String location;
    private Integer capacity;
    private Integer freeSpaces;
    private ArrayList<Bike> bikes = new ArrayList<>();


    public Station(String location, Integer capacity) {
        this.location = location;
        this.capacity = capacity;
        this.stationID = count;
        this.freeSpaces = capacity;
        count++;

    }

    public Integer getStationID() {
        return stationID;
    }


    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }


    public ArrayList<Integer> getBikes() {
        ArrayList<Integer> listBikes = new ArrayList<>();
        for (Bike bike : bikes) {
            listBikes.add(bike.getBikeID());

        }
        return listBikes;
    }

    public Bike getFreeBike() {
        for (Bike bike : bikes) {
            if (bike.getState().equals("CanBeRented")) {
                return bike;
            }
        }
        System.out.println("No bike available");
        return null;
    }

    public void addBike(Bike bike) {
        if (bikes.size() < freeSpaces) {
            this.bikes.add(bike);
            System.out.printf("Bike %d added \n", bike.getBikeID());
            freeSpaces--;
            System.out.println("Thanks for using RentABike");
            bike.setStateTrue("CanBeRented");
        } else {
            System.out.println("Station Full - Can not add bike!!!");
        }
    }

    public void removeBike(Bike bike) {
        if (bikes.contains(bike)) {
            bikes.remove(bike);
            System.out.printf("\n Bike Number %d can now be used ! \n", bike.getBikeID());
            freeSpaces++;
            bike.setStateTrue("CanNotBeRented");

        } else {
            System.out.printf("\n Bike Number %d not in this station !", bike.getBikeID());
        }
    }

    public Integer getFreeSpaces() {
        return freeSpaces;
    }

}
