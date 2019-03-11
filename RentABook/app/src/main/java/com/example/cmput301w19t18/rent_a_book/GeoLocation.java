package com.example.cmput301w19t18.rent_a_book;

import android.location.Location;

public class GeoLocation {

    private double latitude;
    private double longitude;

    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void getLocation() {
        // getting location from Google API (still not sure how to implement this part)
    }

    public void setLocation(double latitude, double longitude) {
        // setting the location from Google API (still not sure how to implement this part)
    }


}
