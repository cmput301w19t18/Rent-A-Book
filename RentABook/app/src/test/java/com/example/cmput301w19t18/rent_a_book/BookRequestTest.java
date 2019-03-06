package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookRequestTest {

    Book obj = new Book("Title", "Author", "Genre", "1234567890", "Available", "Owner", 5);

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