package com.example.cmput301w19t18.rent_a_book;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchResultsActivityTest {

    @Test
    public void onCreate() {

    }

    @Test
    public void DisplayResults() {

        // String btitle, String author, String genre, String ISBN, String bstatus, String owner, Integer rating
        String search = "Brooklyn 99";
        // Book object has private access
<<<<<<< HEAD
        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "jakep@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book2 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "amys@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Requested", 4, "rosad@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Accepted", 4, "charlesb@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book5 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Borrowed", 4, "raymondh@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book6 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "jakep@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
=======
        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "jakep@nypd.org", requestedBy, 5, copyCount);
        Book book2 = new Book("Brooklyn 99 2 - Book Edition", "Daniel Goor", "Comedy", "2", "Available", "amys@nypd.org", requestedBy, 4, copyCount);
        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1","Requested",  "rosad@nypd.org", requestedBy, 5, copyCount);
        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Accepted", "charlesb@nypd.org", requestedBy, 5, copyCount);
        Book book5 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Borrowed", "raymondh@nypd.org", requestedBy, 5, copyCount);
        Book book6 = new Book("Nothing Lasts Forever", "Roderick Thorp", "Thriller", "3", "Available", "jakep@nypd.org", requestedBy, 3, copyCount);
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f

        book4.setRequestedBy("jakep@nypd.org");

        ArrayList<Book> book_results = new ArrayList<Book>();

        //will probably make this into a function
        if (book1.getBtitle().contains(search) || book1.getAuthor().contains(search) && book1.getBstatus().contains("Available") || book1.getBstatus().contains("Requested")) {
            book_results.add(book1);
        }

        if (book2.getBtitle().contains(search) || book2.getAuthor().contains(search) && book2.getBstatus().contains("Available") || book2.getBstatus().contains("Requested")) {
            book_results.add(book2);
        }

        if (book3.getBtitle().contains(search) || book3.getAuthor().contains(search) && book3.getBstatus().contains("Available") || book3.getBstatus().contains("Requested")) {
            book_results.add(book3);
        }

        if (book4.getBtitle().contains(search) || book4.getAuthor().contains(search) && book4.getBstatus().contains("Available") || book4.getBstatus().contains("Requested")) {
            book_results.add(book4);
        }

        if (book5.getBtitle().contains(search) || book5.getAuthor().contains(search) && book5.getBstatus().contains("Available") || book5.getBstatus().contains("Requested")) {
            book_results.add(book5);
        }

        if (book6.getBtitle().contains(search) || book6.getAuthor().contains(search) && book6.getBstatus().contains("Available") || book6.getBstatus().contains("Requested")) {
            book_results.add(book6);
        }

        //assertEquals(book_results.size(), 3);
        assertEquals(book_results.get(0), book1);
        assertEquals(book_results.get(1), book2);
        assertEquals(book_results.get(2), book3);

    }

    @Test
    public void Refresh() {

<<<<<<< HEAD
        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "jakep@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book2 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "amys@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Requested", 4, "rosad@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
=======
        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "jakep@nypd.org", requestedBy, 5, copyCount);
        Book book2 = new Book("Brooklyn 99 2 - Book Edition", "Daniel Goor", "Comedy", "2", "Available", "amys@nypd.org", requestedBy, 4, copyCount);
        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Requested", "rosad@nypd.org", requestedBy, 5, copyCount);
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f

        ArrayList<Book> book_results = new ArrayList<Book>();

        book_results.add(book1);
        book_results.add(book2);
        book_results.add(book3);

        assertEquals(book_results.size(), 3);

<<<<<<< HEAD
        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Accepted", 4, "charlesb@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com");
=======
        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "ginal@nypd.org", requestedBy, 5, copyCount);
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f

        book_results.add(book4);

        assertEquals(book_results.size(), 4);

        book_results.remove(book2);

        assertEquals(book_results.size(), 3);

    }
}
