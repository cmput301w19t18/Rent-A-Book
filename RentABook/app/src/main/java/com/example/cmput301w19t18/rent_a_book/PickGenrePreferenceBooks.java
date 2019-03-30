package com.example.cmput301w19t18.rent_a_book;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmput301w19t18.rent_a_book.databinding.CustomGenrePickTabBinding;

import java.util.List;

public class PickGenrePreferenceBooks extends AppCompatActivity {

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

    private String author;
    private String title;
    private String ISBN;
    private float rating;
    private String bookurl;
    private String description;

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
        genreAdapter.addFragment(new GenreTabforBooks1(), "Page 1");
        genreAdapter.addFragment(new GenreTabforBooks2(), "Page 2");
        genreAdapter.addFragment(new GenreTabforBooks3(), "Page 3");

        viewPager.setAdapter(genreAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // setting up observer
        genres.setText(model.getGenresSelected().toString());
        final Observer<List<String>> genreObserver = new Observer<List<String>>() {
        //model.getCurrPickedGenres().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> s) {
                // Updating UI to show the selected genres
                genres.setText(s.toString());
            }
        };

        model.getCurrPickedGenres().observe(this, genreObserver);


        // unpack data from NewBookActivity
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                author = bundle.getString("author");
                title = bundle.getString("title");
                ISBN = bundle.getString("ISBN");
                rating = bundle.getFloat("rating");
                bookurl = bundle.getString("bookurl");
                description = bundle.getString("description");
            }
        }
        else {
            author = (String) savedInstanceState.getSerializable("author");
            title = (String) savedInstanceState.getSerializable("title");
            ISBN = (String) savedInstanceState.getSerializable("ISBN");
            rating = (float) savedInstanceState.getSerializable("rating");
            description = (String) savedInstanceState.getSerializable("description");
            bookurl = (String) savedInstanceState.getSerializable("bookurl");
        }

        // repack to send to fragment
        Bundle bookInfo =  new Bundle();
        bookInfo.putString("author", author);
        bookInfo.putString("title", title);
        bookInfo.putString("ISBN", ISBN);
        bookInfo.putFloat("rating", rating);
        bookInfo.putString("description", description);
        bookInfo.putString("bookurl", bookurl);

        GenreTabforBooks3 genreInfo = new GenreTabforBooks3();
        genreInfo.setArguments(bookInfo);

    }


}
