package com.example.cmput301w19t18.rent_a_book;

import android.location.Location;
import android.util.Pair;

import java.io.Serializable;

/**
 * Created by ikramshire on 2019-03-06.
 */
//this class deals witht the functins that the owner specifically deals with.

public class Owner implements Serializable {

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Sets user id.
     *
     * @param user_id the user id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * Get my location float [ ].
     *
     * @return the float [ ]
     */
    public float[] getMyLocation() {
        return myLocation;
    }

    /**
     * Sets my location.
     *
     * @param myLocation the my location
     */
    public void setMyLocation(float[] myLocation) {
        this.myLocation = myLocation;
    }

    /**
     * Gets my book.
     *
     * @return the my book
     */
    public Book getMyBook() {
        return myBook;
    }

    /**
     * Sets my book.
     *
     * @param myBook the my book
     */
    public void setMyBook(Book myBook) {
        this.myBook = myBook;
    }

    private String user_id;
    private float[] myLocation;
    private Book myBook;


    /**
     * Gets img.
     *
     * @return the img
     */
    public int getImg() {
        return img;
    }

    /**
     * Sets img.
     *
     * @param img the img
     */
    public void setImg(int img) {
        this.img = img;
    }

    private int img;

    /**
     * Instantiates a new Owner.
     *
     * @param myBook     the my book
     * @param user_id    the user id
     * @param myLocation the my location
     * @param img        the img
     */
    public Owner(Book myBook, String user_id, float[] myLocation, int img){
        this.myBook = myBook;
        this.user_id = user_id;
        this.myLocation = myLocation;
        this.img = img;

    }


}
