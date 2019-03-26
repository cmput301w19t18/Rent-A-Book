package com.example.cmput301w19t18.rent_a_book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SaveInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
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

*/
}
