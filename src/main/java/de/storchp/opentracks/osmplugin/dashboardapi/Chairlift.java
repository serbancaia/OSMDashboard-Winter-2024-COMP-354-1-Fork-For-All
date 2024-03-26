package de.storchp.opentracks.osmplugin.dashboardapi;

import java.util.List;

public class Chairlift {
    private String name;
    private double elevationGain;
    private double distance;
    private long waitingTime;
    private double averageSpeed;

    public Chairlift(String name, double eGain, int distance, long wTime, double aSpeed) {
        this.name = name;
        this.elevationGain = eGain;
        this.distance = distance;
        this.waitingTime = wTime;
        this.averageSpeed = aSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAscentTime() {
        return Math.round(distance/averageSpeed);
    }

    public double getElevationGain() { return elevationGain; }

    public void setElevationGain(double elevGain) { this.elevationGain = elevGain; }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
    // Method to display chairlift information in a table
    // To be replaced with a GUI based table
    public void displayTable() {
        System.out.println("+---------------------------------------+");
        System.out.println("|            Chairlift Info             |");
        System.out.println("+---------------------------------------+");
        System.out.printf("| %-20s | %-10s |\n", "Name", name);
        System.out.printf("| %-20s | %-10.2f |\n", "Elevation Gain", elevationGain);
        System.out.printf("| %-20s | %-10d |\n", "Waiting Time", waitingTime);
        System.out.printf("| %-20s | %-10.2f |\n", "Average Speed", averageSpeed);
        System.out.println("+---------------------------------------+");
    }
}
