package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;

/**
 * Created by ikramshire on 2019-03-06.
 */

public class Borrower implements Serializable {
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private Book book;

    public  Borrower(String user_id, Book book){
        this.user_id = user_id;
        this.book = book;

    }


}
