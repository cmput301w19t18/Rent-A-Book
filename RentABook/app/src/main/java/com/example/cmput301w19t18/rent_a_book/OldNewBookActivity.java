package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.android.volley.Request;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.RatingBar;

import android.widget.RatingBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import java.io.StringReader;
import java.net.URL;

import java.util.ArrayList;


/**
 * The type Old new book activity.
 */
public class OldNewBookActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference unique;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseApp firebaseApp;
    private static final String TAG = "myActivity";

    //buttons and fields
    private Button SubmitB;
    private EditText ISBNF;
    private EditText AuthorF;
    private EditText TitleF;
    private EditText DescF;
    private String email;
    private Button btnPhoto;
    private ImageView imageView;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private String genre;
    private String strGenre;
    private Button GenreB;
    private Button genre1;
    private Button genre2;
    private Button genre3;
    private Button ScanB;
    private Button PhotoB;
    private RatingBar RatingF;
    private ImageView CoverB;

    //latest book added:
    private Book addedBook;
    private uniqueBook uBook;
    private String bookurl, download_url;

    //volley stuff
    private RequestQueue mQueue;

    //record of books added by ISBN
    private Integer[] booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Intent intent = getIntent();
        //Bundle b =  intent.getExtras();

        setContentView(R.layout.activity_newbook);

        //initializing firebase auth object
        bAuth = FirebaseAuth.getInstance();

        //initializing volley request
        mQueue = Volley.newRequestQueue(this);

        //initializing fields and buttons
        SubmitB = (Button) findViewById(R.id.SubmitButton);
        ISBNF = (EditText) findViewById(R.id.ISBNBox);
        AuthorF = (EditText) findViewById(R.id.AuthBox);
        TitleF = (EditText) findViewById(R.id.TitleBox);
        DescF = (EditText) findViewById(R.id.DescriptionBox);
        btnPhoto = findViewById(R.id.AddPhotoButton);
        imageView = findViewById(R.id.image);
        SubmitB.setOnClickListener(this);
        //email = b.getString("user_email");

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });

        RatingF = (RatingBar) findViewById(R.id.bookRating);
        CoverB = (ImageView) findViewById(R.id.imageBookCover);

        GenreB = (Button) findViewById(R.id.GenreButton);
        ScanB = (Button) findViewById(R.id.ScanButton);
        PhotoB = (Button) findViewById(R.id.AddPhotoButton);

        genre1 = (Button) findViewById(R.id.genre1);
        genre2 = (Button) findViewById(R.id.genre2);
        genre3 = (Button) findViewById(R.id.genre3);

        SubmitB.setOnClickListener(this);
        GenreB.setOnClickListener(this);
        ScanB.setOnClickListener(this);
        PhotoB.setOnClickListener(this);

        PhotoB.setOnClickListener(this);

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
                DescF.setText(bundle.getString("description"));
                ISBNF.setText(bundle.getString("ISBN"));
                RatingF.setRating(bundle.getFloat("rating"));
                // make checks for if from scanner or camera (maybe)

                bookurl = bundle.getString("bookurl");
                Picasso.get().load(bookurl).into(CoverB);

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
        unique = FirebaseDatabase.getInstance().getReference().child("UniqueBooks");

    }

    private void addButton(){
        Intent intent = new Intent(this, addPhotoActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    ISBNF.setText(barcode.displayValue);

                    String jsonText = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + barcode.displayValue + "&key=AIzaSyBazEyC2EkUpHmYKCh3NNS-Zq2inaSB7_0";

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, jsonText, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //String test = response.getString("kind");
                                JSONArray jsonArray = response.getJSONArray("items");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);

                                String json_title = jsonObject.getJSONObject("volumeInfo").getString("title");
                                String json_author = jsonObject.getJSONObject("volumeInfo").getJSONArray("authors").get(0).toString();
                                String json_desc = jsonObject.getJSONObject("volumeInfo").getString("description");
                                String json_img = jsonObject.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");

                                TitleF.setText(json_title);
                                AuthorF.setText(json_author);
                                DescF.setText(json_desc);
                                bookurl = json_img;
                                Picasso.get().load(bookurl).into(CoverB);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    mQueue.add(request);

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

        else if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                filePath = data.getParcelableExtra("filepath");
                download_url = data.getParcelableExtra("download_url");

                if(filePath != null) {
                    Toast.makeText(getApplicationContext(), "Uri: " + filePath.toString(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(filePath).into(CoverB);
                    //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    //Bitmap b = getResizedBitmap(bitmap, 150, 240);
                    //CoverB.setImageBitmap(b);
                    //imageView.setImageBitmap(b);
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }


    /**
     * Gets resized bitmap.
     *
     * @param bm        the bm
     * @param newWidth  the new width
     * @param newHeight the new height
     * @return the resized bitmap
     */
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    /**
     * Scan barcode.
     *
     * @param v the v
     */
    public void scanBarcode(View v) {
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, 0);
    }



    private void saveBookInfo() {

        final String author = AuthorF.getText().toString().trim();
        final String ISBN = ISBNF.getText().toString().trim();
        final String title = TitleF.getText().toString().trim();
        final String id = databaseReference.push().getKey();
        final String Uid = unique.push().getKey();

        final uniqueBook[] newUBook = new uniqueBook[1];
        //some of these need to be changed every time a new book is added

        //Currently only is able to add values entered for a new book that is not already in the database

        final String requestedBy = "";
        final String email = bAuth.getCurrentUser().getEmail();

        final String status = "Available"; //as long as there is one copy of the book that is not requested or borrowed

        //Integer copyCount = 1; //how do we check if a copy already exists and increment the counter? Do we actually need to keep track of this or will the owner

        //uniqueBook newUBook = new uniqueBook(ISBN, genre, author, title);
        /*
        // check if this book already has a copy in firebase
        unique.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child(title).exists()) {
                    // set uniquebook
                    newUBook[0] = new uniqueBook(ISBN, genre, author, title);
                    databaseReference.child(Uid).setValue(newUBook).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isComplete()){
                                Toast.makeText(NewBookActivity.this, "Unique Book uploaded", Toast.LENGTH_SHORT).show();
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
                // if not unique; call unique book information from firebase -> update unique in firebase and fill in necessary info for book then add book object to firebase
                else if (dataSnapshot.child(title).exists()) {
                    // fetch book info from firebase
                    // recalculate the avg's. and update the unique book info
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //unique.child("UniqueBooks").setValue(newUBook[0]);
        */

        // check if this book already has a copy in firebase
        unique.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child(title).exists()) {
                    Toast.makeText(OldNewBookActivity.this, "No Unique Book!", Toast.LENGTH_SHORT).show();
                    //add the new book to firebase
                    Book newBook = new Book(title, author, ISBN, status, RatingF.getRating(), email, genre, requestedBy);
                    //Book newBook = new Book(title, author, genre, ISBN, status, requestedBy, rating, email);

                    databaseReference.child(id).setValue(newBook).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                if (task.isComplete()){
                                                                                                    Toast.makeText(OldNewBookActivity.this, "Book uploaded", Toast.LENGTH_SHORT).show();
                                                                                                    finish();
                                                                                                }
                                                                                                if(!task.isSuccessful()){
                                                                                                    Toast.makeText(OldNewBookActivity.this,"error",Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                                else {
                                                                                                    Toast.makeText(OldNewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            }
                                                                                        }
                    );

                    newUBook[0] = new uniqueBook(ISBN, genre, author, title);
                    newUBook[0].setAvgGenre(newBook.getGenre());
                    newUBook[0].setRating(newBook.getRating());
                    newUBook[0].setAvgRating();
                    unique.child(Uid).setValue(newUBook[0]).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                      @Override
                                                                                      public void onComplete(@NonNull Task<Void> task) {
                                                                                          if (task.isComplete()){
                                                                                              Toast.makeText(OldNewBookActivity.this, "Unique Book uploaded", Toast.LENGTH_SHORT).show();
                                                                                              finish();
                                                                                          }
                                                                                          if(!task.isSuccessful()){
                                                                                              Toast.makeText(OldNewBookActivity.this,"error",Toast.LENGTH_SHORT).show();
                                                                                          }
                                                                                          else {
                                                                                              Toast.makeText(OldNewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                                                                                          }
                                                                                      }
                                                                                  }
                    );

                }
                else {
                    Toast.makeText(OldNewBookActivity.this, "Unique Book Exists!", Toast.LENGTH_SHORT).show();

                    DatabaseReference uBook_ref = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid);
                    DatabaseReference bTitle = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid).child("title");
                    DatabaseReference bAuthor = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid).child("author");
                    // update unique books count
                    DatabaseReference cCount = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid).child("copyCount");
                    DatabaseReference bGenre = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid).child("avgGenre");
                    DatabaseReference bAvgRating = FirebaseDatabase.getInstance().getReference("UniqueBooks").child(Uid).child("avgRating");


                    int c = Integer.parseInt(uBook_ref.child("copyCount").toString()) + 1;
                    cCount.setValue(c);

                    //String bTitle = uBook_ref.child("title").toString();
                    //String bAuthor = uBook_ref.child("author").toString();

                    // calculate avg genre
                    String bAvgGenre = newUBook[0].setAvgGenre(genre);
                    bGenre.setValue(bAvgGenre);

                    // calculate avg rating
                    //float avgRating = Float.parseFloat(uBook_ref.child("avgRating").toString());
                    newUBook[0].setRating(RatingF.getRating());
                    float avgRating = newUBook[0].setAvgRating();
                    bAvgRating.setValue(avgRating);
                    Book newBook = new Book(bTitle.toString(), bAuthor.toString(), ISBN, status, avgRating, email, bAvgGenre ,requestedBy);
                    // increase copy count in unique books
                    // create new book object -> but fetch info from unique books
                    databaseReference.child(id).setValue(newBook).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                if (task.isComplete()){
                                                                                                    Toast.makeText(OldNewBookActivity.this, "Book uploaded", Toast.LENGTH_SHORT).show();
                                                                                                    finish();
                                                                                                }
                                                                                                if(!task.isSuccessful()){
                                                                                                    Toast.makeText(OldNewBookActivity.this,"error",Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                                else {
                                                                                                    Toast.makeText(OldNewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            }
                                                                                        }


                    );


                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*

        uniqueBook newUBook = new uniqueBook(ISBN, genre, author, title);
        unique.child(id).setValue(newUBook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    Toast.makeText(NewBookActivity.this, "Unique Book uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if(!task.isSuccessful()){
                    Toast.makeText(NewBookActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

        // if not; set as uniquebook and add book object to firebase


        //add the new book to firebase
        Book newBook = new Book(title, author, ISBN, status, RatingF.getRating(), email, genre, requestedBy);
        //Book newBook = new Book(title, author, genre, ISBN, status, requestedBy, rating, email);

        databaseReference.child(id).setValue(newBook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    Toast.makeText(NewBookActivity.this, "Book uploaded", Toast.LENGTH_SHORT).show();
                    finish();

                }
                if(!task.isSuccessful()){
                    Toast.makeText(NewBookActivity.this,"error",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(NewBookActivity.this,"uploaded",Toast.LENGTH_SHORT).show();
                }

            }
        }

        );
        */
        //databaseReference.child("books").setValue(newBook); //should put the book in the db under books
        //finish();
    }


    ////////////////// Overall book monitor check //////////////////


    @Override //when you press any of the buttons
    public void onClick(View view) {

        if(view == ScanB) {
            scanBarcode(view);
        }

        if(view == SubmitB){
            saveBookInfo(); //calls the save function upon press
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        if (view == PhotoB) {
            addButton();
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

            bookInfo.putString("description", DescF.getText().toString().trim());

            bookInfo.putString("bookurl", bookurl);

            //bookInfo.putString("imgURL", filePath.toString());

            intent.putExtras(bookInfo);
            startActivity(intent);
        }

    }


}

