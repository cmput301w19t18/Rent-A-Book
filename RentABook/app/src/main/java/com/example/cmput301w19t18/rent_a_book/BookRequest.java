package com.example.cmput301w19t18.rent_a_book;

/**
 * The type Book request.
 */
public class BookRequest {
// TODO this is irrelevant -> DELETE not Jeffree Star approved
    private int bookPhoto;
    private Boolean availableStatus;
    private String requestMessage;
    private Book book;

    /**
     * Instantiates a new Book request.
     * Allows a book's status to be set to requested
     *
     * @param bookPhoto       the book photo
     * @param availableStatus the available status
     * @param requestMessage  the request message
     */
    public BookRequest(int bookPhoto, Boolean availableStatus, String requestMessage){
        //code
    }

    /**
     * Check available status.
     */
    public void checkAvailableStatus (){
        if ( this.book.getBstatus() == "Available" || this.book.getBstatus() == "Requested" ){
            this.availableStatus = true;
        } else {
            this.availableStatus = false;
        }
    }

    private void sendRequestMessage () {
        //code
    }

    private void statusToRequested () {
        //code
    }

}
