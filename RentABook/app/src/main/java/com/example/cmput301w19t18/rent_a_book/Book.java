package com.example.cmput301w19t18.rent_a_book;

import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Book extends AppCompatActivity{

    private String btitle;
    private String author;
    private String ISBN; //Established as an ISBN to allow for better error handling and to prevent dropping of leading 0's
    private String bstatus;
    private String owner;
    private Integer rating;
    private String borrowedBy; //Email of the user that borrowed the book
    private Integer copyCount; //number of copies of the book that exist

    private String[] genre;
    private ArrayList<String> requested_by;
    private ArrayList<String> owned_by;
    private ArrayList<String> requestedBy;
    //private ArrayList<String> requestedBy; //Emails of users that have placed requests for the book

    //firebase auth object
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;

    //buttons and fields
    private EditText editTextName, editTextAddress;
    private Button SubmitB;
    private EditText ISBNF;
    private EditText AuthorF;
    private EditText TitleF;
    private EditText DescF;

    @Override
    //now createes a view
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbook);

        //initializing firebase auth object
        bAuth = FirebaseAuth.getInstance();

        //initializing fields and buttons
        SubmitB = (Button) findViewById(R.id.SubmitButton);

        ISBNF = (EditText) findViewById(R.id.ISBNBox);
        AuthorF = (EditText) findViewById(R.id.AuthBox);
        TitleF = (EditText) findViewById(R.id.TitleBox);
        DescF = (EditText) findViewById(R.id.DescriptionBox);

        //check if user is logged in. if not, returns null
        if (bAuth.getCurrentUser() == null){
            finish(); //close activity
            startActivity(new Intent(this, MainActivity.class)); //returns to login screen
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    //constructor (changed to public constructor)
    public Book (String btitle, String author, String[] genre, String ISBN, String bstatus, String owner, Integer rating){
        this.btitle = btitle;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bstatus = bstatus;
        this.owner = owner;
        this.rating = rating;
        this.initialRequestedBy();
        this.borrowedBy = null;

        //use of arrays:
        //https://alvinalexander.com/java/java-string-array-reference-java-5-for-loop-syntax
        //https://stackoverflow.com/questions/17515096/string-array-initialization-in-java

    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void getGenre() {this.genre = genre; }

    public void setGenre(String genre[]) { this.genre = genre; }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ArrayList<String> getRequestedBy() {
        return requestedBy;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    private void addPhoto (String fileName){
        //code
    }

    public void setRequestedBy(String requestingUser) { //email of the user requesting it

        if (this.getBstatus() == "Accepted" || this.getBstatus() == "Borrowed") {
            return;
        } else if (this.getBstatus() == "Requested"){
            this.requestedBy.add(requestingUser);
        } else if (this.getBstatus() == "Available") {
            this.setBstatus("Requested");
            this.requestedBy.add(requestingUser);
        }
    }

    public void setBorrowedBy(String borrowingUser) { //Set borrower and clear all current requests
        this.setBstatus("Borrowed"); //Change status
        this.borrowedBy = borrowingUser; //Set the borrowing user
        this.initialRequestedBy(); //Clear all requests from the book
    }

    //Create an initializer to set RequestedBy to an empty array to avoid having null pointer errors
    public void initialRequestedBy () {
        ArrayList<String> initial = new ArrayList<>();
        this.requestedBy = initial;
        return;
    }


    ////////////// Book operations //////////////


    private void InputNewBook(){
        String author = AuthorF.getText().toString().trim();
        String ISBN = ISBNF.getText().toString().trim();
        String title = TitleF.getText().toString().trim();
        String description = DescF.getText().toString().trim();
        String[] genre = {"test genre"};
        String owner = "test owner";
        Integer rating = 4;
        String status = "available";

        Book book = new Book(title, author, genre, ISBN, status, owner, rating );

        databaseReference.child("books").setValue(book); //should put the book in the db under books

        Toast.makeText(this, "Uploaded.", Toast.LENGTH_LONG).show();
    }



























}