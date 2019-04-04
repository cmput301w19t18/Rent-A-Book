package com.example.cmput301w19t18.rent_a_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Book details.
 * Allows user to click a book image and view the details of the book.
 */
public class OldBookDetails extends AppCompatActivity  implements View.OnClickListener {
    private SearchAdapter adapter;
    private TextView title, author, status, isbn, description, owner;
    private RatingBar ratingbook;
    private DatabaseReference mDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Book> BookList;
    private ArrayList<Book> homeBookList;
    private Button req_button;
    /**
     * The Key.
     */
    public String key;
    private String bookCover;
    private ImageView bookimage;
    private FirebaseAuth mAuth;
    //private String mode;
    private Book curr_book;
    private boolean req = false;
    private String requesters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_book_details);

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
                                Intent intent1;
                                intent1 = new Intent(OldBookDetails.this, HomeActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.search:
                                //Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
                                Intent intent2;
                                intent2 = new Intent(OldBookDetails.this, SearchResultsActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.inbox:
                                //Toast.makeText(getApplicationContext(), "inbox", Toast.LENGTH_LONG).show();
                                Intent intent3 = new Intent(OldBookDetails.this, Inbox.class);
                                startActivity(intent3);
                                break;
                            case R.id.profile:
                                //Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();
                                Intent intent4;
                                intent4 = new Intent(OldBookDetails.this, ProfileActivity.class);
                                startActivity(intent4);
                                break;
                        }
                        return false;
                    }
                });


        String mode = "1";
        BookList = new ArrayList<>();
        homeBookList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Books");
        req_button = (Button) findViewById(R.id.req_button);
        adapter = new SearchAdapter(BookList);

        title = findViewById(R.id.title_textView);
        author = findViewById(R.id.auth_textView);
        status = findViewById(R.id.status_textView);
        isbn = findViewById(R.id.isbn_textView);
        description = findViewById(R.id.desc_textView);
        owner = findViewById(R.id.owner_textView);
        ratingbook = findViewById(R.id.bookRating_view);
        bookimage = findViewById(R.id.bookimage);

        if(getIntent().getStringExtra("mode").contains("1")){
            setBookTitle();
        }
        if(getIntent().getStringExtra("mode").contains("2")){
            homeDetails();

        }


        req_button.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        Toast.makeText(this,mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();


    }

    /**
     * Home details.
     * shows the details of the book that has been clicked
     */
    public void homeDetails() {
        final String rating = getIntent().getStringExtra("ratings");
        final String btitle = getIntent().getStringExtra("btitle");
        final String bdescription = getIntent().getStringExtra("bdescription");


        bookimage = findViewById(R.id.bookimage);
        bookCover = getIntent().getStringExtra("bookphoto");

        Picasso.get().load(bookCover).into(bookimage);
        Query query = mDatabase.orderByChild("btitle");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookList.clear();

                if (dataSnapshot.exists()){

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                        Book homebook = dataSnapshot1.getValue(Book.class);


                        if(homebook.getBtitle().contains(btitle)){
                            BookList.add(homebook);
                            curr_book = homebook;

                            title.setText(curr_book.getBtitle());

                            author.setText(curr_book.getAuthor());

                            status.setText(curr_book.getBstatus());

                            isbn.setText(curr_book.getISBN());

                            description.setText(bdescription);

                            owner.setText(curr_book.getbOwner());

                            ratingbook.setRating(curr_book.getRating());

                            String keyer = dataSnapshot1.getKey();
                            key = keyer;

                            // check if current user has requested the book
                            if (!curr_book.isRequesting(mAuth.getCurrentUser().getEmail())) {
                                req_button.setText(getString(R.string.request));
                            }
                            else if (curr_book.isRequesting(mAuth.getCurrentUser().getEmail())) {
                                req_button.setText(getString(R.string.cancel));
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);

    }


    /**
     * Sets book title.
     */
    public void setBookTitle() {
        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
        bookCover = getIntent().getStringExtra("photo");
        Picasso.get().load(bookCover).into(bookimage);
        final String bdescription2 = getIntent().getStringExtra("bdescription2");





        Query query = mDatabase.orderByChild("btitle");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookList.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Book newBook = snapshot.getValue(Book.class);

                        if (newBook.getBtitle().contains(title.getText())){
                            BookList.add(newBook);
                            String keyer = snapshot.getKey();
                            key = keyer;
                            ratingbook.setRating(newBook.getRating());
                            status.setText(newBook.getBstatus());
                            description.setText(bdescription2);
                            isbn.setText(newBook.getISBN());
                            owner.setText(newBook.getbOwner());

                            Toast.makeText(getApplicationContext(),newBook.getBstatus() ,Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addListenerForSingleValueEvent(valueEventListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v == req_button){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            //final DatabaseReference req_ref = FirebaseDatabase.getInstance().getReference("Books").child(key).child("bstatus");
            //DatabaseReference req_by = FirebaseDatabase.getInstance().getReference().child("Books").child("requestedBy");
            DatabaseReference req_by = ref.child("Books").child(key);//.child("requestedBy");
            req_by.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Book b = dataSnapshot.getValue(Book.class);
                    //List<String> r = Arrays.asList(str.split("\\s*,\\s*"));
                    String str = dataSnapshot.getValue(String.class);
                    List<String> r = Arrays.asList(str.split("\\s*,\\s*"));
                    if (r.contains(mAuth.getCurrentUser().getEmail())) {
                        Toast.makeText(getApplicationContext(),"Requested" ,Toast.LENGTH_LONG).show();
                        //handleRequests(true);
                        req_button.setText("Cancel");
                    }
                    else {
                        Toast.makeText(getApplicationContext(),str ,Toast.LENGTH_LONG).show();
                        req_button.setText("Request");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    public void handleRequests(Boolean mode) {
        if (mode == true) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Books").child(key);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Book b = dataSnapshot1.getValue(Book.class);
                        if (b.getBstatus().equals("Borrowed")) {
                            // disable clickable button
                            req_button.setClickable(false);
                        } else {
                            req_button.setClickable(true);
                            // update status to requested
                            b.setBstatus("Requested");
                            // update requester list
                            b.setRequestedBy(b.getRequestedBy().concat(mAuth.getCurrentUser().getEmail()));
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if (mode == false) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Books").child(key);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Book b = dataSnapshot.getValue(Book.class);
                    // remove user from requester list
                    b.getRequestedBy();

                    // if removal causes empty list; set status to available
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}

