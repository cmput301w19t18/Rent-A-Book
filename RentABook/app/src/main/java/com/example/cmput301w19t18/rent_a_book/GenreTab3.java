package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GenreTab3 extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pick_genre_pref3, container, false);

        Button nonfic = (Button) v.findViewById(R.id.nonficButton);
        Button ya = (Button) v.findViewById(R.id.yaButton);
        Button thriller = (Button) v.findViewById(R.id.thrillerButton);
        Button tragedy = (Button) v.findViewById(R.id.tragedyButton);
        Button poetry = (Button) v.findViewById(R.id.poetryButton);
        Button children = (Button) v.findViewById(R.id.childButton);

        nonfic.setOnClickListener(this);
        ya.setOnClickListener(this);
        thriller.setOnClickListener(this);
        tragedy.setOnClickListener(this);
        poetry.setOnClickListener(this);
        children.setOnClickListener(this);
        return v;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // change to put info into intents to send to main activity and other fragments
            case R.id.nonficButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.yaButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.thrillerButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.tragedyButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.poetryButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
            case R.id.childButton:
                //startActivity(new Intent(getActivity().getBaseContext(), MainActivity.class));
                break;
        }
    }
}
