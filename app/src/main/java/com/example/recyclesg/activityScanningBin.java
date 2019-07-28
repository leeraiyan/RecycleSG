package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class activityScanningBin extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result) {
        openActivityRewards();
        finish();
    }

    public void openActivityRewards() {
        Intent intent = new Intent(this, activityRewards.class);
        startActivity(intent);
    }


    @Override
    protected void onPause(){
        super.onPause();
        ScannerView.stopCamera();
    }


    @Override
    protected void onResume(){
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
