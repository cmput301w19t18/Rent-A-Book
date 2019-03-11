package com.example.cmput301w19t18.rent_a_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by ikramshire on 2019-03-10.
 */
public class NewBookActivityTest {
    @Rule
    public ActivityTestRule<NewBookActivity> mActivityTestRule = new  ActivityTestRule<NewBookActivity>(NewBookActivity.class);
    private NewBookActivity newActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ProfileActivity.class.getName(), null, false);


    @Before
    public void setUp() throws Exception {
        newActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void TestSubmit (){
        assertNotNull(newActivity.findViewById(R.id.SubmitButton));
        onView(withId(R.id.SubmitButton)).perform(click());
        Activity pActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(pActivity);
        pActivity.finish();



;    }

    @After
    public void tearDown() throws Exception {
        newActivity = null;
    }


}