package model;

import java.util.Date;

/**
 * Created by Alexandru on 4/3/2017.
 */

public class Earthquake {

    private double magnitude;
    private Date dateOfEarthquake;
    private String location;


    public Earthquake() {
    }


    public Earthquake(double magnitude, Date dateOfEarthquake, String location) {
        this.magnitude = magnitude;
        this.dateOfEarthquake = dateOfEarthquake;
        this.location = location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public Date getDateOfEarthquake() {
        return dateOfEarthquake;
    }

    public void setDateOfEarthquake(Date dateOfEarthquake) {
        this.dateOfEarthquake = dateOfEarthquake;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
