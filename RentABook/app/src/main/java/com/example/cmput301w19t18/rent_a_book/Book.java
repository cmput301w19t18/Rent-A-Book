package com.example.cmput301w19t18.rent_a_book;

import java.util.ArrayList;

public class Book {

    private String btitle;
    private String author;
    private String genre;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private String owner;
    private Integer rating;

    private ArrayList<String> requestedBy; //Emails of users that have placed requests for the book
    private String borrowedBy; //Email of the user that borrowed the book

    //constructor (changed to public constructor)
    public Book (String btitle, String author, String genre, String ISBN, String bstatus, String owner, Integer rating){
        this.btitle = btitle;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.owner = owner;
        this.rating = rating;
        this.initialRequestedBy();
        this.borrowedBy = null;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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