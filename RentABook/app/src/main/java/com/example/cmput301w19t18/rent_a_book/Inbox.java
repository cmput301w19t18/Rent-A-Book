package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inbox extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Book> BookInboxList;
    private DatabaseReference mUserDatabase;
    FirebaseAuth mAuth;

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


        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");
        BookInboxList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.inboxResults);
        mRecyclerView.setHasFixedSize(true); //for not, it will change size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new InboxAdapter(BookInboxList);
        mAuth = FirebaseAuth.getInstance();


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        displayInbox();
    }

    private void displayInbox() {

        final String user_email = mAuth.getCurrentUser().getEmail();
        Query query = mUserDatabase.orderByChild("bOwner");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookInboxList.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Book req_book = snapshot.getValue(Book.class);
                        if(req_book.getbOwner().contains(user_email)){
                            if(req_book.getBstatus().contains("Requested") ){
                                BookInboxList.add(req_book);

                            }
                        }
                        if(req_book.getBstatus().contains("Borrowed")){
                            if(req_book.getRequestedBy().contains(user_email)){
                                BookInboxList.add(req_book);
                            }
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

}
