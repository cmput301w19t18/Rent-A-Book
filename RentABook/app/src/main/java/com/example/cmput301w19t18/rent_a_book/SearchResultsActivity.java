package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The type Search results activity.
 * The search results activity allows the user to type in the taskbar and see available books to request it
 * (still a work in progress)
 */
public class SearchResultsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Book> BookSearchList;

    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_results);
//        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "jakep@nypd.org", 5, 4);
//        Book book2 = new Book("Brooklyn 99 2 - Book Edition", "Daniel Goor", "Comedy", "2", "Available", "amys@nypd.org", 4, 4);
//        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1","Requested",  "rosad@nypd.org", 5, 1);
//        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Accepted", "charlesb@nypd.org", 5, 4);
//        Book book5 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Borrowed", "raymondh@nypd.org", 5, 4);
//        Book book6 = new Book("Nothing Lasts Forever", "Roderick Thorp", "Thriller", "3", "Available", "jakep@nypd.org", 3, 1);

        BookSearchList = new ArrayList<>();
//        BookSearchList.add(book1);
//        BookSearchList.add(book2);
//        BookSearchList.add(book3);
//        BookSearchList.add(book4);
//        BookSearchList.add(book5);
//        BookSearchList.add(book6);

        mRecyclerView = findViewById(R.id.bookResults);
        mRecyclerView.setHasFixedSize(true); //for not, it will change size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchAdapter(BookSearchList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Display results.
     */
    public void DisplayResults() {
        // code

    }

    /**
     * Refresh.
     */
    public void Refresh() {
        // code
    }
}
