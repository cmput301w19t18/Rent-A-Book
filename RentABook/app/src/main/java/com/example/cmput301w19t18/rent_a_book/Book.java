package com.example.cmput301w19t18.rent_a_book;

public class Book {

    private String btitle;
    private String author;
    private String genre;
    private Integer ISBN;
    private String bstatus;
    private String owner;
    private Integer rating;

    private String requestedBy; //email of user who requested the book
    private String borrowedBy; //email of user that borrowed the book


    //constructor
    private Book (String btitle, String author, String genre, Integer ISBN, String bstatus, String owner, Integer rating){
        //code
    }

    private void addPhoto (String fileName){
        //code
    }

    public void setRequestedBy(String requestedBy) { //email of the user requesting it
        this.requestedBy = requestedBy;
    }

    public void setBorrowedBy(String borrowedBy) { //email of the user borrowing it
        this.borrowedBy = borrowedBy;
    }




}
