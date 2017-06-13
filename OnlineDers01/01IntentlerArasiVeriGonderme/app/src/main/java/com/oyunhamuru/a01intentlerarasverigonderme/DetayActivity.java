package com.oyunhamuru.a01intentlerarasverigonderme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    //TextView tipinde veriGosterTextView objesini oluşturuyoruz
    private TextView veriGosterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        //ActionBar'ın başlığını değiştirir
        getSupportActionBar().setTitle("Detay");

        //veriGosterTextView objesine activity_detay.xml layout'undaki tv_veri_goster
        //nesnesini atıyoruz
        veriGosterTextView=(TextView) findViewById(R.id.tv_veri_goster);

        //Bundle objesi ile gelen veriyi alacağız
        Bundle bundleExtras=getIntent().getExtras();

        //getString metodu ile intent'e kattığımız veriye erişiyoruz
        String veri=bundleExtras.getString(MainActivity.VERI_BASLIGI);

        //veriGosterTextView objesinin textine MainActivity intentinden gelen veriyi atıyoruz
        veriGosterTextView.setText(veri);


    }
}
