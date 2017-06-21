package com.oyunhamuru.s01_intent_intentler_arasi_veri_yollama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetayActivity extends AppCompatActivity {

    private TextView veriGosterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        Intent intent=getIntent();

        String gelenVeri=intent.getExtras().getString(MainActivity.VERI_KEY);

        veriGosterTextView=(TextView) findViewById(R.id.tv_veri_goster);

        if(gelenVeri.isEmpty()||gelenVeri.trim().equals("")){
            Toast.makeText(getApplicationContext(),"Gelen veri boş",Toast.LENGTH_SHORT).show();
        }else {
            veriGosterTextView.setText(gelenVeri);
        }

    }
}
