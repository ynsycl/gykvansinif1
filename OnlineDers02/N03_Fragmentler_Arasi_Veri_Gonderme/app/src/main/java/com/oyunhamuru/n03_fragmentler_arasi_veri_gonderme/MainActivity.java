package com.oyunhamuru.n03_fragmentler_arasi_veri_gonderme;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


//Oluştruduğumuz VeriTasiyici interface'ini MainActivity class'ına imlement
//ettikten sonra interface'in metodunu da imlement ettik
public class MainActivity extends AppCompatActivity implements VeriTasiyici {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * İnterface'den gelen metod bu metod aracılığı ile veri transferi yapılıyır
     * @param veri=taşınacak veri
     */
    @Override
    public void gonder(String veri) {

        //Fragmentlara erişip işlem yapmak için FragmentManager'dan
        //bir nesne oluşturuyoruz
        FragmentManager fragmentManager=getFragmentManager();

        //Verinin gösterileceği fragment'e erişiyoruz.
        FragmentB fragmentB=(FragmentB) fragmentManager.findFragmentById(R.id.fragment2);

        //FragmentB'deki yaziDegistir metodunu çağırıyoruz
        fragmentB.yaziyiDegistir(veri);

    }
}
