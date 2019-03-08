package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class PickGenrePreference extends FragmentActivity {
    static final int numPages = 3;
    private int[] preferenceList = new int [18];
    private int selectedNum = 0;
    private Button romance;

    BookFragmentAdapter bfAdapter;

    ViewPager bvPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_genre_pref);

        // bfAdapter = new BookFragmentAdapter(getSupportFragmentManager());

        //  bvPager = (ViewPager) findViewById(R.id.pager);
        //  bvPager.setAdapter(bfAdapter);

        romance = (Button) findViewById(R.id.romanceButton);

        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bvPager.setCurrentItem(0);
                if (view == romance) {
                    // test to see of button works
                    startActivity(new Intent(PickGenrePreference.this, MainActivity.class));
                    selectedNum += 1;
                    if (selectedNum > 3) {
                        // send out a toast
                    } else {
                        // highlight and track which has been selected
                        // set genre reference to romance
                        preferenceList[2] = 1;

                    }
                }
            }
        });
    }
}
