package com.example.cmput301w19t18.rent_a_book;

//import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

    private int[] preferenceList = new int [18];
    private int selected = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref, container, false);

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
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.comedyButton:
                // check to see if this button is already selected and that less than 3 genres have been selected
                if (preferenceList[0] == 0 && selected < 4) {
                    //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                    // comedyHL.setBackgroundResource(R.drawable.circle_button_highlighted);
                    preferenceList[0] = 1;
                    selected++;
                    }
                else {
                    //comedyHL.setBackgroundColor(getResources().getColor(R.color.white));
                    preferenceList[0] = 0;
                    selected--;
                }
                break;
            case R.id.dramaButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.romanceButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                    break;
            case R.id.comicsButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.fantasyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.horrorButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
        }
    }
}
