package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookReturnTest {

    Book obj = new Book("Title", "Author", "1234567891234", "Borrowed", 5, "Owner USer", "10010000", "");

    String borrowerEmail = "borrower@gmail.com";
    String ownerEmail = "owner@gmail.com";

    @Test
    public void checkReturn ( String borrowerEmail, String ownerEmail){
        assertEquals("borrower@gmail.com", borrowerEmail );
        assertEquals("owner@gmail.com", ownerEmail );
    }



}