package com.example.cmput301w19t18.rent_a_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BookFunctionTest {

    @Test
    public void setRequestedBy() {
        Book obj = new Book("Title", "Author", "Genre", "1234567890", "Available", "Owner", 5);
        obj.setRequestedBy("Requester");
        String Bstatus = obj.getBstatus();
        ArrayList<String> RequestingUsers = obj.getRequestedBy();
        ArrayList<String> Expected = new ArrayList<>();
        Expected.add("Requester");

        assertEquals("Requested", Bstatus);
        assertEquals(Expected, RequestingUsers);
    }

    @Test
    public void setBorrowedBy() {
        Book obj = new Book( "Title", "Author", "Genre", "1234567890", "Available", "Owner", 5);
        obj.setBorrowedBy("Borrower");
        String status = obj.getBstatus();
        String borrower = obj.getBorrowedBy();
        ArrayList<String> Expected = new ArrayList<>();

        assertEquals("Borrowed", status );
        assertEquals("Borrower", borrower );
        assertEquals(Expected, obj.getRequestedBy());
    }

}