package com.example.cmput301w19t18.rent_a_book;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class OldGenreViewModel extends ViewModel {
    // list of genres
    //private int[] genres = new int[18];
    private MutableLiveData<Integer> numSelected = new MutableLiveData<Integer>();

    private MutableLiveData<List<Integer>> genres;

    private MutableLiveData<List<String>> currPickedGenres;


    public LiveData<List<Integer>> getGenres() {
        if (genres == null) {
            genres = new MutableLiveData<List<Integer>>();
            loadGenresSelected();
        }
        return genres;
    }
    //private MutableLiveData<List<String>> currSelectedGenres = new MutableLiveData<>();

    // number of genres selected (must not exceed 3)
    //private int numSelected = 0;

    //private MutableLiveData currPickedGenres = new MutableLiveData();
    //private List<String> currPickedGenres = new ArrayList<String>();

    public LiveData<List<String>> setCurrPickedGenres() {
        return currPickedGenres;
    }

    public LiveData<List<String>> getCurrPickedGenres() {
        if (currPickedGenres == null) {
            currPickedGenres = new MutableLiveData<List<String>>();
            loadCurrPickedGenres();
        }
        return currPickedGenres;
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

    public LiveData<List<String>> setCurrPickedGenres(List<String> s) {
        // add string that says genre to currPickedGenres
        currPickedGenres.setValue(s);
        return currPickedGenres;
    }




    private void loadGenresSelected() {
        List<Integer> selectedGenres = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            selectedGenres.add(0);
        }
        genres.setValue(selectedGenres);
    }

    // TODO
    // use this ^^ to set the genres. check if it is set to 1 or not and proceed
    // then in pickgenrepreference get the indexes of this set to one and print out
    // the appropriate string LIVE

    public LiveData<Integer> getNumSelected() {
        return numSelected;
    }

    public void setNumSelected(Integer i) {
        numSelected.setValue(i);
    }


    /*
    public int[] getGenres() {
        return genres;
    }

    //public void setGenres(int[] genres) {
    //    this.genres = genres;
    //}

    public void setGenres(int pos, int genre) {
        this.genres[pos] = genre;
    }


    public int getNumSelected() {
        return numSelected;
    }

    public void setNumSelected(int numSelected) {
        this.numSelected = numSelected;
    }

*/
/*
    public List<String> getCurrPickedGenres() {
        return currPickedGenres;
    }


    public void setCurrPickedGenres(String pickedGenre) {
        this.currPickedGenres.add(pickedGenre);
    }

    public void removeCurrPickedGenres(String pickedGenre) {
        this.currPickedGenres.remove(pickedGenre);
    }

*/


/*
    public void setGenres(Integer pos, Integer genrePicked) {
        genres[pos].setValue(genrePicked);
    }
*/

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
