package com.example.cmput301w19t18.rent_a_book;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        setContentView(R.layout.activity_inbox);
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
