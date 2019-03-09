package com.example.cmput301w19t18.rent_a_book;

import android.location.Location;
import android.util.Pair;

import java.io.Serializable;

/**
 * Created by ikramshire on 2019-03-06.
 */
//this class deals witht the functins that the owner specifically deals with.

public class Owner implements Serializable {

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public float[] getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(float[] myLocation) {
        this.myLocation = myLocation;
    }

    public Book getMyBook() {
        return myBook;
    }

    public void setMyBook(Book myBook) {
        this.myBook = myBook;
    }

    private String user_id;
    private float[] myLocation;
    private Book myBook;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    private int img;
    public Owner(Book myBook, String user_id, float[] myLocation, int img){
        this.myBook = myBook;
        this.user_id = user_id;
        this.myLocation = myLocation;
        this.img = img;

    }


}
