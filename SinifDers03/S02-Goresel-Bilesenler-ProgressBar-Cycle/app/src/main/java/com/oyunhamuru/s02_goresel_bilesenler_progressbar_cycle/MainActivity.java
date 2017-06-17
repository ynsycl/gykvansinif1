package com.oyunhamuru.s02_goresel_bilesenler_progressbar_cycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button hideProgressBarButton;
    private ProgressBar progressBarCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarCycle=(ProgressBar) findViewById(R.id.progressBar_cyclic);
        hideProgressBarButton=(Button) findViewById(R.id.btn_progress_bar_gizle);

        hideProgressBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(progressBarCycle.getVisibility()==View.VISIBLE){
                    progressBarCycle.setVisibility(View.INVISIBLE);
                }else {
                    progressBarCycle.setVisibility(View.VISIBLE);
                }


            }
        });

    }
}
