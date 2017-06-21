package com.oyunhamuru.s02_intent_implicitustukapali_intent_olusturma;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button webSitesiAcButton;
    private Button haritaAcButton;
    private Button veriPaylasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nesneleriOlustur();
    }

    private void nesneleriOlustur() {
        webSitesiAcButton=(Button) findViewById(R.id.btn_web_sitesi_ac);
        webSitesiAcButton.setOnClickListener(this);

        haritaAcButton=(Button) findViewById(R.id.btn_harita_ac);
        haritaAcButton.setOnClickListener(this);

        veriPaylasButton=(Button) findViewById(R.id.btn_veri_paylas);
        veriPaylasButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_web_sitesi_ac:
                webSitesiAc();
                break;
            case R.id.btn_harita_ac:
                haritaAc();
                break;
            case R.id.btn_veri_paylas:
                veriPaylas();
                break;
        }

    }

    private void veriPaylas() {
        String paylasilacakText="Merhaba";
        String mimeType="text/plain";
        String title=MainActivity.class.getSimpleName();

        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(paylasilacakText)
                .startChooser();

    }

    private void haritaAc() {
        String adres="1600 Amphitheatre Parkway, CA";

        Uri.Builder builderAdres=new Uri.Builder();
        builderAdres.scheme("geo")
                .path("0.0")
                .query(adres);
        Uri adresUri=builderAdres.build();

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(adresUri);

        //Bu intent telefonda çalışabilir mi diye kontrol ediyoruz.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }

    private void webSitesiAc() {
        String url="https://gelecegiyazanlar.turkcell.com.tr/gelecegiyazankadinlar";

        /**
         *
         * Uri nedir?
         *Uri (uniform resource identifier) yani nizami kaynak belirteci,
         * bir kaynağı ya da veriyi isimlendirmek
         *için kullanılan bir standarttır. Aynı zamanda kaynağı nitelendirir.
         * Intent kullanırken bu türden bilgiler gereklidir.
         */
        Uri uriWebSite=Uri.parse(url);

        Intent intent=new Intent(Intent.ACTION_VIEW,uriWebSite);
        startActivity(intent);

    }
}
