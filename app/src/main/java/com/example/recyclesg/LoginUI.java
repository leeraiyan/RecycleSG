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

public class LoginUI extends AppCompatActivity {

    private EditText mEmail_editTxt;
    private EditText mPassword_editTxt;

    private Button mSignIn_btn;
    private Button mRegister_btn;
//    private Button mBack_btn;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui1);
        mAuth = FirebaseAuth.getInstance();

        mEmail_editTxt = (EditText) findViewById(R.id.email_editText);
        mPassword_editTxt = (EditText) findViewById((R.id.password_editText));

        mSignIn_btn = (Button) findViewById(R.id.signin_btn);
        mRegister_btn = (Button) findViewById((R.id.register_btn));
//        mBack_btn = (Button) findViewById(R.id.back_btn);

        mProgress_bar = (ProgressBar) findViewById(R.id.loading_progressBar);

        mSignIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(mEmail_editTxt.getText().toString(),
                        mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginUI.this,"User Signed In",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginUI.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        MainActivity.Amir.setVisibility(View.VISIBLE);
                        finish(); return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(LoginUI.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUI.this, RegisterActivity.class);
                startActivity(intent);
//                if(isEmpty()) return;
//                inProgress(true);
//                mAuth.createUserWithEmailAndPassword(mEmail_editTxt.getText().toString(),
//                        mPassword_editTxt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        Toast.makeText(LoginUI.this,"User Registered Successfully!",
//                                Toast.LENGTH_LONG).show();
//                        inProgress(false);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        inProgress(false);
//                        Toast.makeText(LoginUI.this, "Registration failed!", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

//        mBack_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish(); return;
//            }
//        });

    }


    private void inProgress(boolean x){
        if(x){
            mProgress_bar.setVisibility(View.VISIBLE);
//            mBack_btn.setEnabled(false);
            mSignIn_btn.setEnabled(false);
            mRegister_btn.setEnabled(false);
        }

        else{
            mProgress_bar.setVisibility(View.GONE);
//            mBack_btn.setEnabled(true);
            mSignIn_btn.setEnabled(true);
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
}

