package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Inbox. Displays the books currently being requested from the user, as well as
 * the books that the user owns that are currently being requested.
 */
public class Inbox extends AppCompatActivity {

    /**
     * Initialized firebase data
     */
    private FirebaseAuth bAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabaseBooks;
    private DatabaseReference mDatabaseUsers;
    private RecyclerView recyclerView;
    private TextView noDataView;
    private InboxAdapter mAdapter;
    private ArrayList<Book> bookRequests = new ArrayList<Book>();
    //private Book bookRequested;
    private ArrayList<String> usersRequesting = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_inbox);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navView);


        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.home:
                                Intent intent1;
                                intent1 = new Intent(Inbox.this, HomeActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.search:
                                Intent intent2;
                                intent2 = new Intent(Inbox.this, SearchResultsActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.inbox:
                                // do nothing because we're already here
                                break;
                            case R.id.profile:
                                Intent intent3;
                                intent3 = new Intent(Inbox.this, ProfileActivity.class);
                                startActivity(intent3);
                                break;
                        }
                        return false;
                    }
                }
        );

        // connect to firebase and get book info
        bAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabaseBooks = database.getReference().child("Books");
        mDatabaseUsers = database.getReference().child("Users");

        recyclerView = (RecyclerView) findViewById(R.id.inboxResults);
        noDataView = (TextView) findViewById(R.id.noData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());




        mDatabaseBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Book b = dataSnapshot1.getValue(Book.class);
                    // check if owner owns book
                    if (b.getbOwner().equals(bAuth.getCurrentUser().getEmail()) && b.getBstatus().equals("Requested")) {
                        // add each book based on one single request
                        if (b.getRequestedBy().contains(", ")) {
                            String[] r =  b.getRequestedBy().split(", ");
                            for (int i = 0; i < r.length; i++) {
                                // add new book with each requester?
                                //Toast.makeText(Inbox.this,r[i], Toast.LENGTH_SHORT).show();
                                Book newBook = new Book(b.getBtitle(), b.getAuthor(), b.getISBN(), b.getBstatus(), b.getRating(), b.getbOwner(), b.getGenre(), r[i], "");
                                //b.setRequestedBy(r[i]);
                                bookRequests.add(newBook);
                                usersRequesting.add(r[i]);
                            }
                        }
                        else {
                            usersRequesting.add(b.getRequestedBy());
                            bookRequests.add(b);
                        }

                    }
                }
                mAdapter = new InboxAdapter(Inbox.this, bookRequests, usersRequesting);
                recyclerView.setAdapter(mAdapter);

                if (mAdapter.getItemCount() == 0) {
                    // if no borrow requests show display no request message
                    recyclerView.setVisibility(View.GONE);
                    noDataView.setVisibility(View.VISIBLE);
                } else {
                    // else show the people asking to borrow
                    recyclerView.setVisibility(View.VISIBLE);
                    noDataView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Inbox.this,"Yikes, something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

}