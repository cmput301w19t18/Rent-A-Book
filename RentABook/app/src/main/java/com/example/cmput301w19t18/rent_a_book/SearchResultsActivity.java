package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f

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
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f
    private String search;
    private FloatingActionButton searchButton;
    private EditText userSearchText;
    private DatabaseReference mUserDatabase;

    // I don't know if I should implement the search results as a separate class in addition to this activity lol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search_results);
<<<<<<< HEAD


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navView);


        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        Intent intent;
                        switch (menuItem.getItemId()) {
                            case R.id.home:
                                Intent intent1;
                                intent1 = new Intent(SearchResultsActivity.this, HomeActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.search:
                                // do nothing because we are already here
                                break;
                            case R.id.inbox:
                                Intent intent2;
                                intent2 = new Intent(SearchResultsActivity.this, Inbox.class);
                                startActivity(intent2);
                                break;

                            case R.id.profile:
                                Intent intent3;
                                intent3 = new Intent(SearchResultsActivity.this, ProfileActivity.class);
                                startActivity(intent3);
                                break;
                        }
                        return false;
                    }
                }
        );



        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");
        BookSearchList = new ArrayList<>();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

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
>>>>>>> c157c3a2ee4076250f4c6db129d797e8f1a0d38f
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
