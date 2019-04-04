package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookRequestTest {

<<<<<<< HEAD
    Book obj = new Book("Title", "Author", "1234567891234", "Available", 5, "Owner USer", "10010000", "");
=======
    Book obj = new Book("Title", "Author", "Genre", "1234567890", "Available", "Owner", requestedBy, 5, copyCount);
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f

    @Test
    public void checkAvailableStatus (){
        String status = obj.getBstatus();

        assertEquals("Available", status );
    }


    @Test
    public void statusToRequested () {
        obj.setBstatus("Requested");
        String status = obj.getBstatus();

        assertEquals("Available", status );
    }


}