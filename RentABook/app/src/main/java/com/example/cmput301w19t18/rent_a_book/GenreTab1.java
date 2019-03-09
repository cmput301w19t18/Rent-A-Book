package com.example.cmput301w19t18.rent_a_book;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GenreTab1 extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref, container, false);

        Button comedy = (Button) v.findViewById(R.id.comedyButton);
        Button drama = (Button) v.findViewById(R.id.dramaButton);
        Button romance = (Button) v.findViewById(R.id.romanceButton);
        Button comic = (Button) v.findViewById(R.id.comicsButton);
        Button fantasy = (Button) v.findViewById(R.id.fantasyButton);
        Button horror = (Button) v.findViewById(R.id.horrorButton);

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
            case R.id.comedyButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.dramaButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.romanceButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                    break;
            case R.id.comicsButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.fantasyButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.horrorButton:
                startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
        }
    }
}
