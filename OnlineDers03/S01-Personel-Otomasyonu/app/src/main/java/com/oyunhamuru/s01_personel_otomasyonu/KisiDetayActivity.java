package com.oyunhamuru.s01_personel_otomasyonu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oyunhamuru.s01_personel_otomasyonu.data.Kisi;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiContract;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiDbHelper;

public class KisiDetayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView kisiAdTextView;
    private TextView kisiSoyadTextView;

    private Button kisiSilButton;
    private Button kisiGuncelleButton;

    //Oluşturduğumuz Veritabanı yardımıcı classından nesne türetiyoruz
    private KisiDbHelper dbHelper=new KisiDbHelper(this);

    //KisiListesiActivity classından gelen id'yi bu değişkene atayacağız
    private long kisiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_detay);
        getSupportActionBar().setTitle("Kişi Detay");

        //KisiListesiActivity classından gelen id'yi alıyoruz
        //KisiContract.KisiEnty.ID_KEY bizim tanımladığımız bir anahtar değişken
        //böyle yapmamızın sebebi anahtar değeri elle yazarken hata yapma riskinden dolayı
        Intent intent=getIntent();
        kisiId=intent.getLongExtra(KisiContract.KisiEnty.ID_KEY,0);


        nesneleriOlustur();

        //id yardımı ile veritabanından verileri cekip bu activity'deki görsel nesnelere
        //atama yapıyoruz yani textviewlerin text'ine ad ve soyad'ı katıyoruz
        kisiyiGetir(kisiId);


    }




    private void nesneleriOlustur() {
        kisiAdTextView=(TextView) findViewById(R.id.tv_ad_detay);
        kisiSoyadTextView=(TextView)findViewById(R.id.tv_soyad_detay);

        kisiGuncelleButton=(Button) findViewById(R.id.btn_guncelle_detay);
        kisiSilButton=(Button) findViewById(R.id.btn_sil_detay);

        kisiSilButton.setOnClickListener(this);
        kisiGuncelleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_guncelle_detay:
                //Güncelleme activity'sini başlatıyoruz
                //paramatre olarak kisiId'sini yolluyoruz
                Intent intentGuncelle=new Intent(KisiDetayActivity.this,KisiGuncelleActivity.class);
                intentGuncelle.putExtra(KisiContract.KisiEnty.ID_KEY,kisiId);
                startActivity(intentGuncelle);

                break;

            case R.id.btn_sil_detay:

                //Id'si bilinen kişiyi silme metodu
                kisiSil(kisiId);
                break;
        }

    }


    /**
     * Veritabanına bağlanıp id parametresi ile kişi bilgilerini alır
     * @param kisiId
     */
    private void kisiyiGetir(long kisiId) {
        //Veritabanından çektiğimiz bilgileri kisi nesnesine atayacağız
        Kisi kisi=new Kisi();

        //Veritabanında çalıştırılacak veri çekme sorgusu
        String selectSorgu="SELECT * FROM "+ KisiContract.KisiEnty.TABLE_NAME+" WHERE _ID="+kisiId;

        //Veritabanına okuma özelliği verip veri okuma yapacağız
        SQLiteDatabase mDb=dbHelper.getReadableDatabase();

        //veritabanında çalışan sorgunun sonucunu Cursor nesnesine atama yapıyoruz
        Cursor cursor=mDb.rawQuery(selectSorgu,null);

        //cursor'un index değerini en başa alıyoruz
        cursor.moveToFirst();

        //cursor'da veri varsa if blogunun içine girip verileri kişi nesnesine atama yapıyoruz
        if(cursor.getCount()>0){

            /*
            cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD))
            aslında cursor.getString(index)=cursor.getString(0) 'ı ifade eder
            cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD) kullanarak
            elle veri girip hata yapma olasılığını ortadan kaldırıyoruz
             */
            kisi.setAd(cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_AD)));
            kisi.setSoyad(cursor.getString(cursor.getColumnIndex(KisiContract.KisiEnty.COLUM_SOYAD)));

        }
        //cursor ve veritabanını kapatıyoruz
        cursor.close();
        mDb.close();

        //Textviewlere aldığımız değerleri atama yapıyoruz
        kisiAdTextView.setText(kisi.getAd());
        kisiSoyadTextView.setText(kisi.getSoyad());


    }

    /**
     * Veritabanında belirtilen id deki kşiyi siler
     * @param kisiId silinecek kişinin id si
     */
    private void kisiSil(long kisiId) {


        String silinecekkisiId=String.valueOf(kisiId);

        //Veritabanına yazma özelliği verip veri silme işlemi yapacağız
        SQLiteDatabase mDb=dbHelper.getWritableDatabase();

        /*
         int mDb.delete(String table,String whereClause,String[] whereArgs)
         table parametresi=Verinin silineceği tablo string değer alır
         whereClause parametresi=silme işlemi sırasında uygulanacak isteğe bağlı WHERE yan cümlesi
         null gecilmesi durumunda tüm tabloyu siler. string değer alır
         whereArgs parametresi=parametre2 ye eklenen ? (soru işareti yerine gelecek olan değer)
         string dizi değeri alır

         metod geriye kaç tane satır silinmişse onun sayısını döner int tipinde
          */
        mDb.delete(KisiContract.KisiEnty.TABLE_NAME, KisiContract.KisiEnty._ID+" = ? ", new String[]{silinecekkisiId});

        //Veritabanını kapatıyoruz
        mDb.close();

        //Detayda gösterilecek kişiyi sildiğimiz için liste activity'sine geri dönüyoruz
        Intent intentKisiList=new Intent(KisiDetayActivity.this,KisiListesiActivity.class);
        startActivity(intentKisiList);
    }
}
