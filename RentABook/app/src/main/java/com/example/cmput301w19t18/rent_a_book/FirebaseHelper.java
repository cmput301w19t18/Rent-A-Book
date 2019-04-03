package com.example.cmput301w19t18.rent_a_book;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {
    DatabaseReference ref;
    Boolean saved = null;
    ArrayList<String> booksRequested = new ArrayList<>();

    public FirebaseHelper(DatabaseReference ref) {
        this.ref = ref;
    }

    // saving data
    public Boolean save(Book book) {
        if (book == null) {
            saved = false;
        }
        else {
            try {
                ref.child("Books").push().setValue(book);
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    // reading data
    public ArrayList<String> getData() {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return booksRequested;
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        booksRequested.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String bookTitle = ds.getValue(Book.class).getBtitle();
            booksRequested.add(bookTitle);
        }
    }
}
