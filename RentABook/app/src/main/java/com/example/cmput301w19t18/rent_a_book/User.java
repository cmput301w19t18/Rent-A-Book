package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikramshire on 2019-03-04.
 */

public class User implements Serializable {
    public String email;
    public  String prefList;
    private int[] books_Owned;

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

    private int[] books_borrowed;

    public String getPrefList() {
        return prefList;
    }

    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }






    public User(String email, String prefList ){
        this.email = email;
        this.prefList = prefList;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
