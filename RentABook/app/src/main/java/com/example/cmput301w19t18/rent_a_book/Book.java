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
    private String[] genre; //genre will be determined by an array
    private ArrayList<String> requestedBy; //list of users that are requesting the book by email
    

    //private String description; //Description of the book entered by the user
    private Integer copyCount; //number of copies of the book that exist




    //constructor (changed to public constructor)
    public Book(String btitle, String author, String[] genre, String ISBN, String bstatus, ArrayList<String> requestedBy, Integer rating, Integer copyCount){
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

    public void setGenre(String genre[]) { this.genre = genre; }

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

    public void setRequestedBy(ArrayList<String> requestedBy, String requester_email) {
        this.requestedBy = requestedBy;
        requestedBy.add(requester_email); //appends the requester to the list of requests
    }



























}