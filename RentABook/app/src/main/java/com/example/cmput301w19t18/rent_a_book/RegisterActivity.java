package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button next;
    private Button signup;
    private Button addPhoto;
    private Button cancel;
    private EditText pass;
    private EditText fname;
    private EditText last;
    private EditText phone;
    private EditText et_email;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFireBaseD;
    private DatabaseReference DataR;
    private String prefList;
    private Uri filePath;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        next = (Button) findViewById(R.id.next);
        addPhoto = (Button) findViewById(R.id.addPhoto);
        signup = (Button) findViewById(R.id.signup);
        cancel = (Button) findViewById(R.id.cancel);
        pass = (EditText) findViewById(R.id.pass);
        et_email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        fname = (EditText) findViewById(R.id.fname);
        last = (EditText) findViewById(R.id.last);
        mFireBaseD = FirebaseDatabase.getInstance();
        imageview = (ImageView) findViewById(R.id.viewPhoto);
        //DataR = mFireBaseD.getReference();

        next.setOnClickListener(this);
        signup.setOnClickListener(this);
        cancel.setOnClickListener(this);
        if (mAuth.getCurrentUser() != null ){
            //user is already logged in

        }

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
    }

    private void choosePhoto(){

        Intent intent = new Intent(this, addPhotoActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                filePath = data.getParcelableExtra("filepath");

                if(filePath != null) {
                    //Toast.makeText(getApplicationContext(), "Uri: " + filePath.toString(), Toast.LENGTH_SHORT).show();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        Bitmap b = getResizedBitmap(bitmap, 150, 240);
                        imageview.setImageBitmap(b);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    //signs the user up based on their info
    public void signUp(){
        final String user_email = et_email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String first_name = fname.getText().toString().trim();
        String last_name = last.getText().toString().trim();
        String phone_num = phone.getText().toString().trim();
        //checks if user email and password is empty and makes sure they are not.
        if (user_email.isEmpty()){
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            pass.setError("Email is required");
            pass.requestFocus();
            return;
        }
        //checks if it is a correct email format.
        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
            et_email.setError("Please enter a valid email!");
            et_email.requestFocus();
            return;

        }
        //makes sure password is at least 6 letters long
        if (password.length() < 6){
            pass.setError("Password must be at least 6 letters long!");
            pass.requestFocus();
            return;
        }

        //finally register user, got this code from firebase instructions,
        mAuth.createUserWithEmailAndPassword(user_email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(user_email, prefList);
                    String user_id = mAuth.getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    Toast.makeText(getApplicationContext(),"User Registered!",Toast.LENGTH_SHORT).show();

                    finish();
                }
                //check if email is already registered
                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(),"User  Already Registered!",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    @Override
    public void onClick(View view) {
        //checks what button the user clicked
        if (view == next){
            startActivity(new Intent(this, PickGenrePreference.class));
        }

        if (view == signup){
            signUp();

        }
        if (view == cancel){
            startActivity(new Intent(this,MainActivity.class));

        }
    }
}
