package com.example.cmput301w19t18.rent_a_book;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by ikramshire on 2019-03-06.
 */
//this class deals witht the functins that the owner specifically deals with.

interface Owner  {


    public void setLocation(Location myLocation);
    public  void addPhoto(String myPhoto);



}
