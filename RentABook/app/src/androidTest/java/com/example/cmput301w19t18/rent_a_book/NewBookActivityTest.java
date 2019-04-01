package com.example.cmput301w19t18.rent_a_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.robotium.solo.Solo;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;


//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Created by ikramshire on 2019-04-01.
 */
public class NewBookActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new  ActivityTestRule<HomeActivity>(HomeActivity.class);
    private HomeActivity newActivity = null;
    private Solo newsolo;
    //Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        newsolo  = new Solo(getInstrumentation(), mActivityTestRule.getActivity());
        //newActivity = null;
    }
    @Test
    public void TestSubmit (){
        //assertNotNull(newActivity.findViewById(R.id.SubmitButton));
        //onView(withId(R.id.SubmitButton)).perform(click());
        //Activity pActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        //assertNotNull(pActivity);
        //pActivity.finish();
        newsolo.assertCurrentActivity("WrongActivity", HomeActivity.class);
        newsolo.clickOnView(newsolo.getView(R.id.addEntry_fab));
        newsolo.waitForActivity(NewBookActivity.class);
        //View v = newsolo.getView(R.id.);

    }

    @After
    public void tearDown() throws Exception {
        newActivity = null;
    }

}
