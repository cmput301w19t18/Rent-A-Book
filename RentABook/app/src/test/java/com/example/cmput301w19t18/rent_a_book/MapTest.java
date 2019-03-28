package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    MapsActivity MapTestObj = new MapsActivity();

    @Test
    public void checkAvailableStatus (){

        MapTestObj.setLocationLat(150.0);
        MapTestObj.setLocationLon(-50.0);

        Double lat = MapTestObj.getLocationLat();
        Double lon = MapTestObj.getLocationLon();

        Double latlat = 150.0;
        Double lonlon = -50.0;

        assertEquals(lat, latlat);
        assertEquals(lon, lonlon);
    }
}
