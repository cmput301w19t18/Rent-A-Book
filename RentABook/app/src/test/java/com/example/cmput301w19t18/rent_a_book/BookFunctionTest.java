package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookFunctionTest {

    @Test
    public void setRequestedBy() {
        //First test on an available book
        //(String btitle, String author, String ISBN, String bstatus, Integer rating, String bOwner, String genre, String requestedBy)
        Book obj = new Book("Title", "Author", "1234567891234", "Available", 5, "Owner USer", "10010000", "");
        obj.setRequestedBy("Requester");
        String Bstatus = obj.getBstatus();
        String RequestingUsers = obj.getRequestedBy();
        ArrayList<String> ExpectedArray = new ArrayList<>();
        ExpectedArray.add("Requester");

        assertEquals("Requested", Bstatus);
        assertEquals(ExpectedArray, RequestingUsers);

        //Second test on a requested book; check to ensure the status doesn't change
        Book obj2 = new Book("Title", "Author", "1234567891234", "Requested", 5, "Owner USer", "10010000", "Requesting User");
        obj2.setRequestedBy("Requester");
        String Bstatus2 = obj2.getBstatus();
        String RequestingUsers2 = obj2.getRequestedBy();
        ArrayList<String> ExpectedArray2 = new ArrayList<>();
        ExpectedArray2.add("Requester");

        assertEquals("Requested", Bstatus2);
        assertEquals(ExpectedArray2, RequestingUsers2);

        //Third test on an accepted(borrowed) book to ensure the function doesn't change anything
        Book obj3 = new Book("Title", "Author", "1234567891234", "Accepted", 5, "Owner USer", "10010000", "Requesting User");
        obj3.setRequestedBy("Requester");
        String Bstatus3 = obj3.getBstatus();
        String RequestingUsers3 = obj3.getRequestedBy();
        ArrayList<String> ExpectedArray3 = new ArrayList<>();

        assertEquals("Accepted", Bstatus3);
        assertEquals(ExpectedArray3, RequestingUsers3);
    }

}