package com.example.cmput301w19t18.rent_a_book;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;

public class GenreViewModel extends ViewModel {
    // list of genres
    //private int[] genres = new int[18];
    private static int count = 0;
    private MutableLiveData numSelected;

    private static List<Integer> genresSelected = new ArrayList<Integer>(Collections.nCopies(18,0));;
    private MutableLiveData<List<Integer>> genres;

    private MutableLiveData<List<String>> currPickedGenres;

    private static List<String> pickedGenres = new ArrayList<String>();

    public void setCount(int i) {
        this.count = i;
    }

    public int getCount() {
        return count;
    }


    public LiveData<Integer> getNumSelected() {
        if (numSelected == null) {
            numSelected = new MutableLiveData<>();
        }
        return numSelected;
    }

    // might not need
    public void setNumSelected(Integer i) {
        numSelected.setValue(i);
    }

    public void incrementNumSelected() {
        count++;
        numSelected.setValue(count);
    }

    public void decrementNumSelected() {
        count--;
        numSelected.setValue(count);
    }

    // might not need
    public void setGenresSelected(List<Integer> genresSelected) {
        this.genresSelected = genresSelected;
        /*
        for (int i = 0; i < 18; i++) {
            if (i == 0 && genresSelected.get(i) == 1) {
                pickedGenres.add("Comedy");
            }
            else if (i == 1 && genresSelected.get(i) == 1) {
                pickedGenres.add("Drama");
            }
            else if (i == 2 && genresSelected.get(i) == 1) {
                pickedGenres.add("Romance");
            }
            else if (i == 3 && genresSelected.get(i) == 1) {
                pickedGenres.add("Comics");
            }
            else if (i == 4 && genresSelected.get(i) == 1) {
                pickedGenres.add("Fantasy");
            }
            else if (i == 5 && genresSelected.get(i) == 1) {
                pickedGenres.add("Horror");
            }
        }
        */
    }

    public List<Integer> getGenresSelected() {
        return genresSelected;
    }

    public MutableLiveData<List<Integer>> getGenres() {
        if (genres == null) {
            genres = new MutableLiveData<List<Integer>>();
        }
        return genres;
    }

    public void setGenres(List<Integer> gS) {
        genresSelected = gS;
        genres.setValue(genresSelected);
    }


    public List<String> getPickedGenres() {
        return pickedGenres;
    }

    public void setPickedGenres(List<String> pickedGenres) {
        this.pickedGenres = pickedGenres;
    }




    public void setCurrPickedGenres(List<String> s) {
        pickedGenres = s;
        currPickedGenres.setValue(pickedGenres);
    }

    public MutableLiveData<List<String>> getCurrPickedGenres() {
        if (currPickedGenres == null) {
            currPickedGenres = new MutableLiveData<List<String>>();
            loadCurrPickedGenres();
        }
        return currPickedGenres;
    }



    public void loadGenresSelected() {
        for (int i = 0; i < 18; i++) {
            genresSelected.add(0);
        }

    }



    // use this to print out the string. they match to the index of genres
    private void loadCurrPickedGenres() {
            List<String> pickedGenres = new ArrayList<>();
            /*
            pickedGenres.add("Comedy");
            pickedGenres.add("Drama");
            pickedGenres.add("Romance");
            pickedGenres.add("Comics");
            pickedGenres.add("Fantasy");
            pickedGenres.add("Horror");
            pickedGenres.add("Mystery");
            pickedGenres.add("Scifi");
            pickedGenres.add("Western");
            pickedGenres.add("Biography");
            pickedGenres.add("Historic Fiction");
            pickedGenres.add("Adventure");
            pickedGenres.add("Nonfiction");
            pickedGenres.add("YA");
            pickedGenres.add("Thriller");
            pickedGenres.add("Poetry");
            pickedGenres.add("Children's");
            */
            currPickedGenres.setValue(pickedGenres);
    }


}
