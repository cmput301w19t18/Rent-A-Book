package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NewBookActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseApp firebaseApp;

    //buttons and fields
    private Button SubmitB;
    private EditText ISBNF;
    private EditText AuthorF;
    private EditText TitleF;
    private EditText DescF;
    private String email;
    private String genre;
    private String strGenre;
    private Button GenreB;
    private Button genre1;
    private Button genre2;
    private Button genre3;
    private RatingBar RatingF;

    //latest book added:
    private Book addedBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_newbook);

        //initializing firebase auth object
        bAuth = FirebaseAuth.getInstance();

        //initializing fields and buttons
        SubmitB = (Button) findViewById(R.id.SubmitButton);
        ISBNF = (EditText) findViewById(R.id.ISBNBox);
        AuthorF = (EditText) findViewById(R.id.AuthBox);
        TitleF = (EditText) findViewById(R.id.TitleBox);
        DescF = (EditText) findViewById(R.id.DescriptionBox);
        RatingF = (RatingBar) findViewById(R.id.bookRating);

        GenreB = (Button) findViewById(R.id.GenreButton);

        genre1 = (Button) findViewById(R.id.genre1);
        genre2 = (Button) findViewById(R.id.genre2);
        genre3 = (Button) findViewById(R.id.genre3);

        SubmitB.setOnClickListener(this);
        GenreB.setOnClickListener(this);
        //email = b.getString("user_email");

        // unpack
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                genre = bundle.getString("genres");
                strGenre = bundle.getString("genresString");
                Toast.makeText(this,genre,Toast.LENGTH_SHORT).show();

                if(!strGenre.contains(" ")) {
                    genre1.setText(strGenre);
                }
                else {
                    String selGenres[] = strGenre.split(" ");
                    if (selGenres.length == 2 ) {
                        genre1.setText(selGenres[0]);
                        genre2.setText(selGenres[1]);
                    }
                    else {
                        genre1.setText(selGenres[0]);
                        genre2.setText(selGenres[1]);
                        genre3.setText(selGenres[2]);
                    }

                }

                // set info TODO last ditch effort is force user to enter genre first

                TitleF.setText(bundle.getString("title"));
                AuthorF.setText(bundle.getString("author"));
                ISBNF.setText(bundle.getString("ISBN"));
                RatingF.setRating(bundle.getFloat("rating"));

            }
        }
        else {
            genre = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
            //Toast.makeText(this,"No genres lol",Toast.LENGTH_SHORT).show();
        }

        //check if user is logged in. if not, returns null
        if (bAuth.getCurrentUser() == null){
            finish(); //close activity
            startActivity(new Intent(this, LoginActivity.class)); //returns to login screen
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");

    }


    private void saveBookInfo(){

        String author = AuthorF.getText().toString().trim();
        String ISBN = ISBNF.getText().toString().trim();
        String title = TitleF.getText().toString().trim();
        String id = databaseReference.push().getKey();
        float rating = RatingF.getRating();

        //some of these need to be changed every time a new book is added

        //Currently only is able to add values entered for a new book that is not already in the database

        //String genre = "000001010"; //going to be set by external function
        String requestedBy = "none";
        String owneremail = bAuth.getCurrentUser().getEmail();
        ArrayList<String> ownedBy = null;

        //ownedBy.add(bAuth.getCurrentUser().getEmail()); // sets as owner
        String status = "Available"; //as long as there is one copy of the book that is not requested or borrowed
        //Integer rating = 4;
        Integer copyCount = 1; //how do we check if a copy already exists and increment the counter? Do we actually need to keep track of this or will the owner
        //REMOVE COPYCOUNT FROM INDIVIDUAL BOOK CLASS

        //add the new book to firebase
        Book newBook = new Book(title, author, ISBN, status, rating, owneremail, genre, requestedBy);

        addedBook = newBook; //sets the last added book to this new book

        databaseReference.child(id).setValue(newBook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    Toast.makeText(NewBookActivity.this, "Book uploaded", Toast.LENGTH_SHORT).show();
                    // clear the genre lists and send this to the genre activity

                    GenreTabforBooks3 gt = new GenreTabforBooks3();
                    List<Integer> pList = new ArrayList<>();
                    for (int i = 0; i <18; i++) {
                        pList.add(0);
                    }
                    List<String> gList = new ArrayList<>();
                    gt.setPreferenceList(pList);
                    gt.setGenreList(gList);
                    finish();
                }
                if(!task.isSuccessful()){
                    Toast.makeText(NewBookActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    ////////////////// Overall book monitor check //////////////////

    //fetches a list of all books in the DB
    Query query = FirebaseDatabase.getInstance().getReference("uniqueBooks");
    ArrayList fetchedBookList = new ArrayList<>();

    //query book column, retrieve all unique books
    private void queryBooks(Query query) {
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fetchedBookList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book fetchBook = snapshot.getValue(Book.class);

                        fetchedBookList.add(fetchBook);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    //operations with the book monitor column in the database
    private void bookMonitor(){
        queryBooks(query);
        Boolean wasIn = Boolean.FALSE; //if the book is already in the DB or not

        //if the book is in there, then get all the info on it/
        Integer uCopyCount = 0;
        float uRating = 0;
        String uISBN = addedBook.getISBN();
        String uGenre = addedBook.getGenre();

        String title = addedBook.getBtitle();
        String author = addedBook.getAuthor();

        for (int i=0; i<fetchedBookList.size(); i++) {
            uniqueBook currentBook = (uniqueBook) fetchedBookList.get(i); //casted firebase object to uniquebook object
            //if (currentBook.getISBN() == addedBook.getISBN()){
            if (currentBook.getAuthor().equals(author) && currentBook.getTitle().equals(title)) {
                //means the book is already in the database. increment the count, add to the genre array
                wasIn = Boolean.TRUE;

                uCopyCount = currentBook.getCopyCount();
                uRating = currentBook.getRating();
                title = currentBook.getTitle();
                author = currentBook.getAuthor();

                break;
            }
        }


        //references the uniqueBooks section of the database
        DatabaseReference uniqueBookReference = FirebaseDatabase.getInstance().getReference("UniqueBooks");
        String id = uniqueBookReference.push().getKey(); //// CHECK: supposed to get the key of this particular book ////

        if (wasIn == Boolean.FALSE){
            //means the book is not already in the DB. Adds a new unique book.
            uniqueBook addedUniqueBook = new uniqueBook(uISBN, uGenre, author, title); //creates new uniqueBook object
            uniqueBookReference.setValue(addedUniqueBook);

        } else {
            //means the book is already in the database. increment the count, add to the genre array
            uniqueBook uniqueBookUpdate = new uniqueBook(uISBN, uGenre, uRating, uCopyCount, author, title);
            uniqueBookReference.child(id).setValue(uniqueBookUpdate); //should update the book of that value (id)

        }

    }
     
    ////////////////// Overall book monitor check //////////////////


    @Override //when you press the submit button
    public void onClick(View view) {

        if(view == SubmitB){
            saveBookInfo(); //calls the save function upon press
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        if (view == GenreB) {
            // goes to select genre
            // pack current info to send there and back
            Intent intent = new Intent(this, PickGenrePreferenceBooks.class);
            Bundle bookInfo = new Bundle();

            bookInfo.putString("title",TitleF.getText().toString().trim());

            bookInfo.putString("author", AuthorF.getText().toString().trim());

            bookInfo.putString("ISBN", ISBNF.getText().toString().trim());

            bookInfo.putFloat("rating", RatingF.getRating());
            intent.putExtras(bookInfo);
            startActivity(intent);
        }

    }



}
