package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class activityRewards extends AppCompatActivity {
    private ProgressBar progressBar;
//    private SeekBar seekBar;
    int progress = 75;
    private Handler mHandler = new Handler();
    public TextView textTier;
    public TextView textLevelUp;
    public TextView textVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewardsnew);

        //defining textTier and progressbar
        textTier = (TextView) findViewById(R.id.textTier);
        textLevelUp = (TextView) findViewById(R.id.textLevelUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(progress,true);
        textVisit = (TextView) findViewById(R.id.visitTreenow);

        mHandler.postDelayed(mAnimateRunnable, 1500);
        mHandler.postDelayed(mAnimateRunnable, 1500);
        mHandler.postDelayed(mAnimateRunnable, 1000);
//        progressBar.setProgress(90,true);



//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progressBar.setProgress(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        //defining propeerties buttonVisitNow
        Button buttonVisitTreeNow = (Button) findViewById(R.id.buttonVisitTreeNow);

        //listening for button click^
        buttonVisitTreeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAREnabled();
            }
        });

    }

    private Runnable mAnimateRunnable = new Runnable() {
        @Override
        public void run() {

            if(progress == 75) {
                progressBar.setProgress(100, true);
                progress = 100;
            }


            else if(progress == 100){
                progressBar.setProgress(0, false);
                progress = 0;
                textTier.setText("Tier 4");
            }

            else if(progress == 0){
                progressBar.setProgress(5, true);
                progress = 5;
                textLevelUp.setText("Your tree has grown!");

                textVisit.setText("Visit");
            }


        }
    };


    public void openAREnabled(){
        Intent intent = new Intent(this, ARenabled.class);
        startActivity(intent);
        finish();
    }


}
