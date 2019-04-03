package com.example.cmput301w19t18.rent_a_book;

import android.app.ActionBar;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.support.v4.view.ViewPager;

import com.example.cmput301w19t18.rent_a_book.databinding.CustomGenrePickTabBinding;

import java.util.List;

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


    private GenreAdapter genreAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private GenreViewModel model;
    private CustomGenrePickTabBinding binding;
    private MutableLiveData<List<String>> mLD;

    private String email;
    private String password;
    private String phone;
    private String fName;
    private String lName;
    private String pPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.custom_genre_pick_tab);

        model = ViewModelProviders.of(this).get(GenreViewModel.class);
        final MutableLiveData<List<String>> genreList = model.getCurrPickedGenres();

        binding = DataBindingUtil.setContentView(this, R.layout.custom_genre_pick_tab);

        // TODO credit this
        //enabling MutableLiveData to be updated on UI
        binding.setLifecycleOwner(this);
        binding.setViewModel(model);

        viewPager = (ViewPager) findViewById(R.id.custPager);
        tabLayout = (TabLayout) findViewById(R.id.custTabLayout);
        final TextView genres = (TextView) findViewById(R.id.genresSelected);

        genreAdapter = new GenreAdapter(getSupportFragmentManager());
        genreAdapter.addFragment(new GenreTab1(), "Page 1");
        genreAdapter.addFragment(new GenreTab2(), "Page 2");
        genreAdapter.addFragment(new GenreTab3(), "Page 3");

        viewPager.setAdapter(genreAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // setting up observer
        genres.setText(model.getGenresSelected().toString());
        final Observer<List<String>> genreObserver = new Observer<List<String>>() {
            //model.getCurrPickedGenres().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                // Updating UI to show the 3 selected genres
                genres.setText(s.toString());
            }
        };

        model.getCurrPickedGenres().observe(this, genreObserver);

        // unpack data from first activity
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                email = bundle.getString("email");
                password = bundle.getString("password");
                phone = bundle.getString("phoneNum");
                fName = bundle.getString("firstName");
                lName = bundle.getString("lastName");
                pPic = bundle.getString("profileURI");
            }
        }
        else {
            email = (String) savedInstanceState.getSerializable("email");
            password = (String) savedInstanceState.getSerializable("password");
            phone = (String) savedInstanceState.getSerializable("phoneNum");
            fName = (String) savedInstanceState.getSerializable("firstName");
            lName = (String) savedInstanceState.getSerializable("lastName");
            pPic = (String) savedInstanceState.getSerializable("profileURI");
        }

        // repack to send to fragment
        Bundle userInfo =  new Bundle();
        userInfo.putString("firstName", fName);
        userInfo.putString("lastName", lName);
        userInfo.putString("phoneNum", phone);
        userInfo.putString("email",email);
        userInfo.putString("password", password);
        userInfo.putString("profileURI", pPic);

        GenreTab3 finalInfo = new GenreTab3();
        finalInfo.setArguments(userInfo);

    }

}
