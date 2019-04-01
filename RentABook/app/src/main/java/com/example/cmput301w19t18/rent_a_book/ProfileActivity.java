package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBook;
    private FirebaseAuth bAuth;
    private DatabaseReference databaseReference;
    private Button genre1, genre2, genre3;
    private TextView full_name, phone_number, email;
    private ImageView profilepic;
    private User user;
    private FirebaseDatabase mFireBaseD;
    private String[] strGenres = {"comedy", "drama", "romance", "comics", "fantasy", "horror", "mystery", "scifi", "western", "biography", "hisfic", "adventure", "nonfic", "ya", "thriller", "tragedy", "poetry", "children"};
    private String[] genres = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navView);


        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Intent intent;
                        switch (menuItem.getItemId()) {
                            case R.id.home:
                                intent = new Intent(ProfileActivity.this, HomeActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.search:
                                intent = new Intent(ProfileActivity.this, SearchResultsActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.inbox:
                                // TODO this needs to be implemented
                                break;
                            case R.id.profile:
                                // do nothing because we're already here
                                break;
                        }
                        return false;
                    }
                }
        );

        genre1 = (Button) findViewById(R.id.genre1);
        genre2 = (Button) findViewById(R.id.genre2);
        genre3 = (Button) findViewById(R.id.genre3);

        full_name = (TextView) findViewById(R.id.userFullNameProfile);
        phone_number = (TextView) findViewById(R.id.userPhoneProfile);
        email = (TextView) findViewById(R.id.userEmailProfile);
        profilepic = (ImageView) findViewById(R.id.profilePicture);

        addBook = (Button) findViewById(R.id.editButton);
        addBook.setOnClickListener(this);

        bAuth = FirebaseAuth.getInstance();
        mFireBaseD = FirebaseDatabase.getInstance();
        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("email")
                .equalTo(bAuth.getCurrentUser().getEmail());

        //Toast.makeText(getApplicationContext(), bAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();

        doQuery(query);

    }


    private void doQuery(Query query) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        user = snapshot.getValue(User.class);
                    }
                }
                String name = user.getFirstName() + " " + user.getLastName();
                full_name.setText(name);
                phone_number.setText(user.getPhoneNum());
                email.setText(user.getEmail());

                //Uri myUri = Uri.parse(user.getURI());
                //Uri myUri = Uri.parse(user.getURI());
                Uri myUri = Uri.parse(user.getURI());
                //URI myUri = null;
                //myUri.create(user.getURI());

                //URL url = myUri.toURL();

                //File profilePic = new File(Uri.parse(user.getURI()).toString());


                //Toast.makeText(getApplicationContext(), myUri.toString(), Toast.LENGTH_LONG).show();
                //Picasso.get().load(myUri).placeholder(R.drawable.default_profile_pic_olive).into(profilepic);
                Picasso.get().load(myUri).into(profilepic);

                /*
                URL url;
                try {
                    url = myUri.;
                    System.out.println("URL from URI: " + url);
                }
                catch (MalformedURLException e) {
                    System.out.println("Malformed URL: " + e.getMessage());
                }
                */

                //Toast.makeText(getApplicationContext(), profilePic.toString(), Toast.LENGTH_LONG).show();
                //Picasso.get().load(user.getURI()).into(profilepic);

                //Picasso.with(this).load(myUri).into(profilepic);


                //Picasso picassoInstance = new Picasso.Builder(ProfileActivity.this.getApplicationContext())
                //        .addRequestHandler(new FireBaseRequestHandler())
                 //       .build();


                String genreList = user.getPrefList();
                String[] gList = genreList.split(" ");
                int j = 0;
                for (int i = 0; i < gList.length; i++) {
                    if (gList[i].equals("1")) {
                        genres[j] = strGenres[i];
                        j++;
                    }

                }
                if (genres.length == 1) {
                    genre1.setText(genres[0]);
                }
                else if (genres.length == 2) {
                    genre1.setText(genres[0]);
                    genre2.setText(genres[1]);
                }
                else if (genres.length == 3) {
                    genre1.setText(genres[0]);
                    genre2.setText(genres[1]);
                    genre3.setText(genres[2]);
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
        if (v == addBook){
            //go to register activity
            startActivity( new Intent(this,NewBookActivity.class));
        }

    }
}
