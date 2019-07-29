package com.example.recyclesg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button buttonRecycle;
    private Button buttonVisit;
    private Button buttonLogout;
    private Button buttonMap;
    public static TextView resultTextView;
    private static final int REQUEST_CAMERA = 1;

    //auth values
    private FirebaseAuth mAuth;

    //database values
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().getRef();
    int firebaseMemberCount = 0;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted, Happy Recycling!", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuitesting);
        buttonRecycle = (Button)  findViewById(R.id.btn_recycle);
        buttonVisit = (Button) findViewById(R.id.btn_visit);
        buttonLogout = (Button) findViewById(R.id.btn_logout);
        buttonMap = (Button) findViewById(R.id.btn_find);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //If the permission is not  already granted then ask,if it is already granted then just do nothing for the permission section
        if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            //After this function call,it will ask for permission and whether it granted or not,this response is handle in onRequestPermissionsResult() which we overrided.
        }

        buttonRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null){
                    openSignIn();
                }

                else if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    openActivity2();
                }
                else{
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                }
            }
        });

        buttonVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAREnabled();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Signed Out!", Toast.LENGTH_LONG).show();
                openSignIn();
                finish();

            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });




        resultTextView = (TextView)findViewById(R.id.textView);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, ShoppingCart.class);
        startActivity(intent);
    }

    public void openSignIn(){
        Intent intent = new Intent(this, LoginUI.class);
        startActivity(intent);
    }

    public void openAREnabled(){
        Intent intent = new Intent(this, ARenabled2.class);
        startActivity(intent);
    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
