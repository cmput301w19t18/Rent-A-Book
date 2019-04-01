package com.example.cmput301w19t18.rent_a_book;

public class BookRequest {
// TODO this is irrelevant -> DELETE not Jeffree Star approved
    private int bookPhoto;
    private Boolean availableStatus;
    private String requestMessage;
    private Book book;

    public BookRequest(int bookPhoto, Boolean availableStatus, String requestMessage){
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
        //code
    }

}
