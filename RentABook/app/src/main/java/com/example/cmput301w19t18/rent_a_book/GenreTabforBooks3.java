package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenreTabforBooks3 extends Fragment implements View.OnClickListener {

    /**
     * The GenreTab3
     *
     * This is the third tab for genres. It is a fragment containing the last 6
     * of the available genres. It will send the selected data to firebase when the
     * signup process is complete.
     *
     * Currently it is clickable, but does not send info to firebase
     * or save it yet.
     *
     * author: Julieta Dikova
     *
     * sources:
     * https://medium.com/@droidbyme/android-material-design-tabs-tab-layout-with-swipe-884085ae80ff
     *
     */
    private GenreViewModel model;
    private List<Integer> preferenceList;
    private int selected;
    private List<String> genreList = new ArrayList<>();
    private TextView genreText;

    private String author;
    private String title;
    private String ISBN;
    private float rating;
    private String descrip;
    private Bitmap coverIMG;

    public GenreTabforBooks3() {
        // constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref_books3, container, false);

        // setting up the viewmodel which allows each fragment communicate with each other
        model = ViewModelProviders.of(this).get(GenreViewModel.class);


        // unpack data from activity
        if (savedInstanceState == null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            if (bundle != null) {
                // TODO credit https://www.codexpedia.com/android/passing-data-to-activity-and-fragment-in-android/
                author = getActivity().getIntent().getExtras().getString("author");
                title = getActivity().getIntent().getExtras().getString("title");
                ISBN = getActivity().getIntent().getExtras().getString("ISBN");
                rating = getActivity().getIntent().getExtras().getFloat("rating");
                descrip = getActivity().getIntent().getExtras().getString("description");
                coverIMG = getActivity().getIntent().getExtras().getParcelable("coverPic");
            }
        }


        Button nonfic = (Button) v.findViewById(R.id.nonficButton);
        Button ya = (Button) v.findViewById(R.id.yaButton);
        Button thriller = (Button) v.findViewById(R.id.thrillerButton);
        Button tragedy = (Button) v.findViewById(R.id.tragedyButton);
        Button poetry = (Button) v.findViewById(R.id.poetryButton);
        Button children = (Button) v.findViewById(R.id.childButton);
        TextView done = (TextView) v.findViewById(R.id.done);

        nonfic.setOnClickListener(this);
        ya.setOnClickListener(this);
        thriller.setOnClickListener(this);
        tragedy.setOnClickListener(this);
        poetry.setOnClickListener(this);
        children.setOnClickListener(this);
        done.setOnClickListener(this);
        return v;

    }
    @Override
    public void onClick(View v) {
        preferenceList = model.getGenresSelected();
        selected = model.getCount();
        genreList = model.getPickedGenres();
        genreText = (TextView) getActivity().findViewById(R.id.genresSelected);

        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.nonficButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(12) == 0 && selected < 3) {
                    addGenre(12,"Picked non-fiction!", "Non-Fiction");
                }
                else if (preferenceList.get(12) == 1) {
                    removeGenre(12, "Non-Fiction unselected!", "Non-Fiction");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yaButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(13) == 0 && selected < 3) {
                    addGenre(13,"Picked YA!", "Young-Adult");
                }
                else if (preferenceList.get(13) == 1) {
                    removeGenre(13, "YA unselected!", "Young-Adult");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.thrillerButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(14) == 0 && selected < 3) {
                    addGenre(14,"Picked thriller!", "Thriller");
                }
                else if (preferenceList.get(14) == 1) {
                    removeGenre(14, "Thriller unselected!", "Thriller");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tragedyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(15) == 0 && selected < 3) {
                    addGenre(15,"Picked tragedy!", "Tragedy");
                }
                else if (preferenceList.get(15) == 1) {
                    removeGenre(15, "Tragedy unselected!", "Tragedy");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.poetryButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(16) == 0 && selected < 3) {
                    addGenre(16,"Picked poetry!", "Poetry");
                }
                else if (preferenceList.get(16) == 1) {
                    removeGenre(16, "Poetry unselected!", "Poetry");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.childButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(17) == 0 && selected < 3) {
                    addGenre(17,"Picked children's!", "Children's");
                }
                else if (preferenceList.get(17) == 1) {
                    removeGenre(17, "Children's unselected!", "Children's");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.done:
                // check if three genres are selected;
                // if not ask to pick three genres
                if (selected < 1) {
                    Toast.makeText(this.getContext(), "Please select at least one genre", Toast.LENGTH_SHORT).show();
                }
                // else continue to successful registration page
                else {
                    // add genres here
                    addGenres(preferenceList, genreList);
                }
                break;
        }

    }

    public void addGenre(int pos, String s, String sGenre) {
        // update preference list
        preferenceList.set(pos,1);
        model.setGenresSelected(preferenceList);
        // update the MutableLiveData genres array
        model.getGenres().setValue(preferenceList);
        //increment counter
        model.setCount(selected+1);

        // update genre list
        genreList.add(sGenre);
        model.setPickedGenres(genreList);
        // update the MutableLiveData genres string list to display
        model.getCurrPickedGenres().setValue(genreList);
        // display
        genreText.setText(genreList.toString());

        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();
    }

    public void removeGenre(int pos, String s, String sGenre) {
        // update preference list
        preferenceList.set(pos,0);
        model.setGenresSelected(preferenceList);
        // update the MutableLiveData genres array
        model.getGenres().setValue(preferenceList);
        // decrement counter
        model.setCount(selected-1);

        // update genre list
        genreList.remove(sGenre);
        model.setPickedGenres(genreList);
        // update the MutableLiveData genres string list to display
        model.getCurrPickedGenres().setValue(genreList);
        // display
        genreText.setText(genreList.toString());


        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();
    }

    public void addGenres(List<Integer> prefList, List<String> genList) {
        // string builder here to convert prefList to string
        StringBuilder strBuild = new StringBuilder();
        Iterator<Integer> i = prefList.iterator();
        while(i.hasNext()) {
            strBuild.append(i.next());
            if(i.hasNext()) {
                strBuild.append(" ");
            }
        }

        StringBuilder strBuild2 = new StringBuilder();
        Iterator<String> s = genList.iterator();
        while(s.hasNext()) {
            strBuild2.append(s.next());
            if(s.hasNext()) {
                strBuild2.append(" ");
            }
        }

        // clear genre lists just to avoid errors that may crop up from unclearing
        genList.clear();
        prefList.clear();

        // Send genres and all previous info back to NewBookActivity
        Intent intent = new Intent(this.getContext(), NewBookActivity.class);
        Bundle userInfo =  new Bundle();
        userInfo.putStringArrayList("genreArray",(ArrayList<String>) genreList);
        userInfo.putString("genres",strBuild.toString());
        userInfo.putString("genresString", strBuild2.toString());

        userInfo.putString("author", author);
        userInfo.putString("title", title);
        userInfo.putString("ISBN", ISBN);
        userInfo.putFloat("rating", rating);

        userInfo.putParcelable("coverPic", coverIMG);
        userInfo.putString("description", descrip);

        intent.putExtras(userInfo);

        startActivity(intent);

    }

    public List<Integer> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<Integer> preferenceList) {
        this.preferenceList = preferenceList;
    }
    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }
}
