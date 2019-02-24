package com.example.cmput301w19t18.rent_a_book;

import android.util.Patterns;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by ikramshire on 2019-02-22.
 */

public class RegistrationTest {
    @Test
    public  void signUpTest(){
        RegisterActivity r = new RegisterActivity();
        String user_email = "hello@gmail.com";
        String pass = "hello1";
        assertTrue(!user_email.isEmpty());
        assertTrue(!pass.isEmpty());








    }
}
