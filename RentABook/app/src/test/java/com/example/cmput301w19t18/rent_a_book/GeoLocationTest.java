package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoLocationTest {

    @Test
    public void setLatitude() {
        GeoLocation geoLocation = new GeoLocation(0.5d, 0.5d);

        geoLocation.setLatitude(1.5d);
        double latitude = geoLocation.getLatitude();

        assertEquals(1.5d, latitude, 0.00001);
    }

    @Test
    public void setLongitude() {
        GeoLocation geoLocation = new GeoLocation(0.5d, 0.5d);

        geoLocation.setLongitude(2.0d);
        double longitude = geoLocation.getLongitude();

        assertEquals(2.0d, longitude, 0.00001);
    }

    @Test
    public void setLocation() {
        // i don't really know how to implement this part yet ...
    }
}