package model;

public class Request {
    private final int floor;
    private final int userName;

    public Request(int floor, int userName) {
        this.floor = floor;
        this.userName = userName;
    }

    public int getFloor() {
        return floor;
    }

    public int getUserName() {
        return userName;
    }
}
