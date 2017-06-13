package com.oyunhamuru.a03listviewozellestirme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //List tipinde bir arrayList oluşturduk
    //bu ile fakeData  data oluşturduk
    final List<Kisi> kisiler=new ArrayList<Kisi>();

    //ListView tipinde bir değişken oluşturduk
    private ListView kisiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listeye fakeData'lar atadık
        kisiler.add(new Kisi("Ahmet Yılmaz", false));
        kisiler.add(new Kisi("Ayşe Küçük", true));
        kisiler.add(new Kisi("Fatma Bulgurcu", true));
        kisiler.add(new Kisi("İzzet Altınmeşe", false));
        kisiler.add(new Kisi("Melek Subaşı", true));
        kisiler.add(new Kisi("Selim Serdilli",false));
        kisiler.add(new Kisi("Halil İbrahim",false));


        //activty_main.xml'deki lv_kisiler ListViewini kisiListView 'e atıyoruz
        kisiListView=(ListView) findViewById(R.id.lv_kisiler);

        //Oluşturduğumuz ÖzelAdaptör class'ından bir tane obje oluşturuyoruz
        OzelAdapter adaptorumuz=new OzelAdapter(this, kisiler);

        //Oluşturduğumuz adapter'ı kisiListViewe atayıp verilerin gösterilmesini sağlıyoruz
        kisiListView.setAdapter(adaptorumuz);

        //listedeki her bir elemana tıklama olayını atar
        kisiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Biz sadece position ile işlem yaptık metod otomatik oluştu
                int kisiIndexi=position;

                Kisi kisi=kisiler.get(kisiIndexi);
                String kisiIsmi=kisi.getIsim().toString();
                Toast.makeText(getApplicationContext(),kisiIsmi,Toast.LENGTH_SHORT).show();
            }
        });



    }
}
