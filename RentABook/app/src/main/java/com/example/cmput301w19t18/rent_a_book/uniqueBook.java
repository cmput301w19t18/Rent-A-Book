package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;

public class uniqueBook implements Serializable {

    private String ISBN;
    private String genre;
    private float rating;
    private Integer copyCount;
    private String author;
    private String title;
    private float avgRating;
    private String avgGenre;

    public uniqueBook(String ISBN, String genre, float rating, Integer copyCount, String author, String title){
        this.copyCount = copyCount;
        this.genre = genre;
        this.ISBN = ISBN;
        this.rating = rating;
        this.author = author;
        this.title = title;
        this.avgRating = rating/copyCount;
        this.avgGenre = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
    }

    public uniqueBook(String ISBN, String genre, String author, String title){
        this.copyCount = 1;
        this.genre = genre; //need to implement method to parse genre
        this.ISBN = ISBN;
        this.rating = 0;
        this.author = author;
        this.title = title;
        this.avgGenre = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        this.avgRating = 0;
    }

    // might not need this if
    //calculates average rating of the book
    public void calculateRating(){
        avgRating = getRating()/getCopyCount();
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }
    public String getAvgGenre() {
        return avgGenre;
    }

    public void setAvgGenre(String avgGenre) {
        this.avgGenre = avgGenre;
    }

}
