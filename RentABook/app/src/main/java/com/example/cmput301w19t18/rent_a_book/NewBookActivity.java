package com.example.cmput301w19t18.rent_a_book;

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


public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {

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

    private void saveBookInfo(){
        String author = AuthorF.getText().toString().trim();
        String ISBN = ISBNF.getText().toString().trim();
        String title = TitleF.getText().toString().trim();
        String description = DescF.getText().toString().trim();
        String genre = "test genre";
        String owner = "test owner";
        Integer rating = 4;
        String status = "available";

        Book book = new Book(title, author, genre, ISBN, status, owner, rating );

        databaseReference.child("books").setValue(book); //should put the book in the db under books

        Toast.makeText(this, "Uploaded.", Toast.LENGTH_LONG).show();
    }



    @Override //when you press the submit button
    public void onClick(View view) {

        if(view == SubmitB){
            saveBookInfo(); //calls the save function upon press
        }

    }
}
