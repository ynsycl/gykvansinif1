package com.oyunhamuru.s04_gorsel_bilesenler_seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar volumeSeekBar;
    private TextView showSeekBarValueTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nesneleriOlustur();



        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            //Bu aşağıdaki metodlar seekBarApp isimli seekbar'ımıza setOnSeekBarChangeListener metodunu kullanarak
            //implement edildi
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
               // SeekBar ilerletilmesinde çalışan metod

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                // SeekBar ilerletilmesi başlatıldığında çalışan metod
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // "SeekBar ilerletilmesi durdurulduğunda çalışan metod
                showSeekBarValueTextView.setText("Ses: " + progress + "/" + seekBar.getMax());



            }
        });


    }

    // Bu metod ile görsel bileşenlerimizi oluşturuyoruz
    private void nesneleriOlustur() {
        volumeSeekBar = (SeekBar) findViewById(R.id.seekbar_volume);
        showSeekBarValueTextView = (TextView) findViewById(R.id.tv_show_seekbar_value);
        showSeekBarValueTextView.setText("Ses: " + volumeSeekBar.getProgress() + "/" + volumeSeekBar.getMax());

    }
}
