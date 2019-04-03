package com.example.cmput301w19t18.rent_a_book;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by ikramshire on 2019-04-01.
 */
public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> mReg = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);
    private Solo solo;
    private  RegisterActivity registerActivity = null;
    RegisterActivity reg = null;
    @Before
    public void setUp() throws Exception {
        registerActivity = mReg.getActivity();
        reg = null;
    }
    @Test
    public void TestReg(){
        View signup = registerActivity.findViewById(R.id.textsignup);
        assertNotNull(signup);
        //onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.fname)).perform(typeText("jake"));
        onView(withId(R.id.last)).perform(typeText("hello"));
        onView(withId(R.id.phone)).perform(typeText("7807807800"));
        onView(withId(R.id.pass)).perform(typeText("123456"));
        onView(withId(R.id.next)).perform(click());

    }

    @After
    public void tearDown() throws Exception {
    }

}