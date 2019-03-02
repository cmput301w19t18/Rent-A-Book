package com.example.cmput301w19t18.rent_a_book;

class BookReturn {

    private Boolean returnConfirmed;
    private Book book;

    public BookReturn() {
        //code
    }

    private void checkReturn (String borrowerEmail, String ownerEmail) {
        //make both users confirm the return. match entered emails with firebase
    }
    
    private void statusToAvailable (Boolean returnConfirmed) {
        //sets the book's status to available
    }



}
