package de.storchp.opentracks.osmplugin.dashboardapi;

import java.util.List;

/**
 * (Group 17) This object class represents a run based on data from recorded tracks.
 * It contains methods that can bu used to display relevant information about a user's
 * skiing track.
 *
 * This class is used to provide dummy data for group 17's which involves displaying
 * certain run statistics in OSMDashboard until the statistics team implement their
 * functionalities involving passing this necessary data to the teams working on
 * OSMDashboard.
 *
 * @author danbin & serbancaia
 * @version 2024/03/25
 */
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
     * The real data  for the total run time is suppose to be fetched from a "statistic team".
     * For now, we will implement a small algorithm to calculate time and will use Dummy data
     * for Sprint 2
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

    /**
     * Method to calculate the max speed
     * Important to know that on OPENTRACKS at class TrackStatistics have an attribute called
     * total Duration time
     */

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
    /**
     * Calculates the average speed of an entire run given a list of TrackPoints belonging to a run
     * the user skied on (km/h).
     *
     * @param trackPointCollection List of TrackPoints belonging to a run
     * @return user's average speed during the entire run in km/h
     * @author serbancaia
     */
    private double calculateAverageSpeedKmPerHour(List<TrackPoint> trackPointCollection)
    {
        double averageRunSpeed = 0;
        int countSegments = -1;

        for (TrackPoint trackPoint : trackPointCollection){
            if(countSegments > -1){
                averageRunSpeed+=trackPoint.getSpeed();
            }
            countSegments++;
        }

        if(countSegments < 1)
            return 0;
        else
            return averageRunSpeed/countSegments;
    }

    /**
     * Calculates the distance of an entire run given a list of TrackPoints belonging to a run
     * the user skied on (meters).
     *
     * @param trackPointCollection List of TrackPoints belonging to a run
     * @return user's travelled distance during the entire run in meters
     * @author serbancaia
     */
    private double calculateDistanceInMeters(List<TrackPoint> trackPointCollection)
    {
        double distanceSum = 0;
        TrackPoint trackPoint1 = null;
        TrackPoint trackPoint2 = null;

        for (TrackPoint i : trackPointCollection){
            if(trackPoint1 == null){
                trackPoint1 = i;
            }
            else if(trackPoint2 == null){
                trackPoint2 = i;
                distanceSum+=trackPoint1.getLatLong().sphericalDistance(trackPoint2.getLatLong());
            }
            else{
                trackPoint1 = trackPoint2;
                trackPoint2 = i;
                distanceSum+=trackPoint1.getLatLong().sphericalDistance(trackPoint2.getLatLong());
            }
        }

        return distanceSum;
    }
}

