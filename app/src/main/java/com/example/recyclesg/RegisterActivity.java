package com.example.recyclesg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail_editTxt;
    private EditText mPassword_editTxt;
    private EditText mUsername_editTxt;

    private Button mSignIn_btn;
    private Button mRegister_btn;
    private Button mBack_btn;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        mEmail_editTxt = (EditText) findViewById(R.id.email_editTextr);
        mPassword_editTxt = (EditText) findViewById((R.id.password_editTextr));
        mUsername_editTxt = (EditText) findViewById(R.id.user_editTextr) ;

        mRegister_btn = (Button) findViewById((R.id.register_btnr));
        mBack_btn = (Button) findViewById(R.id.back_btnr);

        mProgress_bar = (ProgressBar) findViewById(R.id.loading_progressBar);

        db = FirebaseFirestore.getInstance();

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(mEmail_editTxt.getText().toString(),
                        mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this,"User Registered Successfully!",
                                Toast.LENGTH_LONG).show();
                        inProgress(false);
//                        Intent intent = new Intent(RegisterActivity.this, LoginUI.class);
//                        startActivity(intent);
//                        finish(); return;

                        createUser();
                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    private void inProgress(boolean x){
        if(x){
            mProgress_bar.setVisibility(View.VISIBLE);
            mBack_btn.setEnabled(false);
//            mSignIn_btn.setEnabled(false);
            mRegister_btn.setEnabled(false);
        }

        else{
            mProgress_bar.setVisibility(View.GONE);
            mBack_btn.setEnabled(true);
//            mSignIn_btn.setEnabled(true);
            mRegister_btn.setEnabled(true);
        }
    }
    private boolean isEmpty(){
        if(TextUtils.isEmpty(mEmail_editTxt.getText().toString())){
            mEmail_editTxt.setError("REQUIRED!");
            return true;
        }


        if(TextUtils.isEmpty(mPassword_editTxt.getText().toString())){
            mPassword_editTxt.setError("REQUIRED!");
            return true;
        }

        return false;
    }
    private void createUser(){



        String emailString = mEmail_editTxt.getText().toString();
        String usernameString = mUsername_editTxt.getText().toString();
        int pointsInt = 0;
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", emailString);
        userData.put("username", usernameString);
        userData.put("points", pointsInt);

        db.collection("UserData").document(emailString)
                .set(userData);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(usernameString).build();

        user.updateProfile(profileUpdates);
    }
}
