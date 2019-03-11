package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

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

/**
 * The type Home activity.
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
    Category category;
    private ArrayList<HorizontalModel> arrayListHorizontal_myBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle b =  intent.getExtras();

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
                Bundle extras = new Bundle();
                Intent intent = new Intent(HomeActivity.this, NewBookActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtras(extras);
                startActivityForResult(intent, ADDING);
                update = true;
            }
        });

        bAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");
        arrayListHorizontal_myBooks = new ArrayList<>();

        category = new Category();
        category.setCategoryTitle("My Books");

        Query query1 = FirebaseDatabase.getInstance().getReference("Books")
                .orderByChild("bOwner")
                .equalTo(bAuth.getCurrentUser().getEmail());

        query1.addListenerForSingleValueEvent(valueEventListener);


// adding this one duplicates the entire list...

//        category = new Category();
//        category.setCategoryTitle("All Books");
//
//        Query query2 = FirebaseDatabase.getInstance().getReference("Books")
//                .orderByChild("btitle");
//
//        query2.addListenerForSingleValueEvent(valueEventListener);

    }

    /**
     * The Value event listener.
     */
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            arrayListHorizontal_myBooks.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book newBook = snapshot.getValue(Book.class);
                    HorizontalModel horizontalModel = new HorizontalModel();
                    horizontalModel.setBookRating(newBook.getRating());
                    String url1 = "http://covers.openlibrary.org/b/isbn/";
                    String url2 = "-M.jpg";
                    horizontalModel.setBookCover(url1+newBook.getISBN()+url2);
                    arrayListHorizontal_myBooks.add(horizontalModel);
                }
                category.setArrayList(arrayListHorizontal_myBooks);
                arrayListVertical.add(category);
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
