package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

public class Book implements Serializable{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private Integer rating;
    private String bOwner;
    private String genre; //genre will be determined by an array
    private String requestedBy; //list of users that are requesting the book by email

    //private String description; //Description of the book entered by the user
    //private Integer copyCount; //number of copies of the book that exist

    /**
     * Stores all information of a book object. Uploaded to firebase for querying.
     * @param btitle title of the book
     * @param author name of the author
     * @param ISBN unique ISBN
     * @param bstatus status of the book
     * @param rating overall rating of the book
     * @param bOwner the user who owns the book, by email/firebase ID
     * @param genre category of the book
     * @param requestedBy user who has requested the book
     */
    public Book(String btitle, String author, String ISBN, String bstatus, Integer rating, String bOwner, String genre, String requestedBy) {
        this.btitle = btitle;
        this.author = author;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.rating = rating;
        this.bOwner = bOwner;
        this.genre = genre;
        this.requestedBy = requestedBy;

        //use of arrays:
        //https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
        //https://stackoverflow.com/questions/17515096/string-array-initialization-in-java

    }

    public Book() {};

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getbOwner() {
        return bOwner;
    }

    public void setbOwner(String bOwner) {
        this.bOwner = bOwner;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}