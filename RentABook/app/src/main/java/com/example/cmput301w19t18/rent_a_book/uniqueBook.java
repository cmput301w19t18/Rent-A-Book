package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;

public class uniqueBook implements Serializable {

    private String ISBN;
    private String genre;
    private float userRating;
    private Integer copyCount;
    private String author;
    private String title;
    private float avgRating;
    private String avgGenre;
    private float totalRunningRating;

    public uniqueBook(String ISBN, String genre, float userRating, Integer copyCount, String author, String title){
        this.copyCount = copyCount;
        this.genre = genre;
        this.ISBN = ISBN;
        this.userRating = userRating;
        this.author = author;
        this.title = title;
        //this.avgRating = userRating/copyCount;
        this.avgRating = calculateRating();
        this.avgGenre = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
    }

    public uniqueBook(String ISBN, String genre, String author, String title){
        this.copyCount = 1;
        this.genre = genre; //need to implement method to parse genre
        this.ISBN = ISBN;
        this.userRating = 0;
        this.author = author;
        this.title = title;
        this.avgGenre = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        this.avgRating = 0;
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
        return userRating;
    }

    public void setRating(float userRating) {
        this.userRating = userRating;
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

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public float getTotalRunningRating() {
        return totalRunningRating;
    }

    public void setTotalRunningRating(float totalRunningRating) {
        this.totalRunningRating = totalRunningRating;
    }

    // methods
    // might not need this if
    //calculates average rating of the book
    public float calculateRating(){
        avgRating = this.getTotalRunningRating()/this.getCopyCount();
        return avgRating;
    }

    // gets the sum of all user ratings
    public void calculateAllRating(float newRating) {
        this.totalRunningRating = this.totalRunningRating + newRating;
        setTotalRunningRating(this.totalRunningRating);
    }

    // calculate the three most popular genres as set by users
    public void calculateAvgGenre() {

    }

    // convert genre strings to actual integer arrays
    public ArrayList<Integer> stringToIntArray(String s) {
        // TODO implement
        ArrayList<Integer> i = new ArrayList<Integer>();
        return i;
    }

}
