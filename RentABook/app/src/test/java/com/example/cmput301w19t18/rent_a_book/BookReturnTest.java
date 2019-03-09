package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookReturnTest {

    Book obj = new Book("Title", "Author", "Genre", "1234567890", "Borrowed", "Owner", 5, copyCount);

    String borrowerEmail = "borrower@gmail.com";
    String ownerEmail = "owner@gmail.com";

    @Test
    public void checkReturn ( String borrowerEmail, String ownerEmail){
        assertEquals("borrower@gmail.com", borrowerEmail );
        assertEquals("owner@gmail.com", ownerEmail );
    }



}