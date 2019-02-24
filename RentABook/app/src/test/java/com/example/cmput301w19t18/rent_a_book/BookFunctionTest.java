package com.example.cmput301w19t18.rent_a_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookFunctionTest {

    @Test
    public void setRequestedBy() {
        Book obj = new Book("Title", "Author", "Genre", "1234567890", "Available", "Owner", 5);
        obj.setRequestedBy("Borrower");


    }

    @Test
    public void setBorrowedBy() {
        Book obj = new Book( "Title", "Author", "Genre", "1234567890", "Available", "Owner", 5);
        obj.setBorrowedBy("Borrower");
        String status = obj.getBstatus();
        String borrower = obj.getBorrowedBy();

        assertEquals("Borrowed", status );
        assertEquals("Borrower", borrower );
    }


}