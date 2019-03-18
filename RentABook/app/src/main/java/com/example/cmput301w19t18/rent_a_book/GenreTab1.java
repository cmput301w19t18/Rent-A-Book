package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenreTab1 extends Fragment implements View.OnClickListener {

    /**
     * The GenreTab1
     *
     * This is the first tab for genres. It is a fragment containing 6
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
    private List<String> genreSelected = new ArrayList<>();

    public GenreTab1() {
        // constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref, container, false);

        // setting up the viewmodel which allows each fragment communicate with each other
        model = ViewModelProviders.of(this).get(GenreViewModel.class);

        // setting up the list of genres selected so the user can keep track of what
        // genres they have picked

        // setting up first 6 buttons representing genres
        Button comedy = (Button) v.findViewById(R.id.comedyButton);
        Button drama = (Button) v.findViewById(R.id.dramaButton);
        Button romance = (Button) v.findViewById(R.id.romanceButton);
        Button comic = (Button) v.findViewById(R.id.comicsButton);
        Button fantasy = (Button) v.findViewById(R.id.fantasyButton);
        Button horror = (Button) v.findViewById(R.id.horrorButton);

        //ImageView comedyHL = (ImageView) v.findViewById(R.id.comedyHighlight);
        //ImageView dramaHL = (ImageView) v.findViewById(R.id.dramaHighlight);
        //ImageView romanceHL = (ImageView) v.findViewById(R.id.romanceHighlight);
        //ImageView comicHL = (ImageView) v.findViewById(R.id.comicsHighlight);
        //ImageView fantasyHL = (ImageView) v.findViewById(R.id.fantasyHighlight);
        //ImageView horrorHL = (ImageView) v.findViewById(R.id.horrorHighlight);

        comedy.setOnClickListener(this);
        drama.setOnClickListener(this);
        romance.setOnClickListener(this);
        comic.setOnClickListener(this);
        fantasy.setOnClickListener(this);
        horror.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        preferenceList = model.getGenresSelected();
        selected = model.getCount();

        // setting up the viewmodel which allows each fragment communicate with each other
        //model = ViewModelProviders.of(this).get(GenreViewModel.class);
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.comedyButton:
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(0) == 0 && selected < 3) {
                    genreSelected.add("Comedy");
                    addGenre(0, "Picked comedy!");
                    // update preference list
                    //preferenceList.set(0,1);
                    //model.getCurrPickedGenres().postValue(genreSelected);
                    //Toast.makeText(this.getContext(),"Picked comedy!",Toast.LENGTH_SHORT).show();
                    }
                else if (preferenceList.get(0) == 1){
                    genreSelected.remove("Comedy");
                    removeGenre(0,"Comedy unselected!");
                    // update preference list
                    //preferenceList.set(0,0);

                    //Toast.makeText(this.getContext(),"Comedy unselected!",Toast.LENGTH_SHORT).show();
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }

                break;


            case R.id.dramaButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(1) == 0 && selected < 3) {
                    addGenre(1, "Picked drama!");
                }
                else if (preferenceList.get(1) == 1){
                    removeGenre(1, "Drama unselected!");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.romanceButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(2) == 0 && selected < 3) {
                    addGenre(2,"Picked romance!");
                }
                else if (preferenceList.get(2) == 1){
                    removeGenre(2, "Romance unselected!");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                    break;
            case R.id.comicsButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(3) == 0 && selected < 3) {
                    addGenre(3, "Picked comics!");
                }
                else if (preferenceList.get(3) == 1){
                    removeGenre(3, "Comics unselected!");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fantasyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(4) == 0 && selected < 3) {
                    addGenre(4, "Picked fantasy!");
                }
                else if (preferenceList.get(4) == 1){
                    removeGenre(4, "Fantasy unselected!");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.horrorButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(5) == 0 && selected < 3) {
                    addGenre(5, "Picked horror!");
                }
                else if (preferenceList.get(5) == 1){
                    removeGenre(5, "Horror unselected!");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    public void addGenre(int pos, String s) {
        // update preference list
        preferenceList.set(pos,1);
        model.setGenresSelected(preferenceList);
        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();
        model.getGenres().setValue(preferenceList);
        model.getCurrPickedGenres().postValue(genreSelected);
        model.setCount(selected+1);
        // set new list
        //model.setGenres();
        // update count
        //model.incrementNumSelected();
    }
    public void removeGenre(int pos, String s) {
        // update preference list
        //preferenceList.set(pos,0);
        // set new list
        //model.setGenres(preferenceList);
        // update count
        //model.decrementNumSelected();

        preferenceList.set(pos,0);
        model.setGenresSelected(preferenceList);
        model.getGenres().setValue(preferenceList);
        model.getCurrPickedGenres().postValue(genreSelected);
        model.setCount(selected-1);

        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();
    }
}
