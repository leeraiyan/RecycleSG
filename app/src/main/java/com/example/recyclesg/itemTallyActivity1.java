package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class itemTallyActivity1 extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_tally1);
        button = (Button)  findViewById(R.id.recyclebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanningModule2();
            }
        });
    }

    public void openScanningModule2(){
        Intent intent = new Intent(this, activityScanningPageAdd.class);
        startActivity(intent);
    }
}
