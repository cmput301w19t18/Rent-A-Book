package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    TextView title;
    TextView author;
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
        mode = "1";
        setContentView(R.layout.activity_book_details);
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
        bookimage = findViewById(R.id.bookimage);
        bookCover = getIntent().getStringExtra("bookphoto");
        Picasso.get().load(bookCover).into(bookimage);
        Query query = mDatabase.orderByChild("btitle");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookList.clear();

                if (dataSnapshot.exists()){
                    homeBookList.clear();
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                        Book homebook = dataSnapshot1.getValue(Book.class);


                        if(homebook.getBtitle().contains(btitle)){
                            Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                            BookList.add(homebook);
                            curr_book = homebook;
                            title = findViewById(R.id.title_textView);
                            title.setText(curr_book.getBtitle());
                            author = findViewById(R.id.auth_textView2);
                            author.setText(curr_book.getAuthor());
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
        author = findViewById(R.id.auth_textView2);
        author.setText(getIntent().getStringExtra("author"));
        bookimage = findViewById(R.id.bookimage);
        bookCover = getIntent().getStringExtra("photo");
        Picasso.get().load(bookCover).into(bookimage);


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

                            Toast.makeText(getApplicationContext(),newBook.getBstatus() + key,Toast.LENGTH_LONG).show();



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
