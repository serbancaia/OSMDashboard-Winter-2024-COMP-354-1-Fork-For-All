package de.storchp.opentracks.osmplugin.dashboardapi;

import java.util.List;


public class Runs
{
    private String name;
    private double averageSpeed;
    private double distance;
    private int runtime;
    private double maxSpeed; //Going to use Trackpoint.Speed variable

    //Constructors
    public Runs()
    {
        this.name = "unnamed";
        this.averageSpeed = 0;
        this.distance = 0;
        this.runtime = 0;
        this.maxSpeed = 0;
    }

    public Runs(String inName, int inAvgSpeed, int inDistance, int inRunTime, double inMaxSpeed)
    {
        this.name = inName;
        this.averageSpeed = inAvgSpeed;
        this.distance = inDistance;
        this.runtime = inRunTime;
        this.maxSpeed = inMaxSpeed;
    }

    public Runs(String inName, int inRunTime, double inMaxSpeed)
    {
        this.name = inName;
        this.runtime = inRunTime;
        this.maxSpeed = inMaxSpeed;
    }

    public Runs(Runs runsObj)
    {
        this.name = runsObj.name;
        this.averageSpeed = runsObj.averageSpeed;
        this.distance = runsObj.distance;
        this.runtime = runsObj.runtime;
        this.maxSpeed = runsObj.maxSpeed;
    }

    //Getters
    public String getName()
    {
        return this.name;
    }

    public int getRuntime()
    {
        return this.runtime;
    }

    public double getMaxSpeed()
    {
        return this.maxSpeed;
    }

    //Setters
    public void setName(String inName)
    {
        this.name = inName;
    }

    public void setRuntime(int inRunTime)
    {
        this.runtime = inRunTime;
    }

    public void setMaxSpeed(double inMaxSpeed)
    {
        this.maxSpeed = inMaxSpeed;
    }

    /**
     * The real data  for the total run time is suppose to be fetch from a "statistic team". For , we will implement a small
     * algorithm to calculate time and will Dummy data for Sprint 2
     *
     */
    private double calculateTotalRunTime(List<TrackPoint> trackPointCollection)
    {
        double returnValue;

        TrackPoint firstPoint = trackPointCollection.get(0);
        TrackPoint lastPoint = trackPointCollection.get(trackPointCollection.size()-1);

        returnValue = Math.abs(firstPoint.getTimeMillis()- lastPoint.getTimeMillis());

        return returnValue;
    }

    //Method to calculate the max speed
    //Important to know that on OPENTRACKS at class TrackStatistics have a attribute total Duration time
    private double calculateMaxSpeed(List<TrackPoint> trackPointCollection)
    {
        double maximumSpeed = trackPointCollection.get(0).getSpeed();

        for (int i=0; i < trackPointCollection.size(); i++)
        {
            if (maximumSpeed < trackPointCollection.get(i).getSpeed())
            {
                maximumSpeed = trackPointCollection.get(i).getSpeed();
            }
        }

        return maximumSpeed;
    }
}

