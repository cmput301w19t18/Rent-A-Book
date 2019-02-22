package com.example.cmput301w19t18.rent_a_book;

public class BookRequest {

    private Boolean availableStatus;
    private String requestMessage;
    private Book book;

    public BookRequest(){
        //code
    }

    public boolean checkAvailableStatus (){
        if ( this.availableStatus == "Available" || this.availableStatus == "Requested"){
            return true;
        } else {
            return false;
        }
    }

    private void sendRequestMessage () {
        //code
    }

    private void statusToRequested () {
        //code
    }

}
