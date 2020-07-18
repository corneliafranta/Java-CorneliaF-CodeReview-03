package rentABike;

public class Rent {
    private int bikeID;
    private int rentStart;
    private int rentEnd;
    private Integer user;
    private String date;

    public Rent(int bikeID, int rentStart, int rentEnd, Integer user, String date) {
        this.bikeID = bikeID;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.user = user;
        this.date = date;

    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public int getRentStart() {
        return rentStart;
    }

    public void setRentStart(int rentStart) {
        this.rentStart = rentStart;
    }

    public int getRentEnd() {
        return rentEnd;
    }

    public String getDate() {
        return date;
    }

    public void setRentEnd(int rentEnd) {
        this.rentEnd = rentEnd;
    }

    public Integer getUser() {

        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }


    public Boolean checkOverHours(int time) {
        return time > this.rentEnd;


    }

}
