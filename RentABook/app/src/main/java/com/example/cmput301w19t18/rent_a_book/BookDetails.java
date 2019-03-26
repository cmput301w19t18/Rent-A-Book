package com.example.cmput301w19t18.rent_a_book;

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

public class BookDetails extends AppCompatActivity  implements View.OnClickListener {
    SearchAdapter adapter;
    TextView title, author, status, isbn, description, owner;
    RatingBar ratingbook;
    private DatabaseReference mDatabase;
    private ArrayList<Book> BookList;
    private ArrayList<Book> homeBookList;
    private Button req_button;
    public String key;
    String bookCover;
    ImageView bookimage;
    FirebaseAuth mAuth;
    String mode;
    Book curr_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mode = "1";
        setContentView(R.layout.activity_book_details2);
        BookList = new ArrayList<>();
        homeBookList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Books");
        req_button = (Button) findViewById(R.id.req_button);
        adapter = new SearchAdapter(BookList);

        if(getIntent().getStringExtra("mode").contains("1")){
            setBookTitle();
        }
        if(getIntent().getStringExtra("mode").contains("2")){
            //Toast.makeText(getApplicationContext(), getIntent().getStringExtra("btitle"), Toast.LENGTH_LONG).show();
            homeDetails();

        }

        //setBookTitle();
        req_button.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();


    }

    public void homeDetails() {

        //Toast.makeText(getApplicationContext(), "home book details", Toast.LENGTH_LONG).show();
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
                            //Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();

                            BookList.add(homebook);
                            curr_book = homebook;

                            title = findViewById(R.id.title_textView);
                            title.setText(curr_book.getBtitle());

                            author = findViewById(R.id.auth_textView);
                            author.setText(curr_book.getAuthor());

                            status = findViewById(R.id.status_textView);
                            status.setText(curr_book.getBstatus());

                            isbn = findViewById(R.id.isbn_textView);
                            isbn.setText(curr_book.getISBN());

                            description = findViewById(R.id.desc_textView);
                            description.setText(bdescription);

                            owner = findViewById(R.id.owner_textView);
                            owner.setText(curr_book.getbOwner());

                            ratingbook = findViewById(R.id.bookRating_view);
                            ratingbook.setRating(curr_book.getRating());

                            String keyer = dataSnapshot1.getKey();
                            key = keyer;
                            //Toast.makeText(getApplicationContext(), "size" + homeBookList.size() ,Toast.LENGTH_LONG).show();


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


    public void setBookTitle() {
        title = findViewById(R.id.title_textView);
        title.setText(getIntent().getStringExtra("title"));
        author = findViewById(R.id.auth_textView);
        author.setText(getIntent().getStringExtra("author"));
        bookimage = findViewById(R.id.bookimage);
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
                            ratingbook = findViewById(R.id.bookRating_view);
                            ratingbook.setRating(newBook.getRating());

                            status = findViewById(R.id.status_textView);
                            status.setText(newBook.getBstatus());
                            description = findViewById(R.id.desc_textView);
                            description.setText(bdescription2);
                            isbn = findViewById(R.id.isbn_textView);
                            isbn.setText(newBook.getISBN());
                            owner = findViewById(R.id.owner_textView);
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

    @Override
    public void onClick(View v) {
        if (v == req_button){
            DatabaseReference req_ref = FirebaseDatabase.getInstance().getReference("Books").child(key).child("bstatus");
            DatabaseReference req_by = FirebaseDatabase.getInstance().getReference("Books").child(key).child("requestedBy");
            req_ref.setValue("Requested");
            req_by.setValue(mAuth.getCurrentUser().getEmail().toString());

            //BookList.get(0).setBstatus("Requested");
            Toast.makeText(getApplicationContext(), "Requested", Toast.LENGTH_LONG).show();

        }
    }
}
