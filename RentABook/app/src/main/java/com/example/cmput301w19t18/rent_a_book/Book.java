package com.example.cmput301w19t18.rent_a_book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.support.v7.app.AppCompatActivity;

/**
 * The type Book.
 * Creates Book Class, stores all information pertaining to books
 * Created by oanderso, and modified by jusong, ishire, dikova
 */
public class Book implements Serializable{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private float rating;
    private String bOwner;
    private String genre; //genre will be determined by an array
    private String requestedBy; //list of users that are requesting the book by email
    private List<String> requestedList = new ArrayList<String>();
    private boolean requested = false;
    private String borrowedBy;
    private String description;


    /**
     * Instantiates a new Book.
     *
     * @param btitle      the btitle
     * @param author      the author
     * @param ISBN        the isbn
     * @param bstatus     the bstatus
     * @param rating      the rating
     * @param bOwner      the b owner
     * @param genre       the genre
     * @param requestedBy the requested by
     */
    public Book(String btitle, String author, String ISBN, String bstatus, float rating, String bOwner, String genre, String requestedBy, String borrowedBy, String description) {
        this.btitle = btitle;
        this.author = author;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.rating = rating;
        this.bOwner = bOwner;
        this.genre = genre;
        this.requestedBy = requestedBy;
        this.borrowedBy = borrowedBy;
        this.description = description;

    }

    public Book(String btitle, String author, String ISBN, String bstatus, float rating, String bOwner, String genre, String description) {
        this.btitle = btitle;
        this.author = author;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.rating = rating;
        this.bOwner = bOwner;
        this.genre = genre;
        this.description = description;

    }

    /**
     * Instantiates a new Book.
     */
    public Book() {}

    /**
     * Gets btitle.
     *
     * @return the btitle
     */
    public String getBtitle() {
        return btitle;
    }

    /**
     * Sets btitle.
     *
     * @param btitle the btitle
     */
    public void setBtitle(String btitle) {
        this.btitle = btitle;
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
     * Gets isbn.
     *
     * @return the isbn
     */
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
     * Gets bstatus.
     *
     * @return the bstatus
     */
    public String getBstatus() {
        return bstatus;
    }

    /**
     * Sets bstatus.
     *
     * @param bstatus the bstatus
     */
    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
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
        this.rating = rating;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public String getbOwner() {
        return bOwner;
    }

    /**
     * Sets owner.
     *
     * @param bOwner the b owner
     */
    public void setbOwner(String bOwner) {
        this.bOwner = bOwner;
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
     * Gets requested by.
     *
     * @return the requested by
     */
    public String getRequestedBy() {
        return requestedBy;
    }

    /**
     * Sets requested by.
     *
     * @param requestedBy the requested by
     */
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    /**
     * Allows multiple people to request a copy of a book.
     * @param requester
     * @return
     */
    public String addRequester(String requester) {
        if(requestedBy.equals(null) || requestedBy.length() == 0) {
            requestedBy = requester;
        }
        else {
            requestedBy += ", " + requester;
        }
        return requestedBy;
    }

    public List<String> stringToList(String str) {
        if (!str.contains(", ")) {
            requestedList.add(str);
        }
        else {
            String[] r = str.split(", ");
            for (int i = 0; i < r.length; i++) {
                requestedList.add(r[i]);
            }
        }

        return requestedList;
    }

    public String listToString(List<String> lStr) {
        StringBuilder strBuild = new StringBuilder();
        Iterator<String> i = lStr.iterator();
        while(i.hasNext()) {
            strBuild.append(i.next());
            if(i.hasNext()) {
                strBuild.append(", ");
            }
        }
        requestedBy = strBuild.toString();
        return requestedBy;
    }

    public boolean isRequesting(String user) {
        if (requestedList.contains(user)) {
            requested = true;
        }
        else {
            requested = false;
        }
        return requested;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}