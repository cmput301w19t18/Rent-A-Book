package com.example.cmput301w19t18.rent_a_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import android.support.test.rule.ActivityTestRule;

public class BookDetailsTest {

    @Rule
    public ActivityTestRule<BookDetails> mActivityTestRule = new ActivityTestRule<BookDetails>(BookDetails.class);

    private BookDetails bookDetails = null;


    @Before
    public void setUp() throws Exception {
        bookDetails = mActivityTestRule.getActivity();
    }

    

    @After
    public void tearDown() throws Exception{

    }
}