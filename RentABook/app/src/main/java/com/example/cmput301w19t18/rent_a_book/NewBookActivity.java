package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseApp firebaseApp;

    //buttons and fields
    private Button SubmitB;
    private EditText ISBNF;
    private EditText AuthorF;
    private EditText TitleF;
    private EditText DescF;

    //record of books added by ISBN
    private Integer[] booksList;

    @Override
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

    //////////////// Check if exists in the database //////////////// https://www.quora.com/How-do-I-check-a-child-exist-in-firebase-database-using-Android


    /*
    ValueEventListener responseListener  = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {

            databaseReference.child("books").orderByChild("ISBN").equalTo(ISBNF.getText().toString().trim()).once

            if (snapshot.getValue() != null) {
                //user exists, do something
            } else {
                //user does not exist, do something else
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }


    };

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getResponsesRef() {
        return getBaseRef().child("books");
    }

    */


    ////////////////////////////////////////////////////////////////


    private void saveBookInfo(){

        String author = AuthorF.getText().toString().trim();
        String ISBN = ISBNF.getText().toString().trim();
        String title = TitleF.getText().toString().trim();

        //some of these need to be changed every time a new book is added

        //Currently only is able to add values entered for a new book that is not already in the database
        String[] genre = {"test genre"}; //going to be set by external function
        String[] ownedBy = {bAuth.getCurrentUser().getEmail()}; // sets as owner
        String status = "Available"; //as long as there is one copy of the book that is not requested or borrowed
        Integer rating = null;
        Integer copyCount = 1; //how do we check if a copy already exists and increment the counter? Do we actually need to keep track of this or will the owner

        //add the new book to firebase
        Book newBook = new Book(title, author, genre, ISBN, status, ownedBy, rating, copyCount);
        databaseReference.child("books").setValue(newBook); //should put the book in the db under books
        Toast.makeText(this, "Book uploaded.", Toast.LENGTH_LONG).show();
    }



    @Override //when you press the submit button
    public void onClick(View view) {

        if(view == SubmitB){
            saveBookInfo(); //calls the save function upon press
        }

    }
}
