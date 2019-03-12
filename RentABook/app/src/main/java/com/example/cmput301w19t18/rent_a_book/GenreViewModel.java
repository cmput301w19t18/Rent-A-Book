package com.example.cmput301w19t18.rent_a_book;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.logging.Handler;

public class GenreViewModel extends ViewModel {
    // list of genres
    private int[] genres = new int[18];
    //private MutableLiveData<List<Integer>> genres;gm

    // number of genres selected (must not exceed 3)
    //private MutableLiveData<Integer> numSelected;
    private int numSelected = 0;

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public int getNumSelected() {
        return numSelected;
    }

    public void setNumSelected(int numSelected) {
        this.numSelected = numSelected;
    }


/*
    LiveData<List<Integer>> getGenreList() {
        if (genres == null) {
            genres = new MutableLiveData<>();
            loadGenres();
        }
        return genres;
    }

    private void loadGenres() {

    }
    */
}
