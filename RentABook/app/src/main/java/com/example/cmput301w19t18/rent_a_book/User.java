package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikramshire on 2019-03-04.
 * Modified by jusong on 2019-03-09.
 */

public class User implements Serializable {

    private String name;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String prefList;
    private int profilePicture;
    private float[] location;
    private ArrayList<Book> ownedBooks; //stores list of books the user owns
    private ArrayList<Book> borrowedBooks; //stores list of books the user has borrowed

    public User(String name, String lastName, int phoneNumber, String email, String prefList, int profilePicture) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.prefList = prefList;
        this.profilePicture = profilePicture;
    }

    public User(String email, String prefList) {
        this.email = email;
        this.prefList = prefList;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefList() {
        return prefList;
    }

    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public float[] getLocation() {
        return location;
    }

    public void setLocation(float[] location) {
        this.location = location;
    }

    public ArrayList<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(ArrayList<Book> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
