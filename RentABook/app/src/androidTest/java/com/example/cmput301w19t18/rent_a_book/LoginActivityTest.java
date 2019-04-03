package com.example.cmput301w19t18.rent_a_book;

import android.util.Patterns;
import android.widget.EditText;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

public class LoginActivityTest {

    private EditText email;
    private EditText pass;

    @Test
    public void signIn() {
        String user_email = "";
        String password = "testpass123";

        onView(withId(R.id.email)).perform(typeText(user_email));
        onView(withId(R.id.email)).check(matches(withText("")));

        onView(withId(R.id.signin)).perform(click());
        onView(withId(R.id.email)).check(matches(hasErrorText("Email is required")));

        String mevar = "me";
        assertEquals(mevar, "me");

    }

    @Test
    public void signIn2(){
        String user_email = "testuser@gmail.com";
        String password = "";

        if (password.isEmpty()){
            pass.setError("Pass is required");
        }

        assertEquals(pass.getText().toString().trim(), "Pass is required");

    }

    @Test
    public void signIn3(){
        String user_email = "testusernotmatchingpattern";
        String password = "testpass123";

        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
        }

        assertEquals(email.getText().toString().trim(), "Please enter a valid email!");

    }

    @Test
    public void signIn4(){
        String user_email = "testuser@gmail.com";
        String password = "tp123";

        if (password.length() < 6) {
            pass.setError("Password must be at least 6 letters long!");
            pass.requestFocus();
        }

        assertEquals(pass.getText().toString().trim(), "Password must be at least 6 letters long!");
    }







}