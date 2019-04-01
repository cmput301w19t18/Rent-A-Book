package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.MenuItem;

import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Home activity.
 * Home activity consists of a nested recyclerview and gets the recommended category, books owned, and top books
 * (still a work of progress and will change)
 *
 * Values are fetched using Firebase and querying them to get the correct information in each category
 *
 * https://firebase.google.com/docs/database/admin/retrieve-data
 * https://github.com/mitchtabian/Firebase-Read-Database/blob/master/FirebaseReadData/app/src/main/java/com/tabian/firebasereaddata/ViewDatabase.java
 * https://www.youtube.com/watch?v=NQI8XNFzZT4&t=5s
 *
 */
public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;

    private RecyclerView verticalRecyclerView;
    /**
     * The Adapter.
     */
    HomeVerticalRecyclerView adapter;
    /**
     * The Array list vertical.
     */
    ArrayList<Category> arrayListVertical;
    /**
     * The Update.
     */
    public boolean update = false;
    /**
     * The constant ADDING.
     */
    public static final int ADDING = 1;
    /**
     * The Category.
     */
    Category category, /**
     * The Category 2.
     */
    category2;
    private ArrayList<Book> arrayListHorizontal_myBooks, arrayListHorizontal_myBooks2;
    private ArrayList<Book> bookList;

    //Map test button
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navView);


        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        Intent intent;
                        switch (menuItem.getItemId()) {
                            case R.id.home:
                                // do nothing because we're already here
                                //Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.search:
                                //Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
                                Intent intent1;
                                intent1 = new Intent(HomeActivity.this, SearchResultsActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.inbox:
                                //Toast.makeText(getApplicationContext(), "inbox", Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(HomeActivity.this, Inbox.class);
                                startActivity(intent2);
                                break;
                            case R.id.profile:
                                //Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();
                                Intent intent3;
                                intent3 = new Intent(HomeActivity.this, ProfileActivity.class);
                                startActivity(intent3);
                                break;
                        }
                        return false;
                    }
                }
        );


        //Map test button//
        Button testButton = findViewById(R.id.maptest);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivityForResult(intent, ADDING);
                update = true;
            }
        });

        arrayListVertical = new ArrayList<>();
        verticalRecyclerView = (RecyclerView)findViewById(R.id.homeRecyclerView);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        adapter = new HomeVerticalRecyclerView(this, arrayListVertical);
        verticalRecyclerView.setAdapter(adapter);

        FloatingActionButton addButton = findViewById(R.id.addEntry_fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle extras = new Bundle();
                Intent intent = new Intent(HomeActivity.this, NewBookActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //intent.putExtras(extras);
                //startActivityForResult(intent, ADDING);
                //update = true;
                startActivity(intent);
            }
        });




        bAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");
        arrayListHorizontal_myBooks = new ArrayList<>();
        arrayListHorizontal_myBooks2 = new ArrayList<>();

        //Toast.makeText(this,bAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();

        bookList = new ArrayList<>();

        category = new Category();
        category.setCategoryTitle("All Books");

        Query query = FirebaseDatabase.getInstance().getReference("Books");
        doQuery(query, category);

    }

    /**
     * The Value event listener 1.
     */


    private void doQuery(Query query, final Category category) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListHorizontal_myBooks.clear();
                bookList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // add all books in a list
                        Book newBook = snapshot.getValue(Book.class);

                        //HorizontalModel horizontalModel = new HorizontalModel();

                        //horizontalModel.setBookRating(newBook.getRating());
                        //String url1 = "http://covers.openlibrary.org/b/isbn/";
                        //String url2 = "-M.jpg";
                        //horizontalModel.setBookCover(url1+newBook.getISBN()+url2);
                        //horizontalModel.setBookTitle(newBook.getBtitle());

                        //arrayListHorizontal_myBooks.add(horizontalModel);

                        arrayListHorizontal_myBooks.add(newBook);
                        bookList.add(newBook);
                    }
                    // add the rest of the categories in here
                    category.setArrayList(arrayListHorizontal_myBooks);
                    arrayListVertical.add(category);

                    category2 = new Category();
                    category2.setCategoryTitle("My Books");

                    for (int i=0; i<bookList.size(); i++) {
                        Book currentBook = bookList.get(i);
                        if (currentBook.getbOwner().contentEquals(bAuth.getCurrentUser().getEmail())) {

//                            HorizontalModel horizontalModel = new HorizontalModel();
//                            horizontalModel.setBookRating(currentBook.getRating());
//                            String url1 = "http://covers.openlibrary.org/b/isbn/";
//                            String url2 = "-M.jpg";
//                            horizontalModel.setBookCover(url1+currentBook.getISBN()+url2);


                            arrayListHorizontal_myBooks2.add(currentBook);
                        }
                    }

                    category2.setArrayList(arrayListHorizontal_myBooks2);
                    arrayListVertical.add(category2);
                    Collections.swap(arrayListVertical,0, 1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addListenerForSingleValueEvent(valueEventListener);

    }

}
