package com.oyunhamuru.n02_kodla_fragment_ekleme;


//Bu Class'i import edeceksin
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //KodlaFragment clasımızdan bir obje oluşturyoruz.
        KodlaFragment kodlaFragment=new KodlaFragment();

        //Fragment işlemlerinde kullanmak için bir fragmentManager nesnesi oluşturuyoruz.
        FragmentManager fragmentManager=getFragmentManager();

        //İşlem yapılırken beklenmeyen bir hatada uygulamanın hata vermesini
        //önlemek için Transaction başlatıyoruz.
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


        /**
         *
         * Transaction'a görev atıyoruz
         * fragmentTransaction.add(paramatre1,paramatre2,paramatre3)
         * parametre1=Fragmentin gösterileceğil layout'un idsi
         * parametre2=Gösterilecek fragment'in nesnesi,
         * paramtre3 oluşturulan fragmente bir alias tanımlama
         */
        fragmentTransaction.add(R.id.layout_activity_main,kodlaFragment,"Kodla Fragment");


        fragmentTransaction.commit();


    }
}
