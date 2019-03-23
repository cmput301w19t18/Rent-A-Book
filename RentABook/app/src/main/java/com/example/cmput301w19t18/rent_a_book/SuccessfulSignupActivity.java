package com.example.cmput301w19t18.rent_a_book;

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


public class SuccessfulSignupActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFireBaseD;
    private DatabaseReference DataR;

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

        viewName.setText(fName + " " + lName);
        viewPhone.setText(phone);
        viewEmail.setText(email);


        String selGenres[] = genreListStr.split(" ");

        genre1.setText(selGenres[0]);
        genre2.setText(selGenres[1]);
        genre3.setText(selGenres[2]);

        /*
        // send info to firebase
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(email, genreList, fName, lName);
                    String user_id = mAuth.getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SuccessfulSignupActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    Toast.makeText(getApplicationContext(),"User Registered!",Toast.LENGTH_SHORT).show();

                    finish();
                }
                //check if email is already registered
                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(),"User Already Registered!",Toast.LENGTH_SHORT).show();

                }

            }
        });
        */

       // get and display from firebase
    }

    public void setButtonAttr(String s, Button b) {
        if (s == "Comedy") {
            b.setText(s);
            b.setBackgroundColor(getResources().getColor(R.color.lightOrangeSpice));
        }
        else if (s == "Drama") {
            b.setText(s);
            b.setBackgroundColor(getResources().getColor(R.color.tanSpice));
        }
        else if (s == "Romance") {

        }
        else if (s == "Comics") {

        }
        else if (s == "Fantasy") {

        }
        else if (s == "Horror") {

        }
        else if (s == "Mystery") {

        }
        else if (s == "Science Fiction") {

        }
        else if (s == "Western") {

        }
        else if (s == "Biography") {

        }
        else if (s == "Historical Fiction") {

        }
        else if (s == "Adventure") {

        }
        else if (s == "Non-Fiction") {

        }
        else if (s == "Young Adult") {

        }
        else if (s == "Thriller") {

        }
        else if (s == "Tragedy") {

        }
        else if (s == "Poetry") {

        }
        else if (s == "Children's") {

        }
    }

    @Override
    public void onClick(View view) {
        // go to home page
    }

}
