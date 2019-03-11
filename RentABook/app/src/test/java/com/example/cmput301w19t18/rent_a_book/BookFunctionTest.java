package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookFunctionTest {

    ArrayList<String> RequestedBy = new ArrayList<String>();
    //String[] GenreList = new String[]{"Genre"};


    @Test
    public void setRequestedBy() {
        //First test on an available book
        Book obj = new Book("Title", "Author", "Test Genre", "1234567891234", "Available", RequestedBy,  0, 5);
        obj.setRequestedBy("requester@gmail.com");
        String Bstatus = obj.getBstatus();
        ArrayList<String> RequestingUsers = obj.getRequestedBy();
        ArrayList<String> ExpectedArray = new ArrayList<>();
        ExpectedArray.add("Requester");

        assertEquals("Requested", Bstatus);
        assertEquals(ExpectedArray, RequestingUsers);

        //Second test on a requested book; check to ensure the status doesn't change
        Book obj2 = new Book("Title", "Author", "Test Genre", "1234567891234", "Requested", RequestedBy,  0, 5);
        obj2.setRequestedBy("requester@gmail.com");
        String Bstatus2 = obj2.getBstatus();
        ArrayList<String> RequestingUsers2 = obj2.getRequestedBy();
        ArrayList<String> ExpectedArray2 = new ArrayList<>();
        ExpectedArray2.add("Requester");

        assertEquals("Requested", Bstatus2);
        assertEquals(ExpectedArray2, RequestingUsers2);

        //Third test on an accepted(borrowed) book to ensure the function doesn't change anything
        Book obj3 = new Book("Title", "Author", "Test Genre", "1234567891234", "Accepted", RequestedBy,  0, 5);
        obj3.setRequestedBy("requester@gmail.com");
        String Bstatus3 = obj3.getBstatus();
        ArrayList<String> RequestingUsers3 = obj3.getRequestedBy();
        ArrayList<String> ExpectedArray3 = new ArrayList<>();

        assertEquals("Accepted", Bstatus3);
        assertEquals(ExpectedArray3, RequestingUsers3);
    }



}