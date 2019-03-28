package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

/**
 * The type Book.
 * Creates Book Class
 * Created by oanderso, and modified by jusong, ishire
 */
public class Book implements Serializable{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private float rating;
    private String bOwner;
    private String genre; //genre will be determined by an array
    private String requestedBy; //list of users that are requesting the book by email

    //private String description; //Description of the book entered by the user
    //private Integer copyCount; //number of copies of the book that exist

    /**
     * Instantiates a new Book.
     *
     * @param btitle      the btitle
     * @param author      the author
     * @param ISBN        the isbn
     * @param bstatus     the bstatus
     * @param rating      the rating
     * @param bOwner      the b owner
     * @param genre       the genre
     * @param requestedBy the requested by
     */
    public Book(String btitle, String author, String ISBN, String bstatus, float rating, String bOwner, String genre, String requestedBy) {
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

    /**
     * Instantiates a new Book.
     */
    public Book() {};

    /**
     * Gets btitle.
     *
     * @return the btitle
     */
    public String getBtitle() {
        return btitle;
    }

    /**
     * Sets btitle.
     *
     * @param btitle the btitle
     */
    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets isbn.
     *
     * @param ISBN the isbn
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets bstatus.
     *
     * @return the bstatus
     */
    public String getBstatus() {
        return bstatus;
    }

    /**
     * Sets bstatus.
     *
     * @param bstatus the bstatus
     */
    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public String getbOwner() {
        return bOwner;
    }

    /**
     * Sets owner.
     *
     * @param bOwner the b owner
     */
    public void setbOwner(String bOwner) {
        this.bOwner = bOwner;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets requested by.
     *
     * @return the requested by
     */
    public String getRequestedBy() {
        return requestedBy;
    }

    /**
     * Sets requested by.
     *
     * @param requestedBy the requested by
     */
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}