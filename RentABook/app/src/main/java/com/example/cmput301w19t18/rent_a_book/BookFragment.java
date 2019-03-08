package com.example.cmput301w19t18.rent_a_book;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.List;

public class BookFragment extends ListFragment {
    int num;
    static BookFragment newInstance(int n) {
        BookFragment b = new BookFragment();

        // Supplying n input as an argument
        Bundle args = new Bundle();
        args.putInt("num", n);
        b.setArguments(args);

        return b;
    }


    public BookFragment() {

    }

}
