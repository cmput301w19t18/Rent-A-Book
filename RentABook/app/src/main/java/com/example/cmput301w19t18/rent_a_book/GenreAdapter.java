package com.example.cmput301w19t18.rent_a_book;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GenreAdapter extends FragmentStatePagerAdapter {
    /**
     * The GenreAdapter
     *
     * The GenreAdapter is a type of FragmentStatePagerAdapter that
     * handles the fragments that make up genre selection
     *
     * author: Julieta Dikova
     *
     * sources:
     * https://medium.com/@droidbyme/android-material-design-tabs-tab-layout-with-swipe-884085ae80ff
     *
     */


    private final List<Fragment> fList = new ArrayList<>();
    private final List<String> ftList = new ArrayList<>();
    private Context context;

    public GenreAdapter(FragmentManager fm) {
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int pos) {
        return fList.get(pos);
    }

    public void addFragment(Fragment fragment, String title) {
        fList.add(fragment);
        ftList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int pos) {
        return null;
    }

    @Override
    public int getCount() {
        // number of swipeable screens
        return fList.size();
    }

}
