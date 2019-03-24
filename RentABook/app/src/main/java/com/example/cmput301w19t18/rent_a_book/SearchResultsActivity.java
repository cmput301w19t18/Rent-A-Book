package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
=======
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Search results activity.
 * The search results activity allows the user to type in the taskbar and see available books to request it
 * (still a work in progress)
 */
public class SearchResultsActivity extends AppCompatActivity {
<<<<<<< HEAD
    public boolean update = false;
    public static final int SEARCHING = 1;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Book> BookSearchList;
=======

    ArrayList<Book> book_results;
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176
    private String search;
    private FloatingActionButton searchButton;
    private EditText userSearchText;
    private DatabaseReference mUserDatabase;

    // I don't know if I should implement the search results as a separate class in addition to this activity lol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
<<<<<<< HEAD
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");
        BookSearchList = new ArrayList<>();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

//        setContentView(R.layout.activity_search_results);
//        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "jakep@nypd.org", 5, 4);
//        Book book2 = new Book("Brooklyn 99 2 - Book Edition", "Daniel Goor", "Comedy", "2", "Available", "amys@nypd.org", 4, 4);
//        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1","Requested",  "rosad@nypd.org", 5, 1);
//        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Accepted", "charlesb@nypd.org", 5, 4);
//        Book book5 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Borrowed", "raymondh@nypd.org", 5, 4);
//        Book book6 = new Book("Nothing Lasts Forever", "Roderick Thorp", "Thriller", "3", "Available", "jakep@nypd.org", 3, 1);
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

        userSearchText = (EditText) findViewById(R.id.searchText);
        FloatingActionButton searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = userSearchText.getText().toString();
                DisplayResults(searchText);

            }
        });

=======
>>>>>>> 62ada93ed3dc1f614ff1724b751fba868020b176
    }

    /**
     * Display results.
     */
    public void DisplayResults(final String searchText) {

        Query query = mUserDatabase.orderByChild("btitle");//.startAt(searchText).endAt(searchText + "\uf8ff");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookSearchList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // add all books in a list
                        Book newBook = snapshot.getValue(Book.class);

                        if (newBook.getBtitle().toLowerCase().contains(searchText.toLowerCase())) {
                            BookSearchList.add(newBook);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addListenerForSingleValueEvent(valueEventListener);

    }

    /**
     * Refresh.
     */
    public void Refresh() {
        // code
    }
}
