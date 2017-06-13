package com.oyunhamuru.a02alertdialoginternetkontrolu;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Button türünde webSayfasıAcButton objesini oluşturuyoruz
    private Button webSayfasıAcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Utilty classına bakmayı unutma
        //app>manifests>AndroidManifest.xml dosyasında izin işlemi yaptık unutma


        //webSayfasıAcButton'a activity_main.xml layoutundaki btn_web_sayfasi_ac objesini
        //atıyoruz
        webSayfasıAcButton=(Button) findViewById(R.id.btn_web_sayfasi_ac);

        //webSayfasıAcButton objesine onClik olayını atıyoruz
        webSayfasıAcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //İnternet bağlantısını kontrol ediyoruz
                //Eğer internet yoksa if bloğunun içine girer
                if(!Utility.hasInternetConnection(getApplicationContext())){

                    //AlertDialog objesi oluşturuyoruz
                    AlertDialog.Builder diyalogOlusturma= new AlertDialog.Builder(MainActivity.this);

                    /*
                    Objeye değerler atıyoruz setTitle ile başlığını ayarlıyoruz setMessage ile gösterilecek
                    uyarı mesajını ayarlıyoruz setNegativeButton ile Uygulamadan çıkma kodunu yazıyor
                    setPossitiveButton ile de ayarlara yönlendiriyoruz
                     */
                    diyalogOlusturma.setTitle("Uyarı")
                            .setMessage("İnternet bağlantınız yok")
                            .setNegativeButton("Uygulamadan Çık", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Uygulamayı kapatıyoruz
                                            dialog.dismiss();
                                            finish();
                                        }
                                    })
                            .setPositiveButton("Ayarlara Git", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //startActivty ile ayarlar menüsünü açaçağız
                                            startActivity( new Intent(Settings.ACTION_SETTINGS));
                                        }
                                    });

                    //Aşağıdaki kod ile de alert diyaloğu çalıştırıyoruz
                    diyalogOlusturma.create().show();

                }else {
                    //Eğer internet varsa burası çalışır
                    Toast.makeText(getApplicationContext(),"İnternet var",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
