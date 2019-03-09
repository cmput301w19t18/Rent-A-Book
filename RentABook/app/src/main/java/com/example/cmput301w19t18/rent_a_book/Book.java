package com.example.cmput301w19t18.rent_a_book;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

public class Book extends AppCompatActivity{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private Integer rating;
    private String borrowedBy; //Email of the user that is currently borrowing the book
    //private String description; //Description of the book entered by the user
    private Integer copyCount; //number of copies of the book that exist

    private String[] genre; //genre will be determined by an array
    private ArrayList<String> requestedBy; //list of users that are requesting the book by email
    private ArrayList<String> ownedBy; //list of users that own this book by email



    //constructor (changed to public constructor)
    public Book(String btitle, String author, String[] genre, String ISBN, String bstatus, String[] ownedBy, Integer rating, Integer copyCount){
        this.btitle = btitle;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.ownedBy = this.ownedBy;
        this.rating = rating;
        this.initialRequestedBy();
        this.borrowedBy = null;

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

    public void setGenre(
            String genre[]) { this.genre = genre;
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

    public ArrayList<String> getRequestedBy() {
        return requestedBy;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    private void addPhoto (String fileName){
        //code
    }

    public void setRequestedBy(ArrayList<String> requestedBy) {
        this.requestedBy = requestedBy;
    }

    public ArrayList<String> getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(ArrayList<String> ownedBy) {
        this.ownedBy = ownedBy;
    }

    public void setRequestedBy(String requestingUser) { //email of the user requesting it

        if (this.getBstatus() == "Accepted" || this.getBstatus() == "Borrowed") {
            return;
        } else if (this.getBstatus() == "Requested"){
            this.requestedBy.add(requestingUser);
        } else if (this.getBstatus() == "Available") {
            this.setBstatus("Requested");
            this.requestedBy.add(requestingUser);
        }
    }

    public void setBorrowedBy(String borrowingUser) { //Set borrower and clear all current requests
        this.setBstatus("Borrowed"); //Change status
        this.borrowedBy = borrowingUser; //Set the borrowing user
        this.initialRequestedBy(); //Clear all requests from the book
    }

    //Create an initializer to set RequestedBy to an empty array to avoid having null pointer errors
    public void initialRequestedBy () {
        ArrayList<String> initial = new ArrayList<>();
        this.requestedBy = initial;
        return;
    }




























}