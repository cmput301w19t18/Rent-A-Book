package com.example.cmput301w19t18.rent_a_book;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookDetailsTest {

    Book curr_book = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "1234567891234", "Available", 4, "jakep@nypd.org", "0 0 0 0 0 0 0 0 0 0 0 0 0 0", "testRequester@gmail.com", "Brooklyn Nine-Nine is an American police television sitcom that premiered on Fox on September 17, 2013.");


    @Rule
    public ActivityTestRule<BookDetails> activityTestRule = new ActivityTestRule<BookDetails>(BookDetails.class);
    private BookDetails bookDetails = null;

    @Before
    public void setUp() {
        bookDetails = activityTestRule.getActivity();
    }

    @Test
    public void testHomeDetails(){

        onView(withId(R.id.title_textView)).check(matches(withText("Brooklynn Nine-Nine: Book Edition")));
        onView(withId(R.id.auth_textView)).check(matches(withText("Daniel Goor")));
        onView(withId(R.id.status_textView)).check(matches(withText("Available")));
        onView(withId(R.id.isbn_textView)).check(matches(withText("1234567891234")));
        onView(withId(R.id.desc_textView)).check(matches(withText("Brooklyn Nine-Nine is an American police television sitcom that premiered on Fox on September 17, 2013.")));
        onView(withId(R.id.owner_textView)).check(matches(withText("jakep@nypd.org")));

    }

    @After
    public void tearDown() {
        bookDetails = null;
    }
}
