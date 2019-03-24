package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.AppCompatActivity;

/**
 * The type Book.
 * Creates Book Class
 * Created by ishire, and modified by jusong
 */
public class Book implements Serializable{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private Integer rating;
<<<<<<< HEAD
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
    public Book(String btitle, String author, String ISBN, String bstatus, Integer rating, String bOwner, String genre, String requestedBy) {
=======
    private List<String> genre; //genre will be determined by an array
    private List<String> requestedBy; //list of users that are requesting the book by email
    

    //private String description; //Description of the book entered by the user
    private Integer copyCount; //number of copies of the book that exist




    //constructor (changed to public constructor)
    public Book(String btitle, String author, List<String> genre, String ISBN, String bstatus, List<String> requestedBy, Integer rating, Integer copyCount){
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176
        this.btitle = btitle;
        this.author = author;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.rating = rating;
<<<<<<< HEAD
        this.bOwner = bOwner;
        this.genre = genre;
        this.requestedBy = requestedBy;
=======
        //this.initialRequestedBy();
        this.requestedBy = this.requestedBy;
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176

        //use of arrays:
        //https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
        //https://stackoverflow.com/questions/17515096/string-array-initialization-in-java

    }

<<<<<<< HEAD
    /**
     * Instantiates a new Book.
     */
    public Book() {};
=======


    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176

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

<<<<<<< HEAD
    /**
     * Gets isbn.
     *
     * @return the isbn
     */
=======
    public void getGenre() {
        this.genre = genre;
    }

    public void setGenre(List<String> genre) { this.genre = genre; }

>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176
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
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

<<<<<<< HEAD
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
=======
    private void addPhoto (String fileName){
        //code
    }

    //Create an initializer to set RequestedBy to an empty array to avoid having null pointer errors
    /*public void initialRequestedBy () {
        List<String> initial = new List<String>() {
        };
        this.requestedBy = initial;
        return;
    }
    */
    public List<String> getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(List<String> requestedBy) {
        this.requestedBy = requestedBy;
         //appends the requester to the list of requests
    }



























>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176
}