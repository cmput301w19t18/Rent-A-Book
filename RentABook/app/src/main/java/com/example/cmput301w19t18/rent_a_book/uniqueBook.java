package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class uniqueBook implements Serializable {

    private String ISBN;
    private String genre;
    private float rating;
    private Integer copyCount;
    private String author;
    private String title;
    private float avgRating;
    private String avgGenre;
    private int[] allUserSelGenre = new int[18];

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
        this.rating = this.rating + rating;
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

    public float setAvgRating() {
        this.avgRating = getRating()/getCopyCount();
        return this.avgRating;
    }
    public String getAvgGenre() {
        return avgGenre;
    }

    public String setAvgGenre(String genre) {

        int count = getCopyCount();
        // convert curr user picked genres to int[]
        List<Integer> g = stringToIntList(genre);

        List<Integer> avgG = new ArrayList<Integer>();
        int[] ag = getAllUserSelGenre();
        // add to all selected genres
        for (int i = 0; i < allUserSelGenre.length; i++) {
            ag[i] = ag[i] + g.get(i);
            avgG.add(ag[i]/count);
        }

        // avg genres converted to string
        this.avgGenre = listToString(avgG);
        return this.avgGenre;

    }

    public int[] getAllUserSelGenre() {
        return allUserSelGenre;
    }

    public void setAllUserSelGenre(int[] allUserSelGenre) {
        this.allUserSelGenre = allUserSelGenre;
    }

    public List<Integer> stringToIntList(String genre) {
        // convert curr user picked genres to int[]
        String[] sg = genre.split(" ");
        //int[] g = new int[18];
        List<Integer> g = new ArrayList<Integer>();
        for (String s : sg) {
            g.add(Integer.valueOf(s));
        }
        return g;
    }

    public String listToString(List<Integer> lStr) {
        StringBuilder strBuild = new StringBuilder();
        Iterator<Integer> i = lStr.iterator();
        while(i.hasNext()) {
            strBuild.append(i.next());
            if(i.hasNext()) {
                strBuild.append(", ");
            }
        }

        return strBuild.toString();
    }
}
