package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addBook = (Button) findViewById(R.id.addbutton);
        addBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == addBook){
            //go to register activity
            startActivity( new Intent(this,NewBookActivity.class));
        }

    }
}
