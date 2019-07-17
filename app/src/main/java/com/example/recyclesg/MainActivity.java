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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button buttonVisit;
    private Button buttonLog;
    public static TextView resultTextView;
    private static final int REQUEST_CAMERA = 1;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().getRef();
    int firebaseMemberCount = 0;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted! Tap again to Recycle", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)  findViewById(R.id.button);
        buttonVisit = (Button) findViewById(R.id.buttonVisit);
        buttonLog = (Button) findViewById(R.id.buttonLog);
//        Book book1 = new Book ("The Suite Life", "Lee Raiyan", 730798, "Fiction");
//        Book book2 = new Book("Emperor's New Groove", "Roberto Carlos", 1997, "Non-Fiction");
        RecyclableItem pepsitin = new RecyclableItem("Pepsi","Aluminium","9556404001033",320,11,false);
        RecyclableItem coffeetin = new RecyclableItem("Nescafe Original","Aluminium","9556001047175",240,11,false);



        //If the permission is not  already granted then ask,if it is already granted then just do nothing for the permission section
        if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            //After this function call,it will ask for permission and whether it granted or not,this response is handle in onRequestPermissionsResult() which we overrided.
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
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

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference postsRef = ref.child("itemDatabase");
                DatabaseReference newPostRef = postsRef.push();

                if(firebaseMemberCount == 0){
                    newPostRef.setValue((pepsitin));
                    firebaseMemberCount++;
                }
                else{
                    newPostRef = postsRef.push();
                    newPostRef.setValue(coffeetin);
                }
            }
        });


        resultTextView = (TextView)findViewById(R.id.textView);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, activityScanningPage.class);
        startActivity(intent);
    }

    public void openAREnabled(){
        Intent intent = new Intent(this, ARenabled2.class);
        startActivity(intent);
    }

}
