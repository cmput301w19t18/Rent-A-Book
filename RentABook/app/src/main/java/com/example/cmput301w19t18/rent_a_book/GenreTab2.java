package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref2, container, false);

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
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.mysteryButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
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
}
