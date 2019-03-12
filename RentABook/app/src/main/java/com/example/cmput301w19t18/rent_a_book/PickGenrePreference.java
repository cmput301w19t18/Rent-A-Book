package com.example.cmput301w19t18.rent_a_book;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class PickGenrePreference extends AppCompatActivity {

    /**
     * PickGenrePreference
     *
     * This is the activity which holds the 3 fragments. It is connected
     * to the RegisterActivity and will allow the user to select 3 genres
     * they like and send this information to firebase.
     *
     * Currently it does not work yet as communication between fragments and
     * activity are still being implemented.
     *
     * author: Julieta Dikova
     *
     * sources:
     * https://medium.com/@droidbyme/android-material-design-tabs-tab-layout-with-swipe-884085ae80ff
     *
     */

    //static final int numPages = 3;
    //private int[] preferenceList = new int [18];
    //private int selectedNum = 0;
    //private int tabIcon = R.drawable.circle_unselected_gray_5dp;

   //private int[] tabIcons = {
   //         R.drawable.unselected_circle_light_gray_10dp,
   //         R.drawable.unselected_circle_light_gray_10dp,
   //         R.drawable.unselected_circle_light_gray_10dp,
   // };

    private GenreAdapter genreAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.custom_genre_pick_tab);

        //GenreViewModel model;

        viewPager = (ViewPager) findViewById(R.id.custPager);
        tabLayout = (TabLayout) findViewById(R.id.custTabLayout);

        genreAdapter = new GenreAdapter(getSupportFragmentManager());
        genreAdapter.addFragment(new GenreTab1(), "Page 1");
        genreAdapter.addFragment(new GenreTab2(), "Page 2");
        genreAdapter.addFragment(new GenreTab3(), "Page 3");

        viewPager.setAdapter(genreAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        //tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        // setting the first screen
        //viewPager.setCurrentItem(0);

    }
/*
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_genre_pick_main, container, false);
        romance = (Button) v.findViewById(R.id.romanceButton);
        // romance.setOnClickListener(this);

        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bvPager.setCurrentItem(0);
                if (view == romance) {
                // test to see of button works
                startActivity(new Intent(PickGenrePreference.this, MainActivity.class));
                //    selectedNum += 1;
                //    if (selectedNum > 3) {
                // send out a toast
                //   } else {
                // highlight and track which has been selected
                // set genre reference to romance
                //     preferenceList[2] = 1;

                //  }
                //  }
            }
        });
        return v;
    }
*/
    /*
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
    */
}
