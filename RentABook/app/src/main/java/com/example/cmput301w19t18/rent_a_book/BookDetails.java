package com.example.cmput301w19t18.rent_a_book;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Book details.
 * Allows user to click a book image and view the details of the book.
 */
public class BookDetails extends AppCompatActivity  implements View.OnClickListener {
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
    private Book curr_book;
    private boolean req = false;
    private String requesters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String mode = "1";
        setContentView(R.layout.activity_book_details2);
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
            DatabaseReference req_ref = FirebaseDatabase.getInstance().getReference("Books").child(key).child("bstatus");
            DatabaseReference req_by = FirebaseDatabase.getInstance().getReference("Books").child(key).child("requestedBy");
            if (!req) {
                req_ref.setValue("Requested");
                status.setText(getString(R.string.requested));

                // add user to request list
                requesters = curr_book.addRequester(mAuth.getCurrentUser().getEmail());
                req_by.setValue(requesters);

                req_button.setText("Cancel");
                req = true;

            }
            else if (req){
                // remove the user from request
                // set button text back to request
                req_ref.setValue("Available");

                // remove user request list
                List<String> l = curr_book.stringToList(curr_book.getRequestedBy());
                l.remove(mAuth.getCurrentUser().getEmail());

                // set list back to string
                requesters = curr_book.listToString(l);
                // clear list
                l.clear();

                // reset string of requesting users
                curr_book.setRequestedBy(requesters);
                req_by.setValue(requesters);

                status.setText("Available");
                req_button.setText("Request");
                req = false;
            }
        }
    }
}
