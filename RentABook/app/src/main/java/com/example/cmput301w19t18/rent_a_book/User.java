package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikramshire on 2019-03-04.
 * Modified by jusong on 2019-03-09
 * Issues: currently does not store all required data, since lists and arrays do not work.
 * TODO: Allow lists/arrays to be uploaded, or find a workaround
 */
public class User implements Serializable {
    /**
     * The Email
     */
    public String email;
    /**
     * The Pref list.
     */
    public  String prefList;
    private int[] books_Owned;
    private int[] books_borrowed;

    /**
     * Instantiates a new User.
     *
     * @param email    the email
     * @param prefList the pref list
     */
    public User(String email, String prefList ){
        this.email = email;
        this.prefList = prefList;

    }

    /**
     * Get books owned int [ ].
     *
     * @return the int [ ]
     */
    public int[] getBooks_Owned() {
        return books_Owned;
    }

    /**
     * Sets books owned.
     *
     * @param books_Owned the books owned
     */
    public void setBooks_Owned(int[] books_Owned) {
        this.books_Owned = books_Owned;
    }

    /**
     * Get books borrowed int [ ].
     *
     * @return the int [ ]
     */
    public int[] getBooks_borrowed() {
        return books_borrowed;
    }

    /**
     * Sets books borrowed.
     *
     * @param books_borrowed the books borrowed
     */
    public void setBooks_borrowed(int[] books_borrowed) {
        this.books_borrowed = books_borrowed;
    }

    /**
     * Gets pref list.
     *
     * @return the pref list
     */
    public String getPrefList() {
        return prefList;
    }

    /**
     * Sets pref list.
     *
     * @param prefList the pref list
     */
    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
