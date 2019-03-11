package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookReturnTest {

    ArrayList<String> RequestedBy = new ArrayList<String>();
    Book obj = new Book("Title", "Author", "Test Genre", "1234567891234", "Available", RequestedBy,  0, 5);

    String borrowerEmail = "borrower@gmail.com";
    String ownerEmail = "owner@gmail.com";

    @Test
    public void checkReturn ( String borrowerEmail, String ownerEmail){
        assertEquals("borrower@gmail.com", borrowerEmail );
        assertEquals("owner@gmail.com", ownerEmail );
    }



}