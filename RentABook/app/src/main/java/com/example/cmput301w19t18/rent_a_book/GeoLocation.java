package com.example.cmput301w19t18.rent_a_book;

import android.location.Location;

/**
 * The type Geo location.
 */
public class GeoLocation {

    private double latitude;
    private double longitude;

    /**
     * Instantiates a new Geo location.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets location.
     */
    public void getLocation() {
        // getting location from Google API (still not sure how to implement this part)
    }

    /**
     * Sets location.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public void setLocation(double latitude, double longitude) {
        // setting the location from Google API (still not sure how to implement this part)
    }


}
