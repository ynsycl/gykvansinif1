package com.oyunhamuru.s01_personel_otomasyonu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oyunhamuru.s01_personel_otomasyonu.data.Kisi;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiContract;
import com.oyunhamuru.s01_personel_otomasyonu.data.KisiDbHelper;

public class KisiGuncelleActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText kisiAdEditText;
    private EditText kisiSoyadEditText;

    private Button guncelleButton;
    private Button yeniKayitaGitButton;

    //Oluşturduğumuz Veritabanı yardımıcı classından nesne türetiyoruz
    private KisiDbHelper dbHelper=new KisiDbHelper(this);;

    //İntent ile gelen güncelleme yapılacak kişinin idsini bu değişkene atayacağız
    private long kisiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_guncelle);

        getSupportActionBar().setTitle("Kişi Güncelle");

        //İntenten  gelen id'yi alıyoruz
        //KisiContract.KisiEnty.ID_KEY bizim tanımladığımız bir anahtar değişken
        //böyle yapmamızın sebebi anahtar değeri elle yazarken hata yapma riskinden dolayı
        Intent intentGelen=getIntent();
        kisiId=intentGelen.getExtras().getLong(KisiContract.KisiEnty.ID_KEY,0);

        nesneleriOlustur();

        //id yardımı ile veritabanından verileri cekip bu activity'deki görsel nesnelere
        //atama yapıyoruz yani EditTextlerin text'ine ad ve soyad'ı katıyoruz
        kisileriGetir(kisiId);
    }


    /**
     * Veritabanına bağlanıp id parametresi ile kişi bilgilerini alır
     * @param kisiId
     */
    private void kisileriGetir(long kisiId) {

        //Veritabanından çektiğimiz bilgileri kisi nesnesine atayacağız
        Kisi kisi=new Kisi();

        //Veritabanında çalıştırılacak veri çekme sorgusu
        String selectSorgusu="SELECT * FROM "+ KisiContract.KisiEnty.TABLE_NAME+" WHERE _ID="+kisiId;

        //Veritabanına okuma özelliği verip veri okuma yapacağız
        SQLiteDatabase mDb=dbHelper.getReadableDatabase();

        //veritabanında çalışan sorgunun sonucunu Cursor nesnesine atama yapıyoruz
        Cursor cursor=mDb.rawQuery(selectSorgusu,null);

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

        //EditTextlere aldığımız değerleri atama yapıyoruz
        kisiAdEditText.setText(kisi.getAd());
        kisiSoyadEditText.setText(kisi.getSoyad());
    }



    private void nesneleriOlustur(){
        kisiAdEditText=(EditText) findViewById(R.id.et_ad_guncelle);
        kisiSoyadEditText=(EditText) findViewById(R.id.et_soyad_guncelle);

        guncelleButton=(Button) findViewById(R.id.btn_guncelle);
        yeniKayitaGitButton=(Button) findViewById(R.id.btn_yeni_kayita_git);

        guncelleButton.setOnClickListener(this);
        yeniKayitaGitButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_guncelle:
                String kisiAd=kisiAdEditText.getText().toString();
                String kisiSoyad=kisiSoyadEditText.getText().toString();

                String hataMesaji="";

                if(kisiAd.trim().equals("")||kisiAd.isEmpty()){
                    hataMesaji+="Bir ad girmelisiniz.\n";
                }
                if(kisiSoyad.trim().equals("")||kisiSoyad.isEmpty()){
                    hataMesaji+="Bir soyad girmelisiniz.";
                }
                if(hataMesaji==""){

                    //Id'si bilinen kişiyi silme metodu
                    kaydiGuncelle(kisiId,kisiAd,kisiSoyad);

                    Toast.makeText(getApplicationContext(),"Kişi başarıyla güncellendi",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),hataMesaji,Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_yeni_kayita_git:
                Intent intent=new Intent(KisiGuncelleActivity.this,KisiEkleActivity.class);
                startActivity(intent);
                break;
        }

    }


    private void kaydiGuncelle(long kisiId, String kisiAd, String kisiSoyad) {

        //kisiId'yi String türüne ceviriyoruz
        String guncellenecekKisiId=String.valueOf(kisiId);

        //Veritabanına yazma özelliği verip veri güncelleme işlemi yapacağız
        SQLiteDatabase mDb=dbHelper.getWritableDatabase();

        //ContentValues sınıfı ile veriler hashmap şeklinde tutup
        //veritabanındaki veriyi güncellemede kullanacağiız.
        ContentValues cv=new ContentValues();
        cv.put(KisiContract.KisiEnty.COLUM_AD,kisiAd);
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,kisiSoyad);


         /**
         *int mDb.update(String table,ContentValues values,String whereClause,String[] whereArgs)
         *table parametresi=Verinin silineceği tablo string değer alır
         *values parametresi=yeni değerlerin tutulduğu contentvalue objesi
         *whereClause parametresi=Güncellenirken uygulanacak isteğe bağlı WHERE deyimi.
         *Null geçildiğinde, tüm satırlar güncellenecektir.
         *whereArgs parametresi=parametre2 ye eklenen ? (soru işareti yerine gelecek olan değer)
         *string dizi değeri alır
         *metod geriye kaç tane satır güncellenmişse onun sayısını döner int tipinde
          */
        mDb.update(KisiContract.KisiEnty.TABLE_NAME,cv, KisiContract.KisiEnty._ID+" = ? ",
                new String[]{String.valueOf(guncellenecekKisiId)});

        //Veritabanını kapatıyorurz
        mDb.close();

    }
}
