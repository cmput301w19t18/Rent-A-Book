package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseApp firebaseApp;
    private static final String TAG = "myActivity";


    //buttons and fields
    private Button SubmitB;
    private EditText ISBNF;
    private EditText AuthorF;
    private EditText TitleF;
    private EditText DescF;
    private String email;
    private Button btnPhoto;
    private ImageView imageView;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;



    //record of books added by ISBN
    private Integer[] booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle b =  intent.getExtras();

        setContentView(R.layout.activity_newbook);

        //initializing firebase auth object
        bAuth = FirebaseAuth.getInstance();


        //initializing fields and buttons
        SubmitB = (Button) findViewById(R.id.SubmitButton);
        ISBNF = (EditText) findViewById(R.id.ISBNBox);
        AuthorF = (EditText) findViewById(R.id.AuthBox);
        TitleF = (EditText) findViewById(R.id.TitleBox);
        DescF = (EditText) findViewById(R.id.DescriptionBox);
        btnPhoto = findViewById(R.id.addphoto);
        imageView = findViewById(R.id.image);
        SubmitB.setOnClickListener(this);
        //email = b.getString("user_email");
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });




        //check if user is logged in. if not, returns null
        if (bAuth.getCurrentUser() == null){
            finish(); //close activity
            startActivity(new Intent(this, MainActivity.class)); //returns to login screen
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");

    }

    private void addButton(){

        Intent intent = new Intent(this, addPhotoActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                filePath = data.getParcelableExtra("filepath");

                if(filePath != null) {
                    //Toast.makeText(getApplicationContext(), "Uri: " + filePath.toString(), Toast.LENGTH_SHORT).show();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        imageView.setImageBitmap(bitmap);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
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
        String id = databaseReference.push().getKey();

        //some of these need to be changed every time a new book is added

        //Currently only is able to add values entered for a new book that is not already in the database

        String genre = "000001010"; //going to be set by external function
        String requestedBy = "jakep@nypd.org";//new ArrayList<String>();
        String email = bAuth.getCurrentUser().getEmail();
        ArrayList<String> ownedBy = null;

        //ownedBy.add(bAuth.getCurrentUser().getEmail()); // sets as owner
        String status = "Available"; //as long as there is one copy of the book that is not requested or borrowed
        Integer rating = 4;
        Integer copyCount = 1; //how do we check if a copy already exists and increment the counter? Do we actually need to keep track of this or will the owner


        //add the new book to firebase
        Book newBook = new Book(title, author, ISBN, status, rating, email, genre, requestedBy);
        //Book newBook = new Book(title, author, genre, ISBN, status, requestedBy, rating, email);

        databaseReference.child(id).setValue(newBook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    Toast.makeText(NewBookActivity.this, "Book uploaded", Toast.LENGTH_SHORT).show();
                    finish();

                }
                if(!task.isSuccessful()){
                    Toast.makeText(NewBookActivity.this,"error",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(NewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                }

            }
        }

        );
        //databaseReference.child("books").setValue(newBook); //should put the book in the db under books
        //finish();
    }

    @Override //when you press the submit button
    public void onClick(View view) {

        if(view == SubmitB){
            saveBookInfo(); //calls the save function upon press
        }

    }
}
