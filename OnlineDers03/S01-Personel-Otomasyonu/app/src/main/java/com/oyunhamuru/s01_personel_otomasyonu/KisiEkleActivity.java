package com.oyunhamuru.s01_personel_otomasyonu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oyunhamuru.s01_personel_otomasyonu.data.KisiContract;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiDbHelper;

public class KisiEkleActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText kisiAdTextView;
    private EditText kisiSoyadTextView;
    private Button yeniKisiKayitButton;


    //Oluşturduğumuz Veritabanı yardımıcı classından nesne türetiyoruz
    private KisiDbHelper dbHelper=new KisiDbHelper(this);

    //Ekelenen yeni kişinin id'sini tutan değişken
    long yeniKayitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_ekle);

        getSupportActionBar().setTitle("Kişi Ekle");

        nesneleriOlustur();
    }

    private void nesneleriOlustur() {
        kisiAdTextView=(EditText) findViewById(R.id.et_ad_ekle);
        kisiSoyadTextView=(EditText) findViewById(R.id.et_soyad_ekle);
        
        yeniKisiKayitButton=(Button) findViewById(R.id.btn_yeni_kayit_ekle);
        yeniKisiKayitButton.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_yeni_kayit_ekle){
            String kisiAd=kisiAdTextView.getText().toString();
            String kisiSoyad=kisiSoyadTextView.getText().toString();
            
            String hataMesaji="";
            
            if(kisiAd.trim().equals("")||kisiAd.isEmpty()){
                hataMesaji+="Bir ad girmelisiniz.\n";
            }
            if(kisiSoyad.trim().equals("")||kisiSoyad.isEmpty()){
                hataMesaji+="Bir soyad girmelisiniz.";
            }
            if(hataMesaji==""){
                //yeni kişi ekleme metodu
                yeniKisiKaydi(kisiAd,kisiSoyad);
            }
            else {
                Toast.makeText(getApplicationContext(),hataMesaji,Toast.LENGTH_SHORT).show();
            }
            
        }
        
    }


    /**
     * Yeni kişiyi veritabanına kaydeder
     * @param kisiAd eklenecek kişinin adı
     * @param kisiSoyad eklenecek kişinin soyadı
     */
    private void yeniKisiKaydi(String kisiAd, String kisiSoyad) {

        //Veritabanına yazma özelliği verip veri ekleme yapacağız
        SQLiteDatabase mDb=dbHelper.getWritableDatabase();

        //ContentValues sınıfı ile veriler hashmap şeklinde tutup
        //Veritabanına eklemede kullanılır.
        ContentValues cv=new ContentValues();

        //cv.put(parametre1,parametre2)
        //parametre1=sütun ismi
        //parametre2=eklenecek veri
        cv.put(KisiContract.KisiEnty.COLUM_AD,kisiAd);
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,kisiSoyad);

        try {
            //Transaction başlatır
            mDb.beginTransaction();

            /**
             * long insert(String table,String nulllColumHack,ContentValues values)
             *
             * paramatre table=veri eklenecek tablo ismi
             * parametre nullColumHack=isteğe bağlı eklenebilir
             * Eğer veri eklenmeyen bir sutun varsa  buradaki değer
             * boş sütuna eklenir.
             * parametre values=eklenecek verinin tutulduğu ContentValues nesnesi
             *
             * metod geriye long tipinde yeni kaydedilen verinin id'sini döner
             */
            yeniKayitId=mDb.insert(KisiContract.KisiEnty.TABLE_NAME,null,cv);

            //Transaction başarılı
            mDb.setTransactionSuccessful();

        }catch (SQLException ex){

        }
        finally {

            //Transaction'ı sonlandır
            mDb.endTransaction();
            //Veritabanını kapat
            mDb.close();

        }

        //intent yardımı ile  yeni kaydedilen kişiyi güncelleme activity'sinde
        // açıyoruz
        Intent intent=new Intent(KisiEkleActivity.this,KisiGuncelleActivity.class);
        intent.putExtra(KisiContract.KisiEnty.ID_KEY,yeniKayitId);
        startActivity(intent);

    }
}
