package com.example.cmput301w19t18.rent_a_book;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Book details.
 * Allows user to click a book image and view the details of the book.
 */
public class BookDetails extends AppCompatActivity implements View.OnClickListener {
    private ImageView cover;
    private RatingBar rating;
    private TextView title;
    private TextView description;
    private TextView author;
    private TextView ISBN;
    private TextView owner;
    private TextView status;
    private Button request;

    // Firebase stuff
    private FirebaseAuth mAuth;
    private FirebaseDatabase fd;
    private DatabaseReference database;
    private DatabaseReference book_ref;
    private String key;
    private Boolean mode = false;


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
                                intent1 = new Intent(BookDetails.this, HomeActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.search:
                                //Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
                                Intent intent2;
                                intent2 = new Intent(BookDetails.this, SearchResultsActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.inbox:
                                //Toast.makeText(getApplicationContext(), "inbox", Toast.LENGTH_LONG).show();
                                Intent intent3 = new Intent(BookDetails.this, Inbox.class);
                                startActivity(intent3);
                                break;
                            case R.id.profile:
                                //Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();
                                Intent intent4;
                                intent4 = new Intent(BookDetails.this, ProfileActivity.class);
                                startActivity(intent4);
                                break;
                        }
                        return false;
                    }
                });

        mAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        key = fd.getReference("Books").push().getKey();
        book_ref = fd.getReference("Books");;//.child("requestedBy");
        Query query = book_ref.orderByChild("btitle");

        cover = (ImageView) findViewById(R.id.bookimage);
        rating = (RatingBar) findViewById(R.id.bookRating_view);
        title = (TextView) findViewById(R.id.title_textView);
        description = (TextView) findViewById(R.id.desc_textView);
        author = (TextView) findViewById(R.id.auth_textView);
        ISBN = (TextView) findViewById(R.id.isbn_textView);
        owner = (TextView) findViewById(R.id.owner_textView);
        status = (TextView) findViewById(R.id.status_textView);
        request = (Button) findViewById(R.id.req_button);

        Picasso.get().load(this.getIntent().getStringExtra("bookphoto")).into(cover);
        rating.setRating(this.getIntent().getExtras().getFloat("rating"));
        title.setText(this.getIntent().getExtras().getString("title"));
        description.setText(this.getIntent().getExtras().getString("description"));
        author.setText(this.getIntent().getExtras().getString("author"));
        ISBN.setText(this.getIntent().getExtras().getString("ISBN"));
        owner.setText(this.getIntent().getExtras().getString("owner"));
        status.setText(this.getIntent().getExtras().getString("status"));


        if (status.getText().equals("Available") || status.getText().equals("Requested")) {
            request.setClickable(true);
            request.setOnClickListener(this);

        } else if (status.getText().equals("Borrowed")) {
            request.setClickable(false);
            //request.setText("Request Unavailable");
        }

        database = FirebaseDatabase.getInstance().getReference();

        database.child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List books = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book b = snapshot.getValue(Book.class);
                    if (b.getBtitle().equals(title.getText())) {
                        books.add(b.getRequestedBy());
                        if (b.getBstatus().equals("Borrowed")) {
                            mode = true;
                        }
                    }
                }
                //Toast.makeText(getApplicationContext(), books.toString(), Toast.LENGTH_LONG).show();
                // if current user is requesting; set button to cancel else set to request
                if (books.contains(mAuth.getCurrentUser().getEmail())){
                    request.setText("Cancel");
                    //removeRequest();

                } else if (!books.contains(mAuth.getCurrentUser().getEmail()) && mode == false){
                    request.setText("Request");
                    //addRequest();
                }
                else if (mode == true) {
                    request.setText("Borrowed");
                    request.setClickable(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        database = FirebaseDatabase.getInstance().getReference();

        database.child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List books = new ArrayList<>();
                Book currBook = new Book();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book b = snapshot.getValue(Book.class);
                    if (b.getBtitle().equals(title.getText())) {
                        books.add(b.getRequestedBy());
                        //books.add(b);
                        currBook = b;
                        if (currBook.getBstatus().equals("Borrowed")) {
                            mode = true;
                        }
                    }
                }
                //Toast.makeText(getApplicationContext(), books.toString(), Toast.LENGTH_LONG).show();
                // split requestedBy string into list
                List<String> reqs = Arrays.asList(currBook.getRequestedBy().split("\\s*,\\s*"));


                // if current user is requesting; set button to cancel else set to request
                if (currBook.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                    request.setText("Cancel");
                    addRequest();
                    /*
                    reqs.remove(mAuth.getCurrentUser().getEmail());
                    // check if reqs is empty
                    if (reqs.isEmpty()) {
                        // set status to available and set requestedBy to ""
                        currBook.setBstatus("Available");
                        currBook.setRequestedBy("");
                    } else {
                        currBook.setRequestedBy(reqs.toString());
                    }
                    */
                    //database.child("Books").setValue(currBook);

                } else if (!books.contains(mAuth.getCurrentUser().getEmail()) && mode == false){
                    request.setText("Request");
                    removeRequest();
                    /*reqs.add(mAuth.getCurrentUser().getEmail());
                    currBook.setRequestedBy(reqs.toString());
                    currBook.setBstatus("Requested");
                    database.child("Books").setValue(currBook);
                    */
                }
                else if (mode == true) {
                    request.setText("Borrowed");
                    request.setClickable(false);
                }
                // if status is available; set button to request and if selected change status to requested


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

public void addRequest() {
    Toast.makeText(getApplicationContext(), "Added request!", Toast.LENGTH_LONG).show();
}

public void removeRequest() {
    Toast.makeText(getApplicationContext(), "Removed request!", Toast.LENGTH_LONG).show();
}

}