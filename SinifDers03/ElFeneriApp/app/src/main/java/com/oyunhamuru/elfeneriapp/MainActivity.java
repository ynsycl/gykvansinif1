package com.oyunhamuru.elfeneriapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraManager mCameraManager;
    private String mKameraId;
    private ImageButton mElFeneriButton;
    private Boolean flasAcikMi;
    private MediaPlayer mediaPlayer;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        degiskenleriOlustur();

        flasKontrol();



        try {
            mKameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void degiskenleriOlustur() {
        mElFeneriButton = (ImageButton) findViewById(R.id.btn_el_feneri);
        mElFeneriButton.setOnClickListener(this);
        flasAcikMi = false;
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

    }

    private void flasKontrol() {
        Boolean flasVarMi = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!flasVarMi) {

            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .create();
            alert.setTitle("Uyarı!!");
            alert.setMessage("Telefonunuzda flaş bulunmamakta!");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "Uygulamadan Çık", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                    System.exit(0);
                }
            });
            alert.show();
            return;
        }
    }

    public void flasiAc() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mKameraId, true);
                muzikCal();
                mElFeneriButton.setImageResource(R.drawable.switchon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void flasiKapat() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mKameraId, false);
                muzikCal();
                mElFeneriButton.setImageResource(R.drawable.switchoff);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void muzikCal(){

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.beep);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.release();
            }
        });
        mediaPlayer.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_el_feneri){
            try {
                if (flasAcikMi) {
                    flasiKapat();
                    flasAcikMi = false;
                } else {
                    flasiAc();
                    flasAcikMi = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
