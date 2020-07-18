package rentABike;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        ArrayList<String[]> StationsData = new ArrayList<>();
        ArrayList<String[]> UsersData = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Path currentRelativePath = Paths.get("");
        String projectPath = currentRelativePath.toAbsolutePath().toString();

        //Getting Stations Data
        try {
            Reader reader = new FileReader(projectPath + "/src/main/java/rentABike/StationsData.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            ArrayList StationsDataRaw = (ArrayList) jsonObject.get("stations");

            for (int i = 0; i < StationsDataRaw.size(); i++) {
                JSONObject station = (JSONObject) StationsDataRaw.get(i);
                String[] StationData = {station.get("location").toString(), station.get("capacity").toString()};

                StationsData.add(StationData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Bike Objects
        HashMap<Integer, Bike> bikes = new HashMap<>();

        //Station Objects
        HashMap<Integer, Station> stations = new HashMap<>();

        //UserObjects
        HashMap<Integer, User> users = new HashMap<>();

        //Data for Bikes
        String[] bikeData = {"black", "blue", "red", "black", "white", "red", "blue", "white"};


        //Creating and saving Bike Objects
        for (String bikeDataSet : bikeData) {
            Bike bike = new Bike(bikeDataSet);
            bikes.put(bike.getBikeID(), bike);

        }

        // Create and save Station Objects
        for (String[] item : StationsData) {
            Station station = new Station(item[0], Integer.parseInt(item[1]));
            stations.put(station.getStationID(), station);
        }

        //Adding 3 Bikes to station 0

        for (int i = 0; i < 3; i++) {
            stations.get(0).addBike(bikes.get(i));
        }

        //Adding 4 Bikes to station1
        for (int i = 3; i < 7; i++) {
            stations.get(1).addBike(bikes.get(i));
        }

        //register a user and saving data into UsersData.json
        Registration registration1 = new Registration("Cornelia", "Franta", "23.11.97", "cornelia.f@gmail.com", "SecretPassword");

        registration1.saveUser();

        //Getting User Data
        try {
            Reader reader = new FileReader(projectPath + "/src/main/java/rentABike/UsersData.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            ArrayList UserDataRaw = (ArrayList) jsonObject.get("users");

            for (int i = 0; i < UserDataRaw.size(); i++) {
                JSONObject user = (JSONObject) UserDataRaw.get(i);
                String[] UserData = {user.get("name").toString(), user.get("surname").toString(), user.get("dateOfBirth").toString(), user.get("email").toString(),};
                UsersData.add(UserData);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


        //Creating and Saving User Objects

        for (String[] userData : UsersData) {

            User user = new User(userData[0], userData[1]);
            users.put(user.getUserID(), user);
        }


        // demonstrating rental procedure

        User user = users.get(0);
        System.out.println(stations.get(0).getBikes());
        System.out.println(stations.get(0).getFreeSpaces());
        user.rentBike(stations.get(0).getFreeBike(), stations.get(0), 143000, 163000, "12.04.2020");
        System.out.println(users.get(0).getRentingStatus());
        System.out.println(stations.get(0).getBikes());
        System.out.println(stations.get(0).getFreeSpaces());
        System.out.println(users.get(0).getCurrentlyRentedBike().getBikeID());
        System.out.println(stations.get(1).getFreeSpaces());
        System.out.println(stations.get(1).getBikes());
        user.returnBike(users.get(0).getCurrentlyRentedBike(), stations.get(1), 163100);
        System.out.println(users.get(0).getRentingStatus());
        System.out.println(users.get(0).getCurrentlyRentedBike());
        System.out.println(stations.get(1).getFreeSpaces());
        System.out.println(stations.get(1).getBikes());

        user.rentBike(stations.get(1).getFreeBike(), stations.get(1), 124502, 180005, "23.06.2020");
        user.returnBike(user.getCurrentlyRentedBike(), stations.get(2), 180005);


        for (String item : user.getRentingHistory()) {
            System.out.println(item);
        }


    }

}
