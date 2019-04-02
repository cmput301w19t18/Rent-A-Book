package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikramshire on 2019-03-04.
 * Modified by jusong on 2019-03-09
 *
 * Contains all of the information pertinent to users. Information can be queried from firebase
 * and edited at a later date by the logged in user.
 */

public class User implements Serializable {
    public String firstName;
    public String lastName;
    public String phoneNum;
    public String email;
    public  String prefList;
    public Double cur_lat = 0.0;
    public Double cur_lon = 0.0;
    private int[] books_Owned;
    private int[] books_borrowed;
    private String URI;

    public User(String email, String prefList, String firstName, String lastName, String phoneNum, String URI){

        this.email = email;
        this.prefList = prefList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.URI = URI;

    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public int[] getBooks_Owned() {
        return books_Owned;
    }

    public void setBooks_Owned(int[] books_Owned) {
        this.books_Owned = books_Owned;
    }

    public int[] getBooks_borrowed() {
        return books_borrowed;
    }

    public void setBooks_borrowed(int[] books_borrowed) {
        this.books_borrowed = books_borrowed;
    }

    public String getPrefList() {
        return prefList;
    }

    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getCur_lat() {
        return cur_lat;
    }
    public void setCur_lat(Double cur_lat) {
        this.cur_lat = cur_lat;
    }
    public Double getCur_lon() {
        return cur_lon;
    }
    public void setCur_lon(Double cur_lon) {
        this.cur_lon = cur_lon;
    }

}