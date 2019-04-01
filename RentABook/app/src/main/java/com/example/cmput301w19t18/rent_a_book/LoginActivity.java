package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin;

    private EditText pass;

    private  EditText email;
    private TextView txtsignup;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        signin = (Button) findViewById(R.id.signin);
        pass = (EditText) findViewById(R.id.pass);
        email = (EditText) findViewById(R.id.email);

        txtsignup = (TextView) findViewById(R.id.textsignup);
        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(this);
        txtsignup.setOnClickListener(this);

    }

    private void signIn(){

        String user_email = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if (user_email.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            pass.setError("Pass is required");
            pass.requestFocus();
            return;
        }
        //checks if it is a correct email format.
        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;

        }
        //makes sure password is atleast 6 letters long
        if (password.length() < 6) {
            pass.setError("Password must be at least 6 letters long!");
            pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(user_email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtras(new Bundle());
                    Bundle bundle = intent.getExtras();
                    bundle.putString("user_email", email.getText().toString().trim());

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
    }

    @Override
    //tracks which view is clicked ie weather user clicked the textview or the 2 buttons
    public void onClick(View view) {
        if (view == signin){
            signIn();
        }

        if (view == txtsignup){
            //go to register activity
            startActivity( new Intent(this, RegisterActivity.class));
        }

    }
}

