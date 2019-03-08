package com.example.cmput301w19t18.rent_a_book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;



public class BookFragmentAdapter extends FragmentPagerAdapter {
    public BookFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int pos) {
        return BookFragment.newInstance(pos);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
