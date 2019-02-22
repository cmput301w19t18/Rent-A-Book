package com.example.cmput301w19t18.rent_a_book;

import java.util.ArrayList;

public class Book {

    private String btitle;
    private String author;
    private String genre;
    private String ISBN;
    private String bstatus;
    private String owner;
    private Integer rating;

    private ArrayList<String> requestedBy; //email of user who requested the book
    private String borrowedBy; //email of user that borrowed the book


    //constructor
    public Book (String btitle, String author, String genre, String ISBN, String bstatus, String owner, Integer rating){
        //code
    }

    private void addPhoto (String fileName){
        //code
    }

    public void setRequestedBy(String requestingUser) { //email of the user requesting it
        if (this.getBstatus() == "Accepted" || this.getBstatus() == "Borrowed") {
            return;
        } else if (this.getBstatus() == "Requested"){
            this.requestedBy.add(requestingUser);
        } else {
            this.setBstatus("Requested");
            this.requestedBy.add(requestingUser);
        }
    }

    public void setBorrowedBy(String borrowingUser) { //email of the user borrowing it
        this.setBstatus("Borrowed");
        this.borrowedBy = borrowingUser;
    }

    public void setBstatus(String newStatus){
        this.bstatus = newStatus;
    }

    public String getBstatus() {
        return bstatus;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }
}
