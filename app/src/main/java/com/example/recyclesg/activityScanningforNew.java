package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class activityScanningforNew extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result) {
        NewNoteActivity.editTextTitle.setText(result.getText(), TextView.BufferType.EDITABLE);
        onBackPressed();
    }

    public void openItemTally1() {
        Intent intent = new Intent(this, itemTallyActivity1.class);
        startActivity(intent);
    }

    public void openBookListActivity(){
        Intent intent = new Intent(this, ShoppingCart.class);
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
