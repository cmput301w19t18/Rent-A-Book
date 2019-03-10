package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    ArrayList<Book> book_results;
    private String search;

    // I don't know if I should implement the search results as a separate class in addition to this activity lol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }

    public void DisplayResults() {
        // code

    }

    public void Refresh() {
        // code
    }
}
