package com.oyunhamuru.s03_gorsel_bilesenler_progressbar_horizontal;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //nesneleri oluşturuyoruz
        progressBarHorizontal = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);


        // Arkaplanda çalışan bir tread oluşturuyoruz
        new Thread(new Runnable() {


            public void run() {

                 /*
                    ProgressBar'ın maximum değeri 100 bu yüzden progressStatus 100 olana kadar
                    şartlı bir döngü başlattık. progressStatus 100 0lunca döngüden çıkılacak
                   */
                while (progressStatus < 100) {

                    //progressStatus değerini 1 arttır
                    progressStatus += 1;

                    /*
                    Handler, arkaplanda çalışan,( veya yardımcı thread'de), belirli aralıklarla tekrarlanmasını istediğimiz olaylara yön vermemizi
                    ve bunları yönetmemizi sağlayan yapılardır. Yani belirli aralıklarla bir kodun çalışmasını istiyorsak
                    bu yapılara ihtiyaç duyarız.
                     */
                    handler.post(new Runnable() {
                        public void run() {
                            progressBarHorizontal.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBarHorizontal.getMax());
                        }
                    });
                    try {
                        // 200 milisaniye tread'i uyutur
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }



}
