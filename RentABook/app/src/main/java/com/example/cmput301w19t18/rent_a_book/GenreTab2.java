package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

public class GenreTab2 extends Fragment implements View.OnClickListener {

    /**
     * The GenreTab2
     *
     * This is the second tab for genres. It is a fragment containing the next 6
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

    public GenreTab2() {
        // constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref2, container, false);

        // setting up the viewmodel which allows each fragment communicate with each other
        model = ViewModelProviders.of(this).get(GenreViewModel.class);

        // setting up genre buttons
        Button mystery = (Button) v.findViewById(R.id.mysteryButton);
        Button scifi = (Button) v.findViewById(R.id.scifiButton);
        Button western = (Button) v.findViewById(R.id.westernButton);
        Button biography = (Button) v.findViewById(R.id.bioButton);
        Button hisfic = (Button) v.findViewById(R.id.historyButton);
        Button adventure = (Button) v.findViewById(R.id.adventureButton);

        mystery.setOnClickListener(this);
        scifi.setOnClickListener(this);
        western.setOnClickListener(this);
        biography.setOnClickListener(this);
        hisfic.setOnClickListener(this);
        adventure.setOnClickListener(this);
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
            case R.id.mysteryButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected

                if (preferenceList.get(6) == 0 && selected < 3) {
                    //genreList.add("Mystery");
                    addGenre(6, "Picked mystery!", "Mystery");
                    // update preference list
                    //preferenceList.set(0,1);
                    //Toast.makeText(this.getContext(), "Picked comedy!", Toast.LENGTH_SHORT).show();
                }
                else if (preferenceList.get(6) == 1){
                    //genreList.remove("Mystery");
                    removeGenre(6,"Mystery unselected!", "Mystery");
                    // update preference list
                    //preferenceList.set(0,0);
                    //Toast.makeText(this.getContext(),"Comedy unselected!",Toast.LENGTH_SHORT).show();
                }

                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                 }

                break;
            case R.id.scifiButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(7) == 0 && selected < 3) {
                    //genreList.add("Scifi");
                    addGenre(7,"Picked science fiction!", "Science-Fiction");
                }
                else if (preferenceList.get(7) == 1) {
                    //genreList.remove("Scifi");
                    removeGenre(7, "Science Fiction unselected!", "Science-Fiction");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.westernButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(8) == 0 && selected < 3) {
                    //genreList.add("Western");
                    addGenre(8,"Picked western!", "Western");
                }
                else if (preferenceList.get(8) == 1) {
                    //genreList.remove("Western");
                    removeGenre(8, "Western unselected!", "Western");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bioButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(9) == 0 && selected < 3) {
                    //genreList.add("Biography");
                    addGenre(9,"Picked biography!", "Biography");
                }
                else if (preferenceList.get(9) == 1) {
                    //genreList.remove("Western");
                    removeGenre(9, "Biography unselected!", "Biography");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.historyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(10) == 0 && selected < 3) {
                    //genreList.add("History");
                    addGenre(10,"Picked historical fiction!", "Historical-Fiction");
                }
                else if (preferenceList.get(10) == 1) {
                    //genreList.remove("History");
                    removeGenre(10, "Historical Fiction unselected!", "Historical-Fiction");
                }
                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.adventureButton:
                //startActivity(new Intent(getActivity().getBaseContext(), LoginActivity.class));
                if (preferenceList.get(11) == 0 && selected < 3) {
                    //genreList.add("Adventure");
                    addGenre(11,"Picked adventure!", "Adventure");
                }
                else if (preferenceList.get(11) == 1) {
                    //genreList.remove("Adventure");
                    removeGenre(11, "Adventure unselected!", "Adventure");
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
