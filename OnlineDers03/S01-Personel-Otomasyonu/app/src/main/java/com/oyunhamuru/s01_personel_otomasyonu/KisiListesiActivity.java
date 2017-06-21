package com.oyunhamuru.s01_personel_otomasyonu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oyunhamuru.s01_personel_otomasyonu.data.Kisi;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiContract;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiDbHelper;

import java.util.ArrayList;
import java.util.List;

public class KisiListesiActivity extends AppCompatActivity {

    //listview objesini oluşturur
    private ListView kisilerListView;

    //listview'e bağlanacak adapteri oluşturur
    private KisiAdapter kisiAdapter;

    //listview'e atanacak veri listesini oluşturur
    List<Kisi> kisiListesi;

    //Oluşturduğumuz Veritabanı yardımıcı classından nesne türetiyoruz
    private KisiDbHelper dbHelper=new KisiDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_listesi);

        getSupportActionBar().setTitle("Kişi Listesi");

        //Listview'i eşleştirme
        kisilerListView=(ListView) findViewById(R.id.lv_kisiler);

        //kişieri veritanından alıp kisiListesine atayan metodun çağrılması
        kisiListesi=kisiListesiGetir();

        //KisiAdapter objesini oluşturur
        kisiAdapter=new KisiAdapter(this,kisiListesi);

        //Adapter'i kisiListesine set etme
        kisilerListView.setAdapter(kisiAdapter);

        //listview'deki bir satıra tıklanınca çalışan tıklama olayı
        kisilerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(KisiListesiActivity.this,KisiDetayActivity.class);
                intent.putExtra(KisiContract.KisiEnty.ID_KEY,id);
                startActivity(intent);
            }
        });



    }

    /**
     * Veritabanından kişi listesni çeker
     * @return geriye kişilistesini döndürür
     */
    private List<Kisi> kisiListesiGetir() {
        //Yeni bir kişi tüeünde ArrayList oluşturuyoruz
        List<Kisi> kisiler=new ArrayList<Kisi>();

        //Veritabanına okuma özelliği verip veri okuma yapacağız
        SQLiteDatabase mDb=dbHelper.getReadableDatabase();

        //Veritabanında çalıştırılacak veri çekme sorgusu
        String selectSorgusu="SELECT * FROM "+ KisiContract.KisiEnty.TABLE_NAME;

        //veritabanında çalışan sorgunun sonucunu Cursor nesnesine atama yapıyoruz
        Cursor cursor=mDb.rawQuery(selectSorgusu,null);

        //cursor'da veri varsa if blogunun içine girip verileri kişi nesnesine atama yapıyoruz
        if(cursor!=null){


            if(cursor.moveToFirst()){
                //Döngü Cursordaki tüm verileri okuyana kadar devam eder
                do{

                      /*
                    cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD))
                    aslında cursor.getString(index)=cursor.getString(0) 'ı ifade eder
                    cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD) kullanarak
                    elle veri girip hata yapma olasılığını ortadan kaldırıyoruz
                     */
                    int kisiId=cursor.getInt(cursor.getColumnIndex(KisiContract.KisiEnty._ID));
                    String ad=cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD));
                    String soyad=cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_SOYAD));

                    //Veritabanından çektiğimiz bilgileri kisi nesnesine atama yapıyoruz
                    Kisi kisi=new Kisi(kisiId,ad,soyad);

                    //kisi nesnesini ArrayListe ekliyoruz
                    kisiler.add(kisi);

                }while (cursor.moveToNext());
            }
        }

        //cursor ve veritabanını kapatıyoruz
        cursor.close();
        mDb.close();

        //kişi listesini döndürüyoruz
        return kisiler;

    }
}
