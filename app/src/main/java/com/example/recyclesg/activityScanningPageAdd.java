package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class activityScanningPageAdd extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
        Toast.makeText(this, "Scan the Barcode on your item", Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleResult(Result result) {
//        MainActivity.resultTextView.setText(result.getText());
        if (ShoppingCart.addingCounter == 100){
            Toast.makeText(this, "Item not found in Database", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(activityScanningPageAdd.this, NewNoteActivity.class));
            finish();
        }

        else{
//            CollectionReference notebookRef = FirebaseFirestore.getInstance()
//                    .collection("Notebook");

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            CollectionReference notebookRef = FirebaseFirestore.getInstance()
                    .collection("UserData/user.getEmail()");
            notebookRef.add(new Note("Ice Mountain", "Plastic", 600));
            Toast.makeText(this, "Added to Cart!", Toast.LENGTH_SHORT).show();
            ShoppingCart.addingCounter++;
            onBackPressed();
        }
//        openActivityRewards();
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
