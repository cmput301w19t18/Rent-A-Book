package com.example.cmput301w19t18.rent_a_book;

public class BookRequest {

    private Boolean availableStatus;
    private String requestMessage;
    private Book book;

    public BookRequest(){
        //code
    }

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
        //if book status not already requested, set it to.
        //append this user name to list

    }

}
