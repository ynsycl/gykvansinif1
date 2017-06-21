package com.oyunhamuru.s01_personel_otomasyonu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button kisiEkleButton;
    private Button kisileriListeleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Personel Otomasyon");

        nesneleriOlustur();
    }

    private void nesneleriOlustur() {
        kisiEkleButton=(Button) findViewById(R.id.btn_kisi_ekle);
        kisiEkleButton.setOnClickListener(this);

        kisileriListeleButton=(Button) findViewById(R.id.btn_kisiler_listesi);
        kisileriListeleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_kisi_ekle:
                Intent intent=new Intent(MainActivity.this,KisiEkleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_kisiler_listesi:
                Intent intent2=new Intent(MainActivity.this,KisiListesiActivity.class);
                startActivity(intent2);

                break;
        }

    }
}
