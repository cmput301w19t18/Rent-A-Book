package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookFunctionTest {

    @Test
    public void setRequestedBy() {
        //First test on an available book
        Book obj = new Book("Title", "Author", "Genre", "1234567890", "Available", "Owner", 5, copyCount);
        obj.setRequestedBy("Requester");
        String Bstatus = obj.getBstatus();
        ArrayList<String> RequestingUsers = obj.getRequestedBy();
        ArrayList<String> ExpectedArray = new ArrayList<>();
        ExpectedArray.add("Requester");

        assertEquals("Requested", Bstatus);
        assertEquals(ExpectedArray, RequestingUsers);

        //Second test on a requested book; check to ensure the status doesn't change
        Book obj2 = new Book("Title", "Author", "Genre", "1234567891", "Requested", "Owner", 5, copyCount);
        obj2.setRequestedBy("Requester");
        String Bstatus2 = obj2.getBstatus();
        ArrayList<String> RequestingUsers2 = obj2.getRequestedBy();
        ArrayList<String> ExpectedArray2 = new ArrayList<>();
        ExpectedArray2.add("Requester");

        assertEquals("Requested", Bstatus2);
        assertEquals(ExpectedArray2, RequestingUsers2);

        //Third test on an accepted(borrowed) book to ensure the function doesn't change anything
        Book obj3 = new Book("Title", "Author", "Genre", "1234567892", "Accepted", "Owner", 5, copyCount);
        obj3.setRequestedBy("Requester");
        String Bstatus3 = obj3.getBstatus();
        ArrayList<String> RequestingUsers3 = obj3.getRequestedBy();
        ArrayList<String> ExpectedArray3 = new ArrayList<>();

        assertEquals("Accepted", Bstatus3);
        assertEquals(ExpectedArray3, RequestingUsers3);
    }

    @Test
    public void setBorrowedBy() {
        Book obj = new Book( "Title", "Author", "Genre", "1234567890", "Available", "Owner", 5, copyCount);
        obj.setBorrowedBy("Borrower");
        String status = obj.getBstatus();
        String borrower = obj.getBorrowedBy();
        ArrayList<String> Expected = new ArrayList<>();

        assertEquals("Borrowed", status ); //Test that the status changes to "Borrowed"
        assertEquals("Borrower", borrower ); //Test that the borrower is set to the proper value
        assertEquals(Expected, obj.getRequestedBy()); //Test that the requests are actually cleared from the book
    }

}