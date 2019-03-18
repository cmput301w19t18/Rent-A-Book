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
import android.widget.Toast;

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
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.mysteryButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                // check to see if this button is already selected and that less than 3 genres have been selected

                if (preferenceList.get(6) == 0 && selected < 3) {
                    addGenre(6, "Picked mystery!");
                    // update preference list
                    //preferenceList.set(0,1);
                    //Toast.makeText(this.getContext(), "Picked comedy!", Toast.LENGTH_SHORT).show();
                }
                else if (preferenceList.get(6) == 1){
                    removeGenre(6,"Mystery unselected!");
                    // update preference list
                    //preferenceList.set(0,0);
                    //Toast.makeText(this.getContext(),"Comedy unselected!",Toast.LENGTH_SHORT).show();
                }

                else if (selected >= 3) {
                    Toast.makeText(this.getContext(),"Too many genres selected!",Toast.LENGTH_SHORT).show();
                 }

                break;
            case R.id.scifiButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.westernButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.bioButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.historyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.adventureButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
        }
    }
    public void addGenre(int pos, String s) {
        // update preference list
        preferenceList.set(pos,1);
        model.setGenresSelected(preferenceList);
        model.getGenres().postValue(preferenceList);
        model.setCount(selected+1);
        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();

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
        model.getGenres().postValue(preferenceList);
        model.setCount(selected-1);

        Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();
    }
}
