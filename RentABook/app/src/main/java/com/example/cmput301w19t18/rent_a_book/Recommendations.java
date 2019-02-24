package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Recommendations extends AppCompatActivity {

    // get preference from firebase database
    public String[] preferredGenres;
    public String[] ratings;

    public Book[] recommended;

    public Recommendations(String[] preferredGenres, String[] ratings ) {
        //...
    }


    // TODO This is the realtime database set up -> getting reference to posts
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Reading to database
    DatabaseReference ref = database.getReference("message");

    // Read from database to get user genre preferences


    // clustering method
    private Book[] clustering() {
        // to be implemented
        return recommended;
    }

    // matrix decomposition method (asymmetric SVD)
    private Book[] asymmetricSVD() {
        // to be implemented
        return recommended;
    }

}
