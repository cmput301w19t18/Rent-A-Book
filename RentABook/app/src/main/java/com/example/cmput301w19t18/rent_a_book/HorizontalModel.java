package com.example.cmput301w19t18.rent_a_book;

/**
 * The type Horizontal model.
 * The horizontal model is an object class that stores the individual book cover and rating in a specific category
 */
public class HorizontalModel {
    /**
     * Gets book rating.
     *
     * @return the book rating
     */
    public int getBookRating() {
        return bookRating;
    }

    /**
     * Sets book rating.
     *
     * @param bookRating the book rating
     */
    public void setBookRating(int bookRating) {
        this.bookRating = bookRating;
    }

    /**
     * Gets book cover.
     *
     * @return the book cover
     */
    public String getBookCover() {
        return bookCover;
    }


    /**
     * Sets book cover.
     *
     * @param bookCover the book cover
     */
    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    private int bookRating;
    private String bookCover;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public  String bookTitle;


}
