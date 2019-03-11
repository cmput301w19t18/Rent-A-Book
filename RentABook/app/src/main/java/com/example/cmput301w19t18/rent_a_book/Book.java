package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

/*
 * Holds all information pertaining to a certain book
 * Books are distinguished based on the ISBN
 *
 * @param: btitle: title of the book as a string
 * @param: author: name of the author as a string
 * @param: ISBN: unique identifier of every book, as a string
 * @param: bstatus: the status of the book. Requested, borrowed, available
 * @param: genre: a string to be parsed as a list storing up to 3 genres that the book is classified by
 * @param: requestedBy: a list of users by email requesting the book
 * @param: copyCount: a count of the number of copies of the book that are currently contained in the database

 */

public class Book implements Serializable{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private Integer rating;
    private String genre; //genre will be determined by an array
    private ArrayList<String> requestedBy; //list of users that are requesting the book by email
    

    //private String description; //Description of the book entered by the user
    private Integer copyCount; //number of copies of the book that exist




    //constructor (changed to public constructor)
    public Book(String btitle, String author, String genre, String ISBN, String bstatus, ArrayList<String> requestedBy, Integer rating, Integer copyCount){
        this.btitle = btitle;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.rating = rating;
        this.initialRequestedBy();
        this.requestedBy = this.requestedBy;

        //use of arrays:
        //https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
        //https://stackoverflow.com/questions/17515096/string-array-initialization-in-java

    }



    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }

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

    public void getGenre() {
        this.genre = genre;
    }

    public void setGenre(String genre) { this.genre = genre; }

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

    private void addPhoto (String fileName){
        //code
    }

    //Create an initializer to set RequestedBy to an empty array to avoid having null pointer errors
    public void initialRequestedBy () {
        ArrayList<String> initial = new ArrayList<>();
        this.requestedBy = initial;
        return;
    }

    public ArrayList<String> getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requester_email) {
        this.requestedBy = requestedBy;
        requestedBy.add(requester_email); //appends the requester to the list of requests
    }



























}