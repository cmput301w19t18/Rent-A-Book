package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenreTabforBooks1 extends Fragment implements View.OnClickListener {

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
    private List<String> genreList = new ArrayList<>();
    private TextView genreText;

    public GenreTabforBooks1() {
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

        genreText = (TextView) getActivity().findViewById(R.id.genresSelected);

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
        genreList = model.getPickedGenres();

        genreText = (TextView) getActivity().findViewById(R.id.genresSelected);

        // setting up the viewmodel which allows each fragment communicate with each other
        //model = ViewModelProviders.of(this).get(GenreViewModel.class);
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.comedyButton:
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(0) == 0 && selected < 3) {
                    //genreList.add("Comedy");
                    addGenre(0, "Picked comedy!", "Comedy");

                    //model.getCurrPickedGenres().setValue(genreList);
                    //genreText.setText(genreList.toString());
                    // update preference list
                    //preferenceList.set(0,1);
                    //model.getCurrPickedGenres().postValue(genreSelected);
                    //Toast.makeText(this.getContext(),"Picked comedy!",Toast.LENGTH_SHORT).show();
                }
                else if (preferenceList.get(0) == 1){
                    //genreList.remove("Comedy");
                    removeGenre(0,"Comedy unselected!", "Comedy");
                    // update preference list
                    //preferenceList.set(0,0);
                    model.getCurrPickedGenres().setValue(genreList);
                    genreText.setText(genreList.toString());
                    //Toast.makeText(this.getContext(),"Comedy unselected!",Toast.LENGTH_SHORT).show();
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.dramaButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(1) == 0 && selected < 3) {
                    //genreList.add("Drama");
                    addGenre(1, "Picked drama!", "Drama");
                }
                else if (preferenceList.get(1) == 1){
                    //genreList.remove("Drama");
                    removeGenre(1, "Drama unselected!", "Drama");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.romanceButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(2) == 0 && selected < 3) {
                    //genreList.add("Romance");
                    addGenre(2,"Picked romance!", "Romance");
                }
                else if (preferenceList.get(2) == 1){
                    //genreList.remove("Romance");
                    removeGenre(2, "Romance unselected!", "Romance");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.comicsButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(3) == 0 && selected < 3) {
                    //genreList.add("Comics");
                    addGenre(3, "Picked comics!", "Comics");
                }
                else if (preferenceList.get(3) == 1){
                    //genreList.remove("Comics");
                    removeGenre(3, "Comics unselected!", "Comics");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fantasyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(4) == 0 && selected < 3) {
                    //genreList.add("Fantasy");
                    addGenre(4, "Picked fantasy!", "Fantasy");
                }
                else if (preferenceList.get(4) == 1){
                    //genreList.remove("Fantasy");
                    removeGenre(4, "Fantasy unselected!", "Fantasy");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.horrorButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList.get(5) == 0 && selected < 3) {
                    //genreList.add("Horror");
                    addGenre(5, "Picked horror!", "Horror");
                }
                else if (preferenceList.get(5) == 1){
                    //genreList.remove("Horror");
                    removeGenre(5, "Horror unselected!", "Horror");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
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
}

