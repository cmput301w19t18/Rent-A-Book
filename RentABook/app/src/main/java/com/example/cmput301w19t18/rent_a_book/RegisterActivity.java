package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * The type Register activity.
 * Allows a user to register a new profile to the firebase database
 * User information can be edited later
 */
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
    private ImageView profilepic;
    private Uri download_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        next = (Button) findViewById(R.id.next);
        addPhoto = (Button) findViewById(R.id.AddPhotoButton);
        cancel = (Button) findViewById(R.id.cancel);
        pass = (EditText) findViewById(R.id.pass);
        et_email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        fname = (EditText) findViewById(R.id.fname);
        last = (EditText) findViewById(R.id.last);
        mFireBaseD = FirebaseDatabase.getInstance();
        profilepic = (ImageView) findViewById(R.id.viewPhoto);
        //DataR = mFireBaseD.getReference();

        next.setOnClickListener(this);
        cancel.setOnClickListener(this);
        if (mAuth.getCurrentUser() != null ){
            //user is already logged in

        }

        //filePath = Uri.parse("R.drawable.default_profile_pic_olive");
        filePath = Uri.parse("https://firebasestorage.googleapis.com/v0/b/rent-a-read.appspot.com/o/images%2Fdefault_pp.png?alt=media&token=73e1ec93-1052-4467-ba6f-9aaf5778e4fb");
        Picasso.get().load(filePath).into(profilepic);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
        //Toast.makeText(this,filePath.toString(), Toast.LENGTH_SHORT).show();
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
                download_uri = data.getParcelableExtra("download_uri");
//                Toast.makeText(this,"REGISTER_ACTIVITY_TOAST: " + download_uri.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,download_url.toString(), Toast.LENGTH_SHORT).show();
                if(filePath != null) {
                    //Toast.makeText(getApplicationContext(), "Uri: " + download_url, Toast.LENGTH_SHORT).show();
                    //Picasso.get().load(download_url).into(profilepic);
                    Toast.makeText(this,filePath.toString(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(filePath).into(profilepic);
                } else {
                    filePath = Uri.parse("android.resource://com.example.cmput301w19t18.rent_a_book/drawable/default_profile_pic_olive");
                    //filePath = Uri.parse("R.drawable.default_profile_pic_olive");
                    //Picasso.get().load(filePath).into(profilepic);
                    //Toast.makeText(this,download_uri.toString(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(download_uri).into(profilepic);
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
/*
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
*/

    /**
     * On next.
     */
//signs the user up based on their info
    public void onNext(){
        final String user_email = et_email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String first_name = fname.getText().toString().trim();
        String last_name = last.getText().toString().trim();
        String phone_num = phone.getText().toString().trim();
        //String profile_uri = download_uri;

        // check for first name
        if (first_name.isEmpty()) {
            fname.setError("First name is required");
            fname.requestFocus();
            return;
        }

        // check for last name
        if (last_name.isEmpty()) {
            last.setError("Last name is required");
            last.requestFocus();
            return;
        }

        // check if its correct phone number
        if (!PhoneNumberUtils.isGlobalPhoneNumber(phone_num) || phone_num.length() < 7) {
            phone.setError("Please enter a valid phone number");
            phone.requestFocus();
            return;
        }


        //checks if user email and password is empty and makes sure they are not.
        if (user_email.isEmpty()){
            et_email.setError("Email is required");
            et_email.requestFocus();
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

        // pack all info into an intent
        Intent intent = new Intent(this, PickGenrePreference.class);
        Bundle userInfo =  new Bundle();
        userInfo.putString("firstName", first_name);
        userInfo.putString("lastName", last_name);
        userInfo.putString("phoneNum", phone_num);
        userInfo.putString("email",user_email);
        userInfo.putString("password", password);
        userInfo.putString("profileURI", download_uri.toString());

        intent.putExtras(userInfo);

        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        //checks what button the user clicked
        if (view == next){

            onNext();
        }

        // TODO cancel should clear everything
        if (view == cancel){
            startActivity(new Intent(this, LoginActivity.class));

        }

    }
}
