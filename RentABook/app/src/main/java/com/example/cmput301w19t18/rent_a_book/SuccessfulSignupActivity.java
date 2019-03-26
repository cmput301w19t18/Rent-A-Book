package com.example.cmput301w19t18.rent_a_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SuccessfulSignupActivity extends AppCompatActivity {

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String phone;
    private String genreList;
    private String genreListStr;

    private Button genre1;
    private Button genre2;
    private Button genre3;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sucess_signup);

        TextView viewName = (TextView) findViewById(R.id.inputName);
        TextView viewPhone = (TextView) findViewById(R.id.inputPhone);
        TextView viewEmail = (TextView) findViewById(R.id.inputEmail);
        TextView proceed = (TextView) findViewById(R.id.proceed);

        genre1 = (Button) findViewById(R.id.genre1);
        genre2 = (Button) findViewById(R.id.genre2);
        genre3 = (Button) findViewById(R.id.genre3);

        // unpack
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                email = bundle.getString("email");
                password = bundle.getString("password");
                fName = bundle.getString("firstName");
                lName = bundle.getString("lastName");
                genreList = bundle.getString("genres");
                phone = bundle.getString("phoneNum");
                genreListStr = bundle.getString("genresString");
            }
        }
        else {
            email = (String) savedInstanceState.getSerializable("email");
            password = (String) savedInstanceState.getSerializable("password");
            fName = (String) savedInstanceState.getSerializable("firstName");
            lName = (String) savedInstanceState.getSerializable("lastName");
            genreList = (String) savedInstanceState.getSerializable("genres");
            phone = (String) savedInstanceState.getSerializable("phoneNum");
            genreListStr =(String) savedInstanceState.getSerializable("genresString");
        }

        String name = fName + " " + lName;
        viewName.setText(name);
        viewPhone.setText(phone);
        viewEmail.setText(email);


        String selGenres[] = genreListStr.split(" ");

        setButtonAttr(selGenres[0], genre1);
        setButtonAttr(selGenres[1], genre2);
        setButtonAttr(selGenres[2], genre3);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // try to sign in here (keep user signed in)
                Intent intent = new Intent(SuccessfulSignupActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }

    public void setButtonAttr(String s, Button b) {
        b.setText(s);
        if (s.equals("Comedy")) {
            b.setBackgroundResource(R.drawable.circle_button);
        }
        else if (s.equals("Drama")) {
            b.setBackgroundResource(R.drawable.tan_spice_circle);
        }
        else if (s.equals("Romance")) {
            b.setBackgroundResource(R.drawable.light_olive_circle);
        }
        else if (s.equals("Comics")) {
            b.setBackgroundResource(R.drawable.medium_orange_spice);
        }
        else if (s.equals("Fantasy")) {
            b.setBackgroundResource(R.drawable.purple_slate_circle);
        }
        else if (s.equals("Horror")) {
            b.setBackgroundResource(R.drawable.light_gray_circle);
        }
        else if (s.equals("Mystery")) {
            b.setBackgroundResource(R.drawable.circle_button);
        }
        else if (s.equals("Science-Fiction")) {
            b.setText("Scifi");
            b.setBackgroundResource(R.drawable.tan_spice_circle);
        }
        else if (s.equals("Western")) {
            b.setBackgroundResource(R.drawable.light_olive_circle);
        }
        else if (s.equals("Biography")) {
            b.setBackgroundResource(R.drawable.medium_orange_spice);
        }
        else if (s.equals("Historical-Fiction")) {
            b.setText("Hisfic");
            b.setBackgroundResource(R.drawable.purple_slate_circle);
        }
        else if (s.equals("Adventure")) {
            b.setBackgroundResource(R.drawable.light_gray_circle);
        }
        else if (s.equals("Non-Fiction")) {
            b.setText("Nonfic");
            b.setBackgroundResource(R.drawable.circle_button);
        }
        else if (s.equals("Young-Adult")) {
            b.setText("YA");
            b.setBackgroundResource(R.drawable.tan_spice_circle);
        }
        else if (s.equals("Thriller")) {
            b.setBackgroundResource(R.drawable.light_olive_circle);
        }
        else if (s.equals("Tragedy")) {
            b.setBackgroundResource(R.drawable.medium_orange_spice);
        }
        else if (s.equals("Poetry")) {
            b.setBackgroundResource(R.drawable.purple_slate_circle);
        }
        else if (s.equals("Children's")) {
            b.setBackgroundResource(R.drawable.light_gray_circle);
        }
    }

    /*
    @Override
    public void onClick(View view) {
        // signin new user and go to home page

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(SuccessfulSignupActivity.this, HomeActivity.class);
                    intent.putExtras(new Bundle());
                    Bundle bundle = intent.getExtras();
                    bundle.putString("user_email", email);

                    intent.putExtras(bundle);
                    //CLOSE ALL OPEN ACTIVITES.
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = new Intent(SuccessfulSignupActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    */


}
