package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Unique book.
 * Allows the tracking of unique books by author and title. This allows us
 * to keep track of data shared across all copies of the same book such as rating.
 */
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

    /**
     * Instantiates a new Unique book.
     *
     * @param ISBN      the isbn
     * @param genre     the genre
     * @param rating    the rating
     * @param copyCount the copy count
     * @param author    the author
     * @param title     the title
     */
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

    /**
     * Instantiates a new Unique book.
     *
     * @param ISBN   the isbn
     * @param genre  the genre
     * @param author the author
     * @param title  the title
     */
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

    /**
     * Calculate rating.
     */
// might not need this if
    //calculates average rating of the book
    public void calculateRating(){
        avgRating = getRating()/getCopyCount();
    }


    /**
     * Gets isbn.
     *
     * @return the isbn
     */
///////////// Getters and setters /////////////
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets isbn.
     *
     * @param ISBN the isbn
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(float rating) {
        this.rating = this.rating + rating;
    }

    /**
     * Gets copy count.
     *
     * @return the copy count
     */
    public Integer getCopyCount() {
        return copyCount;
    }

    /**
     * Sets copy count.
     *
     * @param copyCount the copy count
     */
    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets avg rating.
     *
     * @return the avg rating
     */
    public float getAvgRating() {
        return avgRating;
    }

    /**
     * Sets avg rating.
     *
     * @return the avg rating
     */
    public float setAvgRating() {
        this.avgRating = getRating()/getCopyCount();
        return this.avgRating;
    }

    /**
     * Gets avg genre.
     *
     * @return the avg genre
     */
    public String getAvgGenre() {
        return avgGenre;
    }

    /**
     * Sets avg genre.
     *
     * @param genre the genre
     * @return the avg genre
     */
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

    /**
     * Get all user sel genre int [ ].
     *
     * @return the int [ ]
     */
    public int[] getAllUserSelGenre() {
        return allUserSelGenre;
    }

    /**
     * Sets all user sel genre.
     *
     * @param allUserSelGenre the all user sel genre
     */
    public void setAllUserSelGenre(int[] allUserSelGenre) {
        this.allUserSelGenre = allUserSelGenre;
    }

    /**
     * String to int list list.
     *
     * @param genre the genre
     * @return the list
     */
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

    /**
     * List to string string.
     *
     * @param lStr the l str
     * @return the string
     */
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
