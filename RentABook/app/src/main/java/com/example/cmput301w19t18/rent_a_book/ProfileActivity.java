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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addBook;

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


        addBook = (Button) findViewById(R.id.editButton);
        addBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == addBook){
            //go to register activity
            startActivity( new Intent(this,NewBookActivity.class));
        }

    }
}
