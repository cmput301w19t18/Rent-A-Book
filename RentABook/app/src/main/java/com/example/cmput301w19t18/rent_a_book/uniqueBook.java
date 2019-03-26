package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;

public class uniqueBook implements Serializable {

    private String ISBN;
    private String genre;
    private Integer rating;
    private Integer copyCount;

    public uniqueBook(String ISBN, String genre, Integer rating, Integer copyCount){
        this.copyCount = copyCount;
        this.genre = genre;
        this.ISBN = ISBN;
        this.rating = rating;
    }

    public uniqueBook(String ISBN, String genre){
        this.copyCount = 1;
        this.genre = "000000000000000000"; //need to implement method to parse genre
        this.ISBN = ISBN;
        this.rating = 0;
    }

    //calculates average rating of the book
    public void calculateRating(){

    }


    ///////////// Getters and setters /////////////
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }

}
